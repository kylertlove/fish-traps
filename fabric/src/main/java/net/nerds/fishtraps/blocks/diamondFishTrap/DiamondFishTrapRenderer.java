package net.nerds.fishtraps.blocks.diamondFishTrap;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.nerds.fishtraps.blocks.FishTrapsManager;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapBlockEntity;

public class DiamondFishTrapRenderer extends BlockEntityRenderer<DiamondFishTrapBlockEntity> {

    private ItemStack fishBait;

    public DiamondFishTrapRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher_1) {
        super(blockEntityRenderDispatcher_1);
        fishBait = new ItemStack(FishTrapsManager.FISH_BAIT);
    }

    @Override
    public void render(DiamondFishTrapBlockEntity blockEntity, float delta, MatrixStack stack, VertexConsumerProvider vcp, int x, int y) {

        if(blockEntity.showFishBait()) {
            stack.push();
            stack.translate(0.5D, 0.0D, 0.5D);
            ItemEntity bait = new ItemEntity(blockEntity.getWorld(), x, y, blockEntity.getPos().getZ(), fishBait);
            float g = 0.53125F;
            stack.translate(0.0D, 0.4000000059604645D, 0.0D);
            //stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(y));
            stack.translate(0.0D, -0.20000000298023224D, 0.0D);
            //stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-30.0F));
            stack.scale(g, g, g);
            MinecraftClient.getInstance().getEntityRenderManager().render(bait, 0.0D, 0.0D, 0.0D, 0.0F, 0, stack, vcp, x);
            stack.pop();
        }
    }
}
