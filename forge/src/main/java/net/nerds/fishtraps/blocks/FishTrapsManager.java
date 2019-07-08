package net.nerds.fishtraps.blocks;

import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.nerds.fishtraps.util.FishTrapNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class FishTrapsManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public static WoodenFishTrap woodenFishTrap = new WoodenFishTrap();
    public static TileEntityType<WoodenFishTrapTileEntity> woodenFishTrapEntityType;

    @ObjectHolder(FishTrapNames.WOODEN_FISH_TRAP_CONTAINER_TYPE)
    public static ContainerType<WoodenFishTrapContainer> woodenFishTrapContainerContainerType;


    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        System.out.println("----------------------------Registering Blocks--------------------------");
        blockRegistryEvent.getRegistry().register(woodenFishTrap);
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent){
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(woodenFishTrap, new Item.Properties()
                        .group(ItemGroup.BUILDING_BLOCKS))
                        .setRegistryName(Objects.requireNonNull(woodenFishTrap.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event)
    {
        event.getRegistry().register(new ContainerType<WoodenFishTrapContainer>((windowId, playerInv) -> {
            return new WoodenFishTrapContainer(windowId, playerInv, new WoodenFishTrapTileEntity(woodenFishTrapEntityType));
        }).setRegistryName(FishTrapNames.WOODEN_FISH_TRAP_CONTAINER_TYPE));
    }
}
