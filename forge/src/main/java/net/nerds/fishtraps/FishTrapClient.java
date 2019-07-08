package net.nerds.fishtraps;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nerds.fishtraps.blocks.FishTrapsManager;
import net.nerds.fishtraps.blocks.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.WoodenFishTrapGui;
import net.nerds.fishtraps.util.FishTrapNames;

@Mod.EventBusSubscriber(modid = Fishtraps.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FishTrapClient {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event)
    {
        ScreenManager.registerFactory(FishTrapsManager.woodenFishTrapContainerContainerType, WoodenFishTrapGui::new);
    }

}
