package net.nerds.fishtraps.blocks.WoodenTrap;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.nerds.fishtraps.blocks.BaseTrap.BaseFishTrapTileEntity;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.FishTrapNames;

import javax.annotation.Nullable;

public class WoodenFishTrapTileEntity extends BaseFishTrapTileEntity {

    public WoodenFishTrapTileEntity(int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(FishTrapNames.woodenFishTrapEntityType, fishDelay, lureLevel, luckOfTheSeaLevel);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.fishtraps.wooden_fish_trap");
    }

    @Override
    protected Container createMenu(int i, PlayerInventory playerInventory) {
        return new WoodenFishTrapContainer(i, playerInventory, this);
    }

}
