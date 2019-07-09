package net.nerds.fishtraps.blocks.DiamondTrap;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.util.FishTrapsConfig;

public class DiamondFishTrap extends BaseFishTrapBlock {

    private static String name = "diamond_fish_trap";

    public DiamondFishTrap() {
        super(name);
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return new DiamondFishTrapTileEntity(
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapBaseTime.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLureLevel.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLuckLevel.get());
    }
}
