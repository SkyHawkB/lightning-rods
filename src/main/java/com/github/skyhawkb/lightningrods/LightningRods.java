package com.github.skyhawkb.lightningrods;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.event.RegistryEvent;

@Mod(modid = LightningRods.MODID, name = LightningRods.NAME, version = LightningRods.VERSION)
public class LightningRods {
    public static final String MODID = "lightningrods";
    public static final String NAME = "LightningRods";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static LightningRods instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
       @SubscribeEvent
       public static void registerItems(RegistryEvent.Register<Item> event) {

       }
       @SubscribeEvent
       public static void registerBlocks(RegistryEvent.Register<Block> event) {

       }
    }
}
