package net.nerds.fishtraps;

import net.nerds.fishtraps.blocks.WoodenFishTrap;
import net.nerds.fishtraps.blocks.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.WoodenFishTrapGui;
import net.nerds.fishtraps.blocks.WoodenFishTrapTileEntity;

public enum FishTrapTypes {
    WOODEN("wooden_fish_trap"),
    IRON("iron_fish_trap"),
    DIAMOND("diamond_fish_trap");

    private WoodenFishTrap fishTrapBlock;
    private WoodenFishTrapContainer fishTrapContainer;
    private WoodenFishTrapGui fishTrapGui;
    private WoodenFishTrapTileEntity fishTrapTileEntity;

    FishTrapTypes(String name) {

    }

    public WoodenFishTrap getFishTrapBlock() {
        return fishTrapBlock;
    }

    public WoodenFishTrapContainer getFishTrapContainer() {
        return fishTrapContainer;
    }

    public WoodenFishTrapGui getFishTrapGui() {
        return fishTrapGui;
    }

    public WoodenFishTrapTileEntity getFishTrapTileEntity() {
        return fishTrapTileEntity;
    }
}
