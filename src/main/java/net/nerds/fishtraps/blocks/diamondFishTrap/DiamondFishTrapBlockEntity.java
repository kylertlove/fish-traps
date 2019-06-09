package net.nerds.fishtraps.blocks.diamondFishTrap;

import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlockEntity;

public class DiamondFishTrapBlockEntity extends BaseFishTrapBlockEntity {
    public DiamondFishTrapBlockEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapEntityManager.DIAMOND_FISH_TRAP_ENTITY, fishDelay, lureLevel, luckOfTheSeaLevel);
    }
}
