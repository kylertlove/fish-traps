package net.nerds.fishtraps.blocks.woodenFishTrap;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WoodenFishTrapContainer extends Container {

    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    public final World world;

    public WoodenFishTrapContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(null, syncId);
        this.inventory = inventory;
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;

        //Fish Bait Slot
        this.addSlot(new Slot(inventory, 0, 7, 117));


        //player inventory
        int i;
        for(i = 0; i < 3; ++i) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + i * 9 + 9, 8 + var4 * 18, 84 + i * 18));
            }
        }
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity_1) {
        return this.inventory.canPlayerUseInv(playerEntity_1);
    }


    @Override
    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
        this.inventory.onInvClose(playerEntity_1);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
