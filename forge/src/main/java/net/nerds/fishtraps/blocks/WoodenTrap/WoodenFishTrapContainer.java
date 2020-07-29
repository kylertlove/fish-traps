package net.nerds.fishtraps.blocks.WoodenTrap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.BaitSlot;
import net.nerds.fishtraps.util.FishTrapItemHandler;
import net.nerds.fishtraps.util.FishTrapNames;
import net.nerds.fishtraps.util.OutputSlot;

public class WoodenFishTrapContainer extends BaseFishTrapContainer {

    public WoodenFishTrapContainer(int i, PlayerInventory playerInventory, FishTrapItemHandler inventory) {
        super(i, playerInventory, inventory, FishTrapNames.woodenFishTrapContainerContainerType);
    }
}
