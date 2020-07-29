package net.nerds.fishtraps.blocks.BaseTrap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.FishTrapItemHandler;
import net.nerds.fishtraps.util.FishTrapsConfig;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public abstract class BaseFishTrapTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private FishTrapItemHandler fishTrapItemHandler = new FishTrapItemHandler();
    private RangedWrapper itemHandlerBait = new RangedWrapper(fishTrapItemHandler,0, 1);
    private RangedWrapper itemHandlerOutput = new RangedWrapper(fishTrapItemHandler, 1, 46);
    private LazyOptional capBait = LazyOptional.of(() -> itemHandlerBait);
    private LazyOptional capOutput = LazyOptional.of(() -> itemHandlerOutput);
    private long tickCounter = 0;
    private long tickCheck;
    private int luckOfTheSeaLevel;
    private int lureLevel;
    private boolean shouldPenalize;
    private int penaltyMultiplier;
    private int penaltyTickCheck;
    private boolean showFishBait = false;
    private ResourceLocation lootLocation;

    public BaseFishTrapTileEntity(TileEntityType tileEntityType, long delay, int lure, int luck) {
        super(tileEntityType);
        this.lootLocation = new ResourceLocation(Fishtraps.MODID, "traps/" + tileEntityType.getRegistryName().getPath());
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
        showFishBait = itemHandlerBait.getStackInSlot(0).getCount() > 0;
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
        LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) this.world))
                .withParameter(LootParameters.POSITION, new BlockPos(pos))
                .withParameter(LootParameters.TOOL, itemStack)
                .withRandom(world.rand)
                .withLuck(this.lureLevel);
        LootTable lootTable = Objects.requireNonNull(this.world.getServer()).getLootTableManager().getLootTableFromLocation(this.lootLocation);
        List<ItemStack> list = lootTable.generate(lootContextBuilder.build(LootParameterSets.FISHING));
        fishTrapItemHandler.addListToInventory(list);
        ItemStack fishBait = itemHandlerBait.getStackInSlot(0);
        if(fishBait.getItem() instanceof FishBait) {
            if(fishBait.attemptDamageItem(1, world.rand, null)){
                this.fishTrapItemHandler.setStackInSlot(0, ItemStack.EMPTY);
                markDirty();
            }
        }
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.fishTrapItemHandler.getList());
        return compound;
    }

    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT compound) {
        super.read(state, compound);
        ItemStackHelper.loadAllItems(compound, this.fishTrapItemHandler.getList());
        this.fishTrapItemHandler.deserializeNBT(compound);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        if(side == Direction.UP) {
            return cap.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, capBait);
        }else if(side == Direction.DOWN) {
            return cap.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, capOutput);
        } else {
            return LazyOptional.empty();
        }
    }

    public FishTrapItemHandler getInventory() {
        return this.fishTrapItemHandler;
    }
}