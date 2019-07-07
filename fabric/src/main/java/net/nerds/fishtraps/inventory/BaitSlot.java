package net.nerds.fishtraps.inventory;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.nerds.fishtraps.items.FishingBait;

public class BaitSlot extends Slot {

    public BaitSlot(Inventory inventory, int invIndex, int x, int y) {
        super(inventory, invIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemStack itemStack) {
        return itemStack.getItem() instanceof FishingBait;
    }

    public ItemStack takeStack(int i) {
        return super.takeStack(i);
    }
}
