package net.nerds.fishtraps.blocks.woodenFishTrap;

import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlockEntity;

public class WoodenFishTrapBlockEntity extends BaseFishTrapBlockEntity {

    public WoodenFishTrapBlockEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapEntityManager.WOODEN_FISH_TRAP_ENTITY, fishDelay, lureLevel, luckOfTheSeaLevel);
    }
}
