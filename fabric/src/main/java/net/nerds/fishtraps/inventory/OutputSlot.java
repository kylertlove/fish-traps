package net.nerds.fishtraps.inventory;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

/**
 * Fish trap loot inventory slot objects.  Basically just copy code from FunaceOutputSlot class.
 */
public class OutputSlot extends Slot {

    public OutputSlot(Inventory inventory, int invIndex, int x, int y) {
        super(inventory, invIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemStack itemStack) {
        return false;
    }

    public ItemStack takeStack(int i) {
        return super.takeStack(i);
    }
}
