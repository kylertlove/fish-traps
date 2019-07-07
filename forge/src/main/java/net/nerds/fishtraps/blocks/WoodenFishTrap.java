package net.nerds.fishtraps.blocks;

import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.IBlockReader;
import net.nerds.fishtraps.Fishtraps;

public class WoodenFishTrap extends Block implements IWaterLoggable {

    private static String name = "wooden_fish_trap";

    public WoodenFishTrap() {
        super(Block.Properties.from(Blocks.OAK_LOG));
        this.setRegistryName(Fishtraps.MODID, name);
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return new WoodenFishTrapTileEntity(FishTrapsManager.woodenFishTrapEntityType);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
