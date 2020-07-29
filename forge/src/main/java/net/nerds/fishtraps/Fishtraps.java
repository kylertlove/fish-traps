package net.nerds.fishtraps;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nerds.fishtraps.util.FishTrapsConfig;

@Mod("fishtraps")
public class Fishtraps {

    public static final String MODID = "fishtraps";

    public Fishtraps() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FishTrapsConfig.FORGE_CONFIG_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
