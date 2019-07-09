package net.nerds.fishtraps.blocks.IronTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.util.FishTrapNames;

public class IronFishTrapTileEntity extends BaseFishTrapTileEntity {

    public IronFishTrapTileEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapNames.ironFishTrapEntityType, fishDelay, lureLevel, luckOfTheSeaLevel);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.fishtraps.iron_fish_trap");
    }

    @Override
    protected Container createMenu(int i, PlayerInventory playerInventory) {
        return new IronFishTrapContainer(i, playerInventory, this);
    }
}
