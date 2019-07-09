package net.nerds.fishtraps.blocks.WoodenTrap;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.nerds.fishtraps.Fishtraps;

public class WoodenFishTrapGui extends ContainerScreen<WoodenFishTrapContainer> implements IHasContainer<WoodenFishTrapContainer> {

    private static final ResourceLocation trapTexture = new ResourceLocation(Fishtraps.MODID, "textures/gui/fish_trap_gui1.png");
    private int rows = 5;

    public WoodenFishTrapGui(WoodenFishTrapContainer container, PlayerInventory playerInventory, ITextComponent iTextComponent) {
        super(container, playerInventory, iTextComponent);
        this.ySize = 133 + this.rows * 18;
        this.xSize = 176;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String name = new TranslationTextComponent("block.fishtraps.wooden_fish_trap").getString();
        font.drawString(name, 8, 6, 0x404040);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackground();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().getTextureManager().bindTexture(trapTexture);
        blit((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}