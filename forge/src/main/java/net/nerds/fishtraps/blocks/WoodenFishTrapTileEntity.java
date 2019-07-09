package net.nerds.fishtraps.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.items.FishBait;

import java.util.List;
import java.util.Objects;

public class WoodenFishTrapTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

    private NonNullList<ItemStack> inventory;
    private int maxStorage = 46;
    private long tickCounter = 0;
    private long tickCheck = 100;
    private int luckOfTheSeaLevel = 3;
    private int lureLevel = 3;

    public WoodenFishTrapTileEntity() {
        super(FishTrapsManager.woodenFishTrapEntityType);
        inventory = NonNullList.withSize(maxStorage, ItemStack.EMPTY);
    }

    @Override
    public void tick() {
        if(tickCounter >= tickCheck) {
            tickCounter = 0;
            validateWaterAndFish();
        } else {
            tickCounter++;
        }
    }

    private void validateWaterAndFish() {
        if(!world.isRemote) {
            boolean isSurroundedByWater = true;
            Iterable<BlockPos> waterCheckInterator = BlockPos.getAllInBoxMutable(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),
                    new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1));
            for(BlockPos blockPos : waterCheckInterator) {
                Block block = world.getBlockState(blockPos).getBlock();
                if(world.getTileEntity(pos) != null && (block != Blocks.WATER && !(block instanceof WoodenFishTrap))) {
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
        addItemsToInventory(list);
        ItemStack fishBait = inventory.get(0);
        if(fishBait.getItem() instanceof FishBait) {
            if(fishBait.attemptDamageItem(1, world.rand, null)){
                inventory.set(0, ItemStack.EMPTY);
                markDirty();
            }
        }
    }

    private void addItemsToInventory(List<ItemStack> list) {
        list.stream().forEach(itemStack -> {
            for(int i = 1; i < inventory.size(); i++) {
                if(inventory.get(i).isEmpty()) {
                    inventory.set(i, itemStack);
                    markDirty();
                    break;
                } else if(inventory.get(i).isItemEqual(itemStack) &&
                        (inventory.get(i).getCount() + itemStack.getCount() <= itemStack.getMaxStackSize()) &&
                        itemStack.isStackable()) {
                    inventory.set(i, new ItemStack(itemStack.getItem(), itemStack.getCount() + inventory.get(i).getCount()));
                    markDirty();
                    break;
                }
            }
        });
    }

    @Override
    public int getSizeInventory() {
        return maxStorage;
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.inventory.set(i, itemStack);
        if (itemStack.getCount() > this.getInventoryStackLimit()) {
            itemStack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity playerEntity) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return playerEntity.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventory = itemsIn;
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.inventory);
        }
        return compound;
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.inventory);
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("fishtraps.wooden-fish-trap");
    }

    @Override
    protected Container createMenu(int i, PlayerInventory playerInventory) {
        return new WoodenFishTrapContainer(i, playerInventory, this);
    }
}
