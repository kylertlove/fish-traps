package net.nerds.fishtraps.blocks.WoodenTrap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapTileEntity;
import net.nerds.fishtraps.util.FishTrapsConfig;

import javax.annotation.Nullable;

public class WoodenFishTrap extends BaseFishTrapBlock {

    private static String name = "wooden_fish_trap";

    public WoodenFishTrap(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return WoodenFishTrapTileEntity();
    }
}
