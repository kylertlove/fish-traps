package net.nerds.fishtraps.blocks.woodenFishTrap;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.MobSpawnerLogic;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.FishTrapsManager;

@Environment(EnvType.CLIENT)
public class WoodenFishTrapRenderer extends BlockEntityRenderer<WoodenFishTrapBlockEntity> {

    private WoodenFishTrapBlockEntity woodenFishTrapBlockEntity;

    public WoodenFishTrapRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(WoodenFishTrapBlockEntity blockEntity, float delta, MatrixStack stack, VertexConsumerProvider vcp, int x, int y) {
        //super.render(blockEntity, x, y, z, float_1, int_1);
        woodenFishTrapBlockEntity = blockEntity;
        stack.push();
        stack.translate(0.5D, 0.0D, 0.5D);
        //MobSpawnerLogic mobSpawnerLogic = blockEntity.getLogic();
        //Entity entity = new ItemEntity(blockEntity.getWorld(), x, y, );
//        if (entity != null) {
            float g = 0.53125F;
//            float h = Math.max(entity.getWidth(), entity.getHeight());
//            if ((double)h > 1.0D) {
//                g /= h;
//            }

            stack.translate(0.0D, 0.4000000059604645D, 0.0D);
            stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(y));
            stack.translate(0.0D, -0.20000000298023224D, 0.0D);
            stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-30.0F));
            stack.scale(g, g, g);
           // MinecraftClient.getInstance().getEntityRenderManager().render(entity, 0.0D, 0.0D, 0.0D, 0.0F, f, stack, vcp, x);
        //}

        stack.pop();
    }

//    @Override
//    public boolean method_3563(WoodenFishTrapBlockEntity blockEntity_1) {
//        return true;
//    }


//    public void method_3590(double double_1, double double_2, double double_3, float float_1, int int_1) {
//        GlStateManager.pushMatrix();
//        GlStateManager.translatef((float)double_1 + 0.5F, (float)double_2, (float)double_3 + 0.5F);
//        if(woodenFishTrapBlockEntity.showFishBait()) {
//            renderEntity(float_1);
//        }
//        GlStateManager.popMatrix();
//    }
//
//    public static void renderEntity(float float_1) {
//        ItemStack bait = new ItemStack(FishTrapsManager.FISH_BAIT);
//            float float_2 = 0.8125F;
//            GlStateManager.translatef(0.0F, 0.4F, 0.0F);
//            GlStateManager.rotatef((float) MathHelper.lerp((double)float_1, 3, 3) * 10.0F, 0.0F, 1.0F, 0.0F);
//            GlStateManager.translatef(0.0F, -0.2F, 0.0F);
//            GlStateManager.rotatef(-30.0F, 1.0F, 0.0F, 0.0F);
//            GlStateManager.scalef(float_2, float_2, float_2);
//            MinecraftClient.getInstance().getItemRenderer().renderItem(bait, ModelTransformation.Type.NONE);
//    }

}
