package net.nerds.fishtraps;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.nerds.fishtraps.blocks.FishTrapsManager;
import net.nerds.fishtraps.config.FishTrapValues;
import net.nerds.fishtraps.config.FishTrapsConfig;

public class Fishtraps implements ModInitializer {

    public static final String MODID = "fishtraps";
    public static ItemGroup fishTraps = FabricItemGroupBuilder
            .build(new Identifier(MODID, MODID), () -> new ItemStack(Items.FISHING_ROD));
    public static FishTrapsConfig fishTrapsConfig;

    @Override
    public void onInitialize() {
       fishTrapsConfig = new FishTrapsConfig();
       fishTrapsConfig.loadConfig();
       FishTrapsManager.init();
    }
}
