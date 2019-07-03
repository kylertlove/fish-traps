package net.nerds.fishtraps.blocks.baseTrap;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class BaseFishTrapBlock extends Block implements BlockEntityProvider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public BaseFishTrapBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(WATERLOGGED, true));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        throw new Error("Override this, dumbass");
    }

    @Override
    public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        if (blockState_1.getBlock() != blockState_2.getBlock()) {
            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 instanceof Inventory) {
                ItemScatterer.spawn(world_1, blockPos_1, (Inventory)blockEntity_1);
                world_1.updateHorizontalAdjacent(blockPos_1, this);
            }
            super.onBlockRemoved(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
        }
    }

    @Override
    public boolean onBlockAction(BlockState blockState_1, World world_1, BlockPos blockPos_1, int int_1, int int_2) {
        super.onBlockAction(blockState_1, world_1, blockPos_1, int_1, int_2);
        BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
        return blockEntity_1 != null && blockEntity_1.onBlockAction(int_1, int_2);
    }

    /**
     * Need to override because it is returning air on block break
     * @return
     */
    @Override
    public Identifier getDropTableId() {
        Identifier identifier = Registry.BLOCK.getId(this);
        return new Identifier(identifier.getNamespace(), "blocks/" + identifier.getPath());
    }

    /**
     * Vanilla bug with the rendering water on sides:
     * https://bugs.mojang.com/browse/MC-125351?page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel&showAll=true
     * @return
     */
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState blockState_1) {
        return (Boolean)blockState_1.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState_1);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
        FluidState fluidState = itemPlacementContext.getWorld().getFluidState(itemPlacementContext.getBlockPos());
        boolean waterLog = fluidState.matches(FluidTags.WATER) && fluidState.getLevel() == 8;
        return super.getPlacementState(itemPlacementContext).with(WATERLOGGED, waterLog);
    }
}
