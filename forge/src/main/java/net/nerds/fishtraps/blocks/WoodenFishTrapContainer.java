package net.nerds.fishtraps.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;

public class WoodenFishTrapContainer extends Container {

    private PlayerInventory playerInventory;
    private IInventory inventory;

    public WoodenFishTrapContainer(int i, PlayerInventory playerInventory, IInventory inventory) {
        super(FishTrapsManager.woodenFishTrapContainerContainerType, i);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
    }

    public static WoodenFishTrapContainer createWoodenContainer(int windowId, PlayerInventory playerInventory, IInventory inventory) {
        return new WoodenFishTrapContainer(windowId, playerInventory, inventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }
}
