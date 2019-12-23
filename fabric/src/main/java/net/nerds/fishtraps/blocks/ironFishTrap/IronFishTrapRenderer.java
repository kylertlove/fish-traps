package net.nerds.fishtraps.blocks.ironFishTrap;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.nerds.fishtraps.blocks.FishTrapsManager;

public class IronFishTrapRenderer extends BlockEntityRenderer<IronFishTrapBlockEntity> {

    private IronFishTrapBlockEntity ironFishTrapBlockEntity;

    public IronFishTrapRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher_1) {
        super(blockEntityRenderDispatcher_1);
    }

    @Override
    public void render(IronFishTrapBlockEntity blockEntity, float v, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int i1) {
        ironFishTrapBlockEntity = blockEntity;
    }

    public void method_3590(double double_1, double double_2, double double_3, float float_1, int int_1) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)double_1 + 0.5F, (float)double_2, (float)double_3 + 0.5F);
        if(ironFishTrapBlockEntity.showFishBait()) {
            renderEntity(float_1);
        }
        GlStateManager.popMatrix();
    }

    public static void renderEntity(float float_1) {
        ItemStack bait = new ItemStack(FishTrapsManager.FISH_BAIT);
        float float_2 = 0.8125F;
        GlStateManager.translatef(0.0F, 0.4F, 0.0F);
        GlStateManager.rotatef((float) MathHelper.lerp((double)float_1, 3, 3) * 10.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -0.2F, 0.0F);
        GlStateManager.rotatef(-30.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scalef(float_2, float_2, float_2);
       // MinecraftClient.getInstance().getItemRenderer().renderItem(bait, ModelTransformation.Type.NONE);
    }
}
