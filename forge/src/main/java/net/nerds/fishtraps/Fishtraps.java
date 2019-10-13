package net.nerds.fishtraps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrap;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapTileEntity;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrap;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapTileEntity;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrap;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapTileEntity;

@Mod(modid = Fishtraps.MODID)
public class Fishtraps {

    public static final String MODID = "fishtraps";
    public static WoodenFishTrap woodenFishTrap = new WoodenFishTrap();
    public static IronFishTrap ironFishTrap = new IronFishTrap();
    public static DiamondFishTrap diamondFishTrap = new DiamondFishTrap();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        MinecraftForge.EVENT_BUS.register(this);
//        GameRegistry.registerTileEntity(WoodenFishTrapTileEntity.class, new ResourceLocation(Fishtraps.MODID, "wooden_fish_trap"));
//        GameRegistry.registerTileEntity(IronFishTrapTileEntity.class, new ResourceLocation(Fishtraps.MODID, "iron_fish_trap"));
//        GameRegistry.registerTileEntity(DiamondFishTrapTileEntity.class, new ResourceLocation(Fishtraps.MODID, "diamond_fish_trap"));
    }

    @SubscribeEvent
    public void blockBois(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(woodenFishTrap);
        e.getRegistry().register(ironFishTrap);
        e.getRegistry().register(diamondFishTrap);
    }

    @SubscribeEvent
    public void items(RegistryEvent.Register<Item> e) {
       // e.getRegistry().register(new ItemBlock());
    }

}
