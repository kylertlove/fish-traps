package net.nerds.fishtraps.blocks.diamondFishTrap;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlock;
import net.nerds.fishtraps.config.FishTrapValues;

public class DiamondFishTrapBlock extends BaseFishTrapBlock {

    public DiamondFishTrapBlock() {
        super(FabricBlockSettings.of(Material.METAL).hardness(3.5f).nonOpaque().build());
    }
    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new DiamondFishTrapBlockEntity(
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_TIME),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_LURE),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_LUCK));
    }

    @Override
    public ActionResult onUse(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (!world_1.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(FishTrapEntityManager.DIAMOND_FISH_TRAP_CONTAINER, playerEntity_1, buf -> buf.writeBlockPos(blockPos_1));
        }
        return ActionResult.PASS;
    }
}
