package net.nerds.fishtraps.blocks.IronTrap;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapTileEntity;
import net.nerds.fishtraps.util.FishTrapsConfig;

import javax.annotation.Nullable;

public class IronFishTrap extends BaseFishTrapBlock {

    private static String name = "iron_fish_trap";

    public IronFishTrap() {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new IronFishTrapTileEntity(
                FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapBaseTime.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLureLevel.get(),
                FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLuckLevel.get());
    }
}
