package net.nerds.fishtraps.blocks.woodenFishTrap;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapGui;

@Environment(EnvType.CLIENT)
public class WoodenFishTrapGui extends BaseFishTrapGui {

    public WoodenFishTrapGui(WoodenFishTrapBlockEntity fishTrapBlockEntity, WoodenFishTrapContainer fishTrapContainer, String containerLabel, String textComponent) {
        super(fishTrapBlockEntity, fishTrapContainer, containerLabel, textComponent);
    }

}
