package net.nerds.fishtraps.blocks.WoodenTrap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.util.FishTrapsConfig;

import javax.annotation.Nullable;

public class WoodenFishTrap extends BaseFishTrapBlock {

    private static String name = "wooden_fish_trap";

    public WoodenFishTrap() {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new WoodenFishTrapTileEntity(
                FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapBaseTime.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLureLevel.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLuckLevel.get());
    }
}
