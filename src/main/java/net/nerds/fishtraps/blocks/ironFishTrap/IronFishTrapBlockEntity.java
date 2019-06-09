package net.nerds.fishtraps.blocks.ironFishTrap;

import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlockEntity;

public class IronFishTrapBlockEntity extends BaseFishTrapBlockEntity {

    public IronFishTrapBlockEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapEntityManager.IRON_FISH_TRAP_ENTITY, fishDelay, lureLevel, luckOfTheSeaLevel);
    }
}
