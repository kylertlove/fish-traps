package net.nerds.fishtraps.items;

import net.minecraft.item.Item;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.config.FishTrapValues;

public class FishingBait extends Item {
    public FishingBait() {
        super(new Item.Settings().itemGroup(Fishtraps.fishTraps).durability(Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.FISH_BAIT_DURABILITY)));
    }
}
