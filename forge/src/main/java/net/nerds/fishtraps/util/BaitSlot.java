package net.nerds.fishtraps.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.nerds.fishtraps.items.FishBait;

public class BaitSlot extends SlotItemHandler {

    public BaitSlot(FishTrapItemHandler inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof FishBait;
    }

}
