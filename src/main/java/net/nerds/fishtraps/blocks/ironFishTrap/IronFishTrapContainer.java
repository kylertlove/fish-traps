package net.nerds.fishtraps.blocks.ironFishTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;

public class IronFishTrapContainer extends BaseFishTrapContainer {

    public IronFishTrapContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(syncId, playerInventory, inventory);
    }
}
