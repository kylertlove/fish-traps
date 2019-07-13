package net.nerds.fishtraps.blocks.DiamondTrap;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.util.FishTrapsConfig;

import javax.annotation.Nullable;

public class DiamondFishTrap extends BaseFishTrapBlock {

    private static String name = "diamond_fish_trap";

    public DiamondFishTrap() {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DiamondFishTrapTileEntity(
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapBaseTime.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLureLevel.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLuckLevel.get());
    }
}
