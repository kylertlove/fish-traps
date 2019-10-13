package net.nerds.fishtraps.blocks.WoodenTrap;

import net.minecraft.util.text.ITextComponent;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.util.FishTrapNames;

import javax.annotation.Nullable;

public class WoodenFishTrapTileEntity extends BaseFishTrapTileEntity {

    public WoodenFishTrapTileEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapNames.woodenFishTrapEntityType, fishDelay, lureLevel, luckOfTheSeaLevel);
    }

}
