package net.nerds.fishtraps.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;

import java.util.List;
import java.util.Optional;

public class FishTrapInventory implements IInventory {

    public static final int SLOTS = 46;
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(SLOTS, ItemStack.EMPTY);
    private FishTrapItemHandler fishTrapItemHandler;

    public FishTrapInventory(BaseFishTrapTileEntity entity) {
        fishTrapItemHandler = new FishTrapItemHandler(entity);
    }

    @Override
    public int getSizeInventory() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return fishTrapItemHandler.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        Optional<ItemStack> stack = Optional.ofNullable(getStackInSlot(index));
        if(stack.isPresent() && !stack.get().isEmpty()) {
            setInventorySlotContents(index, ItemStack.EMPTY);
        }
        return stack.get();
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory.set(index, stack);
    }

    @Override
    public void markDirty() {
        fishTrapItemHandler.baseFishTrapTileEntity.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    public FishTrapItemHandler getItemHandler() {
        return fishTrapItemHandler;
    }

    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    public void setInventory(NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }
}
