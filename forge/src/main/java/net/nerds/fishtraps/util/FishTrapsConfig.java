package net.nerds.fishtraps.util;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class FishTrapsConfig {

    public static final ForgeConfigSpec FORGE_CONFIG_SPEC;
    public static final FishTrapsConfig FISH_TRAPS_CONFIG;

    public final IntValue woodenTrapLureLevel;
    public final IntValue woodenTrapLuckLevel;
    public final IntValue woodenTrapBaseTime;
    public final IntValue ironTrapLureLevel;
    public final IntValue ironTrapLuckLevel;
    public final IntValue ironTrapBaseTime;
    public final IntValue diamondTrapLureLevel;
    public final IntValue diamondTrapLuckLevel;
    public final IntValue diamondTrapBaseTime;
    public final IntValue trapPenaltyMultiplier;
    public final IntValue fishBaitDurability;
    public final BooleanValue shouldTrapHavePenalty;

    static {
        Pair<FishTrapsConfig,ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(FishTrapsConfig::new);
        FORGE_CONFIG_SPEC = specPair.getRight();
        FISH_TRAPS_CONFIG = specPair.getLeft();
    }

    FishTrapsConfig(ForgeConfigSpec.Builder builder) {
        woodenTrapLureLevel = builder.comment("The Lure Level of the wooden trap").defineInRange("woodenTrapLureLevel", 1, 0, 10);
        woodenTrapLuckLevel = builder.comment("The Lure Level of the wooden trap").defineInRange("woodenTrapLuckLevel", 1, 0, 10);
        woodenTrapBaseTime = builder.comment("The Lure Level of the wooden trap").defineInRange("woodenTrapBaseTime", 900, 20, 2000000000);

        ironTrapLureLevel = builder.comment("The Lure Level of the Iron trap").defineInRange("ironTrapLureLevel", 2, 0, 10);
        ironTrapLuckLevel = builder.comment("The Lure Level of the Iron trap").defineInRange("ironTrapLuckLevel", 2, 0, 10);
        ironTrapBaseTime = builder.comment("The Lure Level of the Iron trap").defineInRange("ironTrapBaseTime", 600, 20, 2000000000);

        diamondTrapLureLevel = builder.comment("The Lure Level of the diamond trap").defineInRange("diamondTrapLureLevel", 3, 0, 10);
        diamondTrapLuckLevel = builder.comment("The Lure Level of the diamond trap").defineInRange("diamondTrapLuckLevel", 3, 0, 10);
        diamondTrapBaseTime = builder.comment("The Lure Level of the diamond trap").defineInRange("diamondTrapBaseTime", 400, 20, 2000000000);

        trapPenaltyMultiplier = builder.comment("The Multiplier penalty of traps without fishbait").defineInRange("trapPenaltyMultiplier", 40, 1, 1000);
        fishBaitDurability = builder.comment("The durability of fish bait (NOT implemented yet").defineInRange("fishBaitDurability", 400, 1, 2000000000);
        shouldTrapHavePenalty = builder.comment("Should fish traps be penalized if they dont have bait in them").define("shouldTrapHavePenalty", true);

    }

}
