package net.nerds.fishtraps.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.items.FishBait;

import javax.annotation.Nonnull;
import java.util.List;

public class FishTrapItemHandler extends ItemStackHandler {

    public FishTrapItemHandler() {
        super(46);
    }

    public void addListToInventory(List<ItemStack> list) {
        list.forEach(itemStack -> {
            for(int i = 1; i <= getSlots() - 1; i++) {
                if(insertItem(i, itemStack, false) == ItemStack.EMPTY) {
                    break;
                }
            }
        });
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

    public NonNullList getList() {
        return this.stacks;
    }
}
