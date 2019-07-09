package net.nerds.fishtraps;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapGui;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapGui;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapGui;
import net.nerds.fishtraps.util.FishTrapNames;

@Mod.EventBusSubscriber(modid = Fishtraps.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FishTrapClient {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event)
    {
        ScreenManager.registerFactory(FishTrapNames.woodenFishTrapContainerContainerType, WoodenFishTrapGui::new);
        ScreenManager.registerFactory(FishTrapNames.ironFishTrapContainerContainerType, IronFishTrapGui::new);
        ScreenManager.registerFactory(FishTrapNames.diamondFishTrapContainerContainerType, DiamondFishTrapGui::new);
    }

}
