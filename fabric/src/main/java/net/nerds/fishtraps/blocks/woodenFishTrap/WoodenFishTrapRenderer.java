package net.nerds.fishtraps.blocks.woodenFishTrap;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.nerds.fishtraps.blocks.FishTrapsManager;

@Environment(EnvType.CLIENT)
public class WoodenFishTrapRenderer extends BlockEntityRenderer<WoodenFishTrapBlockEntity> {

    private ItemStack fishBait;
    public WoodenFishTrapRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
        fishBait = new ItemStack(FishTrapsManager.FISH_BAIT);
    }

    @Override
    public void render(WoodenFishTrapBlockEntity blockEntity, float delta, MatrixStack stack, VertexConsumerProvider vcp, int x, int y) {
        if(blockEntity.showFishBait()) {
            stack.push();
            stack.translate(0.5D, 0.3D, 0.5D);
            float g = 0.83125F;
            stack.translate(0.0D, 0.4000000059604645D, 0.0D);
            stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(9.0f));
            stack.translate(0.0D, -0.20000000298023224D, 0.0D);
            stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-30.0F));
            stack.scale(g, g, g);
            MinecraftClient.getInstance().getItemRenderer().renderItem(fishBait, ModelTransformation.Mode.NONE, x, 0, stack, vcp);
            stack.pop();
        }
    }
}
