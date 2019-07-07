package net.nerds.fishtraps.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class FishTrapsManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public static WoodenFishTrap woodenFishTrap = new WoodenFishTrap();
    public static TileEntityType<WoodenFishTrapTileEntity> woodenFishTrapEntityType;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().register(woodenFishTrap);
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent){
        itemRegistryEvent.getRegistry()
                .register(new BlockItem(woodenFishTrap, new Item.Properties()
                        .group(ItemGroup.BUILDING_BLOCKS))
                        .setRegistryName(Objects.requireNonNull(woodenFishTrap.getRegistryName())));
    }
}
