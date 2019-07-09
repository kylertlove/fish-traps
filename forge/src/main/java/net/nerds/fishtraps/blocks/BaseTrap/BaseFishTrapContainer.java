package net.nerds.fishtraps.blocks.BaseTrap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.BaitSlot;
import net.nerds.fishtraps.util.OutputSlot;

public abstract class BaseFishTrapContainer extends Container {

    private PlayerInventory playerInventory;
    private IInventory inventory;

    public BaseFishTrapContainer(int i, PlayerInventory playerInventory, IInventory inventory, ContainerType containerType) {
        super(containerType, i);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
        int invCountNum = 0;

        //Fish Bait Slot
        //inventory, Slot #, x, y
        this.addSlot(new BaitSlot(inventory, invCountNum++, 8, 118));

        for(int outInvIndex = 0; outInvIndex < 5; ++outInvIndex) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new OutputSlot(inventory, invCountNum++, (8 + column * 18), 17 + (outInvIndex * 18)));
            }
        }

        //player inventory
        int playerInvIndex;
        for(playerInvIndex = 0; playerInvIndex < 3; ++playerInvIndex) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + playerInvIndex * 9 + 9, 8 + var4 * 18, 142 + playerInvIndex * 18));
            }
        }
        for(playerInvIndex = 0; playerInvIndex < 9; ++playerInvIndex) {
            this.addSlot(new Slot(playerInventory, playerInvIndex, 8 + playerInvIndex * 18, 200));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotIndex) {
        Slot initSlot = this.inventorySlots.get(slotIndex);
        if (initSlot.getStack() == ItemStack.EMPTY) {
            return ItemStack.EMPTY;
        }
        ItemStack originalItem = initSlot.getStack().copy();

        if (initSlot.inventory == player.inventory) {
            ItemStack baitItem = initSlot.getStack();
            Slot baitSlot = this.inventorySlots.get(0);
            if(isFishBait(baitItem) && !baitSlot.getHasStack()) {
                baitSlot.putStack(baitItem);
                initSlot.putStack(ItemStack.EMPTY);
                return originalItem;
            }
            return ItemStack.EMPTY;
        } else {
            if(playerInventory.addItemStackToInventory(originalItem)) {
                initSlot.putStack(ItemStack.EMPTY);
                return originalItem;
            } else {
                return ItemStack.EMPTY;
            }
        }
    }

    private boolean isFishBait(ItemStack itemStack) {
        if(itemStack.getItem() instanceof FishBait) {
            return true;
        }
        return false;
    }
}