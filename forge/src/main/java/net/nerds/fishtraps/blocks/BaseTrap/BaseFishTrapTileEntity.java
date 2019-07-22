package net.nerds.fishtraps.blocks.BaseTrap;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.FishTrapInventory;
import net.nerds.fishtraps.util.FishTrapsConfig;

import java.util.List;
import java.util.Objects;

public abstract class BaseFishTrapTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    //private NonNullList<ItemStack> inventory;
    private FishTrapInventory inventory = new FishTrapInventory(this);
    private LazyOptional inventoryHolder = LazyOptional.of(() -> inventory.getItemHandler());
    private long tickCounter = 0;
    private long tickCheck;
    private int luckOfTheSeaLevel;
    private int lureLevel;
    private boolean shouldPenalize;
    private int penaltyMultiplier;
    private int penaltyTickCheck;
    private boolean showFishBait = false;

    public BaseFishTrapTileEntity(TileEntityType tileEntityType, long delay, int lure, int luck) {
        super(tileEntityType);
        //inventory = NonNullList.withSize(maxStorage, ItemStack.EMPTY);
        this.luckOfTheSeaLevel = luck;
        this.lureLevel = lure;
        this.shouldPenalize = FishTrapsConfig.FISH_TRAPS_CONFIG.shouldTrapHavePenalty.get();
        this.penaltyMultiplier = FishTrapsConfig.FISH_TRAPS_CONFIG.trapPenaltyMultiplier.get();
        this.penaltyTickCheck = (int)delay * this.penaltyMultiplier;
        this.tickCheck = delay;
    }

    @Override
    public void tick() {
        if(tickCounter >= getValidationNumber()) {
            tickCounter = 0;
            validateWaterAndFish();
        } else {
            tickCounter++;
        }
    }
    private long getValidationNumber() {
        showFishBait = this.inventory.getInventory().get(0).getCount() > 0;
        if(!showFishBait && this.shouldPenalize) {
            return this.penaltyTickCheck;
        } else {
            return this.tickCheck;
        }
    }

    private void validateWaterAndFish() {
        if(!world.isRemote) {
            boolean isSurroundedByWater = true;
            Iterable<BlockPos> waterCheckInterator = BlockPos.getAllInBoxMutable(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),
                    new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1));
            for(BlockPos blockPos : waterCheckInterator) {
                Block block = world.getBlockState(blockPos).getBlock();
                if(world.getTileEntity(pos) != null && (block != Blocks.WATER && !(block instanceof BaseFishTrapBlock))) {
                    isSurroundedByWater = false;
                    break;
                }
            }
            if(isSurroundedByWater) {
                fish();
            }
        }
    }

    private void fish() {
        ItemStack itemStack = new ItemStack(Items.FISHING_ROD);
        itemStack.addEnchantment(Enchantments.LURE, this.lureLevel);
        itemStack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, this.luckOfTheSeaLevel);
        LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld)this.world))
                .withParameter(LootParameters.POSITION, new BlockPos(pos))
                .withParameter(LootParameters.TOOL, itemStack)
                .withRandom(world.rand)
                .withLuck(this.lureLevel);
        LootTable lootTable = Objects.requireNonNull(this.world.getServer()).getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
        List<ItemStack> list = lootTable.generate(lootContextBuilder.build(LootParameterSets.FISHING));
        inventory.getItemHandler().addListToInventory(list);
        ItemStack fishBait = inventory.getStackInSlot(0);
        if(fishBait.getItem() instanceof FishBait) {
            if(fishBait.attemptDamageItem(1, world.rand, null)){
                inventory.setInventorySlotContents(0, ItemStack.EMPTY);
                markDirty();
            }
        }
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory.getInventory());
        return compound;
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory.setInventory(NonNullList.withSize(this.getInventory().getSizeInventory(), ItemStack.EMPTY));
        ItemStackHelper.loadAllItems(compound, this.inventory.getInventory());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        if(side == Direction.DOWN || side == Direction.UP) {
            return cap.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventoryHolder);
        } else {
            return LazyOptional.empty();
        }
    }

    public FishTrapInventory getInventory() {
        return inventory;
    }
}