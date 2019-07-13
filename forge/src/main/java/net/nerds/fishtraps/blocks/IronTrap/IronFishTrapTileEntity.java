package net.nerds.fishtraps.blocks.IronTrap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapContainer;
import net.nerds.fishtraps.util.FishTrapNames;

import javax.annotation.Nullable;

public class IronFishTrapTileEntity extends BaseFishTrapTileEntity {

    public IronFishTrapTileEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapNames.ironFishTrapEntityType, fishDelay, lureLevel, luckOfTheSeaLevel);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.fishtraps.iron_fish_trap");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new IronFishTrapContainer(i, playerInventory, this.getInventory());
    }
}
