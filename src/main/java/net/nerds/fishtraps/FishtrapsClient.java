package net.nerds.fishtraps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.math.BlockPos;
import net.nerds.fishtraps.blocks.FishTrapEntityManager;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapContainer;
import net.nerds.fishtraps.blocks.baseTrap.BaseFishTrapGui;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapContainer;
import net.nerds.fishtraps.blocks.diamondFishTrap.DiamondFishTrapGui;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapContainer;
import net.nerds.fishtraps.blocks.ironFishTrap.IronFishTrapGui;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapBlockEntity;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapContainer;
import net.nerds.fishtraps.blocks.woodenFishTrap.WoodenFishTrapGui;

public class FishtrapsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        this.registerClientGuis();
    }

    private void registerClientGuis() {

        ScreenProviderRegistry.INSTANCE.registerFactory(FishTrapEntityManager.WOODEN_FISH_TRAP_CONTAINER, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            WoodenFishTrapBlockEntity fishTrapBlockEntity = (WoodenFishTrapBlockEntity) player.world.getBlockEntity(pos);
            return new WoodenFishTrapGui(fishTrapBlockEntity,
                    new WoodenFishTrapContainer(syncid, player.inventory, fishTrapBlockEntity),
                    "Wooden Fish Trap", "wooden_fish_trap");
        }));

        ScreenProviderRegistry.INSTANCE.registerFactory(FishTrapEntityManager.IRON_FISH_TRAP_CONTAINER, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            IronFishTrapBlockEntity fishTrapBlockEntity = (IronFishTrapBlockEntity) player.world.getBlockEntity(pos);
            return new IronFishTrapGui(fishTrapBlockEntity,
                    new IronFishTrapContainer(syncid, player.inventory, fishTrapBlockEntity),
                    "Iron Fish Trap", "iron_fish_trap");
        }));

        ScreenProviderRegistry.INSTANCE.registerFactory(FishTrapEntityManager.DIAMOND_FISH_TRAP_CONTAINER, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            DiamondFishTrapBlockEntity fishTrapBlockEntity = (DiamondFishTrapBlockEntity) player.world.getBlockEntity(pos);
            return new DiamondFishTrapGui(fishTrapBlockEntity,
                    new DiamondFishTrapContainer(syncid, player.inventory, fishTrapBlockEntity),
                    "Diamond Fish Trap", "Diamond_fish_trap");
        }));

    }
}
