package net.nerds.fishtraps.items;

import net.minecraft.item.Item;
import net.nerds.fishtraps.FishTrapsManager;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.util.FishTrapsConfig;

public class FishBait extends Item {
    public FishBait() {
        super(new Item.Properties().defaultMaxDamage(FishTrapsConfig.FISH_TRAPS_CONFIG.fishBaitDurability.get()).group(FishTrapsManager.fishTrapItemGroup));
        this.setRegistryName(Fishtraps.MODID, "fish_trap_bait");
    }
}
