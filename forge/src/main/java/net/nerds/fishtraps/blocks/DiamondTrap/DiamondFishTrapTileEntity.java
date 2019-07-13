package net.nerds.fishtraps.blocks.DiamondTrap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.util.FishTrapNames;

import javax.annotation.Nullable;

public class DiamondFishTrapTileEntity extends BaseFishTrapTileEntity {

    public DiamondFishTrapTileEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapNames.diamondFishTrapEntityType, fishDelay, lureLevel, luckOfTheSeaLevel);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.fishtraps.diamond_fish_trap");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new DiamondFishTrapContainer(i, playerInventory, this.getInventory());
    }
}
