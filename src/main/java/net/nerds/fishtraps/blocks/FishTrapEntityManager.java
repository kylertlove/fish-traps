package net.nerds.fishtraps.blocks;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapBlockEntity;

import java.util.function.Supplier;

public class FishTrapEntityManager {

    public static BlockEntityType WOODEN_FISH_TRAP_ENTITY;
    public static final Identifier WOODEN_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "wooden_fish_trap_container");

    public static BlockEntityType IRON_FISH_TRAP_ENTITY;
    public static final Identifier IRON_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "iron_fish_trap_container");

    public static BlockEntityType DIAMOND_FISH_TRAP_ENTITY;
    public static final Identifier DIAMOND_FISH_TRAP_CONTAINER = new Identifier(Fishtraps.MODID, "diamond_fish_trap_container");


    public static void init(){
        WOODEN_FISH_TRAP_ENTITY = registerTile(new Identifier(Fishtraps.MODID, "wooden_fish_trap"), WoodenFishTrapBlockEntity.class, FishTrapsManager.WOODEN_FISH_TRAP);
        IRON_FISH_TRAP_ENTITY = registerTile(new Identifier(Fishtraps.MODID, "iron_fish_trap"), IronFishTrapBlockEntity.class, FishTrapsManager.IRON_FISH_TRAP);
        DIAMOND_FISH_TRAP_ENTITY = registerTile(new Identifier(Fishtraps.MODID, "diamond_fish_trap"), DiamondFishTrapBlockEntity.class, FishTrapsManager.DIAMOND_FISH_TRAP);
    }

    public static void initGui() {
        ContainerProviderRegistry.INSTANCE.registerFactory(WOODEN_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new BaseFishTrapContainer(syncid, player.inventory, (WoodenFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });

        ContainerProviderRegistry.INSTANCE.registerFactory(IRON_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new BaseFishTrapContainer(syncid, player.inventory, (IronFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });

        ContainerProviderRegistry.INSTANCE.registerFactory(DIAMOND_FISH_TRAP_CONTAINER, (syncid, identifier, player, buf) -> {
            return new BaseFishTrapContainer(syncid, player.inventory, (DiamondFishTrapBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
    }



    public static BlockEntityType registerTile(Identifier identifier, Class<? extends BlockEntity> blockEntity, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY, identifier, BlockEntityType.Builder.create((Supplier<BlockEntity>) () -> {
            try {
                return blockEntity.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        }, blocks).build(null));
    }
}
