package net.nerds.fishtraps.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.items.FishBait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class FishTrapItemHandler implements IItemHandler {

    Logger logger = LogManager.getLogger(this.getClass().getName());

    BaseFishTrapTileEntity baseFishTrapTileEntity;

    public FishTrapItemHandler(BaseFishTrapTileEntity entity) {
        baseFishTrapTileEntity = entity;
    }

    public void addListToInventory(List<ItemStack> list) {
        list.forEach(itemStack -> {
            for(int i = 1; i <= baseFishTrapTileEntity.getInventory().getInventory().size(); i++) {
                if(insertItem(i, itemStack, false) == ItemStack.EMPTY) {
                    break;
                }
            }
        });
    }

    @Override
    public int getSlots() {
        return FishTrapInventory.SLOTS;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return baseFishTrapTileEntity.getInventory().getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(getStackInSlot(slot).isEmpty()) {
            if(!simulate) {
                baseFishTrapTileEntity.getInventory().getInventory().set(slot, stack);
                baseFishTrapTileEntity.markDirty();
                return ItemStack.EMPTY;
            }
        } else if(getStackInSlot(slot).isItemEqual(stack) &&
                (getStackInSlot(slot).getCount() + stack.getCount() <= getStackInSlot(slot).getMaxStackSize()) &&
                getStackInSlot(slot).isStackable()) {
            if(!simulate) {
                baseFishTrapTileEntity.getInventory().getInventory()
                        .set(slot, new ItemStack(stack.getItem(), stack.getCount() + getStackInSlot(slot).getCount()));
                baseFishTrapTileEntity.markDirty();
            }
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack stack = getStackInSlot(slot);
            if (stack.isEmpty() ||
                    slot < 1 || slot >= getSlots() ||
                    amount < 1) {
                return ItemStack.EMPTY;
            } else if (amount >= stack.getCount()) {
                if(!simulate) {
                    baseFishTrapTileEntity.getInventory().setInventorySlotContents(slot, ItemStack.EMPTY);
                }
                return stack;
            } else {
                stack.shrink(amount);
                return stack.copy().split(amount);
            }
    }

    @Override
    public int getSlotLimit(int slot) {
        return getStackInSlot(slot).getMaxStackSize();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if(slot == 0) {
            if(stack.getItem() instanceof FishBait){
                return true;
            }
            return false;
        }
        return true;
    }
}
