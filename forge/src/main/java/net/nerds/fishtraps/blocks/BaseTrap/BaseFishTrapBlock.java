package net.nerds.fishtraps.blocks.BaseTrap;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.nerds.fishtraps.Fishtraps;

public abstract class BaseFishTrapBlock extends ContainerBlock implements IWaterLoggable {

    public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BaseFishTrapBlock(String name) {
        super(Block.Properties.from(Blocks.OAK_LOG));
        this.setRegistryName(Fishtraps.MODID, name);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.TRUE));
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos blockPos, PlayerEntity entity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if(!world.isRemote) {
            INamedContainerProvider containerProvider = getContainer(state, world, blockPos);
            if(containerProvider != null)
                entity.openContainer(containerProvider);
        }
        return ActionResultType.PASS;
    }

    @Override
    public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(WATERLOGGED, ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8);
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof BaseFishTrapTileEntity) {
                InventoryHelper.dropInventoryItems(worldIn, pos, ((BaseFishTrapTileEntity) tileentity).getInventory());
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
