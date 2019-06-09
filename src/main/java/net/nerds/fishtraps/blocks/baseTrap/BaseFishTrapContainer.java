package net.nerds.fishtraps.blocks.baseTrap;

import net.minecraft.container.ContainerType;
import net.minecraft.container.GenericContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.world.World;

public class BaseFishTrapContainer extends GenericContainer {
    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    public final World world;

    public BaseFishTrapContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ContainerType.GENERIC_9X6, syncId, playerInventory, inventory, 6);
        this.inventory = inventory;
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return this.playerInventory.canPlayerUseInv(playerEntity);
    }
}
