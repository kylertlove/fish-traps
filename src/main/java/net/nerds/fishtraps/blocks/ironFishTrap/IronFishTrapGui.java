package net.nerds.fishtraps.blocks.ironFishTrap;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapGui;

@Environment(EnvType.CLIENT)
public class IronFishTrapGui extends BaseFishTrapGui {

    public IronFishTrapGui(BaseFishTrapBlockEntity fishTrapBlockEntity, BaseFishTrapContainer fishTrapContainer, String containerLabel, String textComponent) {
        super(fishTrapBlockEntity, fishTrapContainer, containerLabel, textComponent);
    }
}
