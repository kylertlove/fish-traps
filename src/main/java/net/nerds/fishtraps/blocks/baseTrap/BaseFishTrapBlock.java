package net.nerds.fishtraps.blocks.baseTrap;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class BaseFishTrapBlock extends Block implements BlockEntityProvider {


    public BaseFishTrapBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        throw new Error("Override this, dumbass");
    }


    @Override
    public BlockRenderType getRenderType(BlockState var1) {
        return BlockRenderType.MODEL;
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
        return blockEntity_1 == null ? false : blockEntity_1.onBlockAction(int_1, int_2);
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

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
