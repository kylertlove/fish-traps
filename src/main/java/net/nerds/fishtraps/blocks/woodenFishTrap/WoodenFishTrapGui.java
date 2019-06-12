package net.nerds.fishtraps.blocks.woodenFishTrap;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapGui;

@Environment(EnvType.CLIENT)
public class WoodenFishTrapGui extends AbstractContainerScreen {

    public Identifier fishGui = new Identifier(Fishtraps.MODID, "textures/gui/fish_trap_gui1.png");
    public BaseFishTrapBlockEntity tile;
    private String containerLabel = "";
    private final int rows;

    public WoodenFishTrapGui(WoodenFishTrapBlockEntity fishTrapBlockEntity, WoodenFishTrapContainer fishTrapContainer, String containerLabel, String textComponent) {
        super(fishTrapContainer, fishTrapContainer.playerInventory, new TextComponent(textComponent));
        this.tile = fishTrapBlockEntity;
        this.rows = 5;
        this.containerHeight = 133 + this.rows * 18;
        this.containerLabel = containerLabel;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(int var1, int var2, float var3) {
        this.renderBackground();
        super.render(var1, var2, var3);
        this.drawMouseoverTooltip(var1, var2);
    }

    @Override
    public void drawForeground(int int_1, int int_2) {
        this.font.draw(this.containerLabel, 8.0F, 6.0F, 4210752);
        this.font.draw(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.containerHeight - 96 + 2), 4210752);
    }

    @Override
    public void drawBackground(float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(fishGui);
        int w = (this.width - this.containerWidth) / 2;
        int h = (this.height - this.containerHeight) / 2;
        this.blit(w, h, 0, 0, this.containerWidth, this.containerHeight);
 //       this.blit(w, h + this.rows * 18 + 17, 0, 126, this.containerWidth, 96);
    }
}
