package net.nerds.fishtraps;

import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.nerds.fishtraps.blocks.WoodenFishTrap;
import net.nerds.fishtraps.blocks.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.WoodenFishTrapTileEntity;
import net.nerds.fishtraps.items.FishBait;
import net.nerds.fishtraps.util.FishTrapNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class FishTrapsManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public static WoodenFishTrap woodenFishTrap = new WoodenFishTrap();

    @ObjectHolder(FishTrapNames.WOODEN_FISH_TRAP_ENTITY_TYPE)
    public static TileEntityType<WoodenFishTrapTileEntity> woodenFishTrapEntityType;

    @ObjectHolder(FishTrapNames.WOODEN_FISH_TRAP_CONTAINER_TYPE)
    public static ContainerType<WoodenFishTrapContainer> woodenFishTrapContainerContainerType;

    public static ItemGroup fishTrapItemGroup = new ItemGroup("fishtraps") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.FISHING_ROD);
        }
    };


    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().register(woodenFishTrap);
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent){
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(woodenFishTrap, new Item.Properties()
                        .group(fishTrapItemGroup))
                        .setRegistryName(Objects.requireNonNull(woodenFishTrap.getRegistryName())));
        itemRegistryEvent.getRegistry().register(new FishBait());
    }

    @SubscribeEvent
    public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event)
    {
        event.getRegistry().register(TileEntityType.Builder.create(WoodenFishTrapTileEntity::new, woodenFishTrap)
                .build(null).setRegistryName(woodenFishTrap.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event)
    {
        event.getRegistry().register(new ContainerType<WoodenFishTrapContainer>((windowId, playerInv) -> {
            return new WoodenFishTrapContainer(windowId, playerInv, new WoodenFishTrapTileEntity());
        }).setRegistryName(FishTrapNames.WOODEN_FISH_TRAP_CONTAINER_TYPE));
    }
}
