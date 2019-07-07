package net.nerds.fishtraps.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class WoodenFishTrapContainer extends Container {

    protected WoodenFishTrapContainer(@Nullable ContainerType<?> type, int i) {
        super(type, i);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
