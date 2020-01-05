package net.nerds.fishtraps.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapContainer;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapRenderer;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapContainer;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapRenderer;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapRenderer;
import net.nerds.fishtraps.config.FishTrapValues;

public class FishTrapEntityManager {

    public static BlockEntityType<WoodenFishTrapBlockEntity> WOODEN_FISH_TRAP_ENTITY;
    public static final Identifier WOODEN_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "wooden_fish_trap_container");

    public static BlockEntityType<IronFishTrapBlockEntity> IRON_FISH_TRAP_ENTITY;
    public static final Identifier IRON_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "iron_fish_trap_container");

    public static BlockEntityType<DiamondFishTrapBlockEntity> DIAMOND_FISH_TRAP_ENTITY;
    public static final Identifier DIAMOND_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "diamond_fish_trap_container");

    public static void init(){
        WOODEN_FISH_TRAP_ENTITY = BlockEntityType.Builder.create(() -> new WoodenFishTrapBlockEntity(
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.WOODEN_TIME),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.WOODEN_LURE),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.WOODEN_LUCK)
        ), FishTrapsManager.WOODEN_FISH_TRAP).build(null);

        IRON_FISH_TRAP_ENTITY = BlockEntityType.Builder.create(() -> new IronFishTrapBlockEntity(
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.IRON_TIME),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.IRON_LURE),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.IRON_LUCK)
        ), FishTrapsManager.IRON_FISH_TRAP).build(null);

        DIAMOND_FISH_TRAP_ENTITY = BlockEntityType.Builder.create(() -> new DiamondFishTrapBlockEntity(
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_TIME),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_LURE),
                Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.DIAMOND_LUCK)
        ), FishTrapsManager.DIAMOND_FISH_TRAP).build(null);

        Registry.register(Registry.BLOCK_ENTITY, WOODEN_FISH_TRAP_CONTAINER, WOODEN_FISH_TRAP_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY, IRON_FISH_TRAP_CONTAINER, IRON_FISH_TRAP_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY, DIAMOND_FISH_TRAP_CONTAINER, DIAMOND_FISH_TRAP_ENTITY);
    }

    public static void initGui() {
        ContainerProviderRegistry.INSTANCE.registerFactory(WOODEN_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new WoodenFishTrapContainer(syncid, player.inventory, (WoodenFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
        ContainerProviderRegistry.INSTANCE.registerFactory(IRON_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new IronFishTrapContainer(syncid, player.inventory, (IronFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
        ContainerProviderRegistry.INSTANCE.registerFactory(DIAMOND_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new DiamondFishTrapContainer(syncid, player.inventory, (DiamondFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerEntityRenderers() {
        BlockEntityRendererRegistry.INSTANCE.register(WOODEN_FISH_TRAP_ENTITY, WoodenFishTrapRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(IRON_FISH_TRAP_ENTITY, IronFishTrapRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(DIAMOND_FISH_TRAP_ENTITY, DiamondFishTrapRenderer::new);
    }
}
