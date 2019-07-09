package net.nerds.fishtraps.util;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapContainer;
import net.nerds.fishtraps.blocks.DiamondTrap.DiamondFishTrapTileEntity;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapContainer;
import net.nerds.fishtraps.blocks.IronTrap.IronFishTrapTileEntity;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.WoodenTrap.WoodenFishTrapTileEntity;

public class FishTrapNames {

    public static final String WOODEN_FISH_TRAP_ENTITY_TYPE = Fishtraps.MODID + ":wooden_fish_trap";
    public static final String WOODEN_FISH_TRAP_CONTAINER_TYPE = Fishtraps.MODID + ":wooden_fish_trap";
    public static final String IRON_FISH_TRAP_ENTITY_TYPE = Fishtraps.MODID + ":iron_fish_trap";
    public static final String IRON_FISH_TRAP_CONTAINER_TYPE = Fishtraps.MODID + ":iron_fish_trap";
    public static final String DIAMOND_FISH_TRAP_ENTITY_TYPE = Fishtraps.MODID + ":diamond_fish_trap";
    public static final String DIAMOND_FISH_TRAP_CONTAINER_TYPE = Fishtraps.MODID + ":diamond_fish_trap";

    @ObjectHolder(WOODEN_FISH_TRAP_ENTITY_TYPE)
    public static TileEntityType<WoodenFishTrapTileEntity> woodenFishTrapEntityType;

    @ObjectHolder(WOODEN_FISH_TRAP_CONTAINER_TYPE)
    public static ContainerType<WoodenFishTrapContainer> woodenFishTrapContainerContainerType;

    @ObjectHolder(IRON_FISH_TRAP_ENTITY_TYPE)
    public static TileEntityType<IronFishTrapTileEntity> ironFishTrapEntityType;

    @ObjectHolder(IRON_FISH_TRAP_CONTAINER_TYPE)
    public static ContainerType<IronFishTrapContainer> ironFishTrapContainerContainerType;

    @ObjectHolder(DIAMOND_FISH_TRAP_ENTITY_TYPE)
    public static TileEntityType<DiamondFishTrapTileEntity> diamondFishTrapEntityType;

    @ObjectHolder(DIAMOND_FISH_TRAP_CONTAINER_TYPE)
    public static ContainerType<DiamondFishTrapContainer> diamondFishTrapContainerContainerType;
}
