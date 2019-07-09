package net.nerds.fishtraps.blocks.DiamondTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.util.FishTrapNames;

public class DiamondFishTrapContainer extends BaseFishTrapContainer {

    public DiamondFishTrapContainer(int i, PlayerInventory playerInventory, IInventory inventory) {
        super(i, playerInventory, inventory, FishTrapNames.diamondFishTrapContainerContainerType);
    }
}
