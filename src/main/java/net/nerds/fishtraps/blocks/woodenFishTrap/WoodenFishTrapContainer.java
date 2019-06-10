package net.nerds.fishtraps.blocks.woodenFishTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;

public class WoodenFishTrapContainer extends BaseFishTrapContainer {

    public WoodenFishTrapContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(syncId, playerInventory, inventory);
    }
}
