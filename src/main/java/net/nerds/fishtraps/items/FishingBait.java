package net.nerds.fishtraps.items;

import net.minecraft.item.Item;
import net.nerds.fishtraps.Fishtraps;

public class FishingBait extends Item {
    public FishingBait() {
        super(new Item.Settings().itemGroup(Fishtraps.fishTraps).durability(600));
    }
}
