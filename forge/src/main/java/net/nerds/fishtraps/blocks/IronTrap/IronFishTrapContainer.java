package net.nerds.fishtraps.blocks.IronTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.util.FishTrapNames;

public class IronFishTrapContainer extends BaseFishTrapContainer {

    public IronFishTrapContainer(int i, PlayerInventory playerInventory, IInventory inventory) {
        super(i, playerInventory, inventory, FishTrapNames.ironFishTrapContainerContainerType);
    }
}
