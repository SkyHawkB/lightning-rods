package com.github.skyhawkb.lightningrods.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
    public static BlockLightningRod LIGHTNING_ROD = new BlockLightningRod();

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(
                LIGHTNING_ROD
        );

        GameRegistry.registerTileEntity(LIGHTNING_ROD.getTileEntityClass(), LIGHTNING_ROD.getRegistryName().toString());
    }
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                LIGHTNING_ROD.getItemBlock()
        );
    }
    public static void registerModels() {
        LIGHTNING_ROD.registerItemModel(Item.getItemFromBlock(LIGHTNING_ROD));
    }
}
