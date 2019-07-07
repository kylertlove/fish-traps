package net.nerds.fishtraps.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class WoodenFishTrapTileEntity extends TileEntity implements ITickableTileEntity, ISidedInventory {

    private NonNullList<ItemStack> inventory;
    private int maxStorage = 46;

    public WoodenFishTrapTileEntity(TileEntityType<?> type) {
        super(type);
        inventory = NonNullList.withSize(maxStorage, ItemStack.EMPTY);
    }

    @Override
    public void tick() {

    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

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

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        return compound;
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory)
    {
        return new WoodenFishTrapContainer();
    }

}
