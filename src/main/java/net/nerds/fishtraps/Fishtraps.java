package net.nerds.fishtraps;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.nerds.fishtraps.blocks.FishTrapsManager;

public class Fishtraps implements ModInitializer {

    public static final String MODID = "fishtraps";
    public static ItemGroup fishTraps = FabricItemGroupBuilder
            .build(new Identifier(MODID, MODID), () -> new ItemStack(Items.FISHING_ROD));

    @Override
    public void onInitialize() {
       FishTrapsManager.init();
    }
}
