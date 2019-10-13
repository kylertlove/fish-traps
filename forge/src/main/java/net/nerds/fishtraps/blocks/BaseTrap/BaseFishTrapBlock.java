package net.nerds.fishtraps.blocks.BaseTrap;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.nerds.fishtraps.Fishtraps;

public abstract class BaseFishTrapBlock extends Block implements ITileEntityProvider {

    public BaseFishTrapBlock(String name) {
        super(Material.WOOD);
        this.setRegistryName(Fishtraps.MODID, name);
    }
}
