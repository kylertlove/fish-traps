package net.nerds.fishtraps.blocks.ironFishTrap;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlock;

public class IronFishTrapBlock extends BaseFishTrapBlock {

    public IronFishTrapBlock() {
        super(FabricBlockSettings.of(Material.METAL).hardness(3.5f).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new IronFishTrapBlockEntity(250, 2, 2);
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(FishTrapEntityManager.IRON_FISH_TRAP_CONTAINER, player, buf -> buf.writeBlockPos(blockPos));
        }
        return true;
    }
}
