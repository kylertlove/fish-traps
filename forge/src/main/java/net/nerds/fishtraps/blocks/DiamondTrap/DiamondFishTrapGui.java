package net.nerds.fishtraps.blocks.DiamondTrap;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nerds.fishtraps.Fishtraps;

@OnlyIn(Dist.CLIENT)
public class DiamondFishTrapGui extends ContainerScreen<DiamondFishTrapContainer> implements IHasContainer<DiamondFishTrapContainer> {

    private static final ResourceLocation trapTexture = new ResourceLocation(Fishtraps.MODID, "textures/gui/fish_trap_gui1.png");
    private int rows = 5;

    public DiamondFishTrapGui(DiamondFishTrapContainer container, PlayerInventory playerInventory, ITextComponent iTextComponent) {
        super(container, playerInventory, iTextComponent);
        this.ySize = 133 + this.rows * 18;
        this.xSize = 176;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.font.func_238422_b_(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(trapTexture);
        blit(matrixStack, (width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}