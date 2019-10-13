package net.nerds.fishtraps;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrap;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapContainer;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapTileEntity;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrap;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapContainer;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapTileEntity;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrap;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapTileEntity;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.FishTrapNames;
import net.nerds.fishtraps.util.FishTrapsConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;

public class FishTrapsManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public static WoodenFishTrap woodenFishTrap = new WoodenFishTrap();
    public static IronFishTrap ironFishTrap = new IronFishTrap();
    public static DiamondFishTrap diamondFishTrap = new DiamondFishTrap();

    public static ItemGroup fishTrapItemGroup = new ItemGroup("fishtraps.fishtraps") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.FISHING_ROD);
        }
    };

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().register(woodenFishTrap);
        blockRegistryEvent.getRegistry().register(ironFishTrap);
        blockRegistryEvent.getRegistry().register(diamondFishTrap);
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent){
        itemRegistryEvent.getRegistry().register(new FishBait());
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(woodenFishTrap, new Item.Properties()
                        .group(fishTrapItemGroup))
                        .setRegistryName(Objects.requireNonNull(woodenFishTrap.getRegistryName())));
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(ironFishTrap, new Item.Properties()
                        .group(fishTrapItemGroup))
                        .setRegistryName(Objects.requireNonNull(ironFishTrap.getRegistryName())));
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(diamondFishTrap, new Item.Properties()
                        .group(fishTrapItemGroup))
                        .setRegistryName(Objects.requireNonNull(diamondFishTrap.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder
                .create((Supplier<? extends TileEntity>) () -> {
                    return new WoodenFishTrapTileEntity(
                            FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapBaseTime.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLureLevel.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLuckLevel.get());
                }, woodenFishTrap)
                .build(null).setRegistryName(Objects.requireNonNull(woodenFishTrap.getRegistryName())));
        event.getRegistry().register(TileEntityType.Builder
                .create((Supplier<? extends TileEntity>) () -> {
                    return new IronFishTrapTileEntity(
                            FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapBaseTime.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLureLevel.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLuckLevel.get());
                }, ironFishTrap)
                .build(null).setRegistryName(Objects.requireNonNull(ironFishTrap.getRegistryName())));
        event.getRegistry().register(TileEntityType.Builder
                .create((Supplier<? extends TileEntity>) () -> {
                    return new DiamondFishTrapTileEntity(
                            FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapBaseTime.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLureLevel.get(),
                            FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLuckLevel.get());
                }, diamondFishTrap)
                .build(null).setRegistryName(Objects.requireNonNull(diamondFishTrap.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().register(new ContainerType<WoodenFishTrapContainer>((windowId, playerInv) -> {
            return new WoodenFishTrapContainer(windowId, playerInv, new WoodenFishTrapTileEntity(
                    FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapBaseTime.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLureLevel.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.woodenTrapLuckLevel.get()).getInventory());
        }).setRegistryName(FishTrapNames.WOODEN_FISH_TRAP_CONTAINER_TYPE));
        event.getRegistry().register(new ContainerType<IronFishTrapContainer>((windowId, playerInv) -> {
            return new IronFishTrapContainer(windowId, playerInv, new IronFishTrapTileEntity(
                    FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapBaseTime.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLureLevel.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.ironTrapLuckLevel.get()).getInventory());
        }).setRegistryName(FishTrapNames.IRON_FISH_TRAP_CONTAINER_TYPE));
        event.getRegistry().register(new ContainerType<DiamondFishTrapContainer>((windowId, playerInv) -> {
            return new DiamondFishTrapContainer(windowId, playerInv, new DiamondFishTrapTileEntity(
                    FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapBaseTime.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLureLevel.get(),
                    FishTrapsConfig.FISH_TRAPS_CONFIG.diamondTrapLuckLevel.get()).getInventory());
        }).setRegistryName(FishTrapNames.DIAMOND_FISH_TRAP_CONTAINER_TYPE));
    }
}
