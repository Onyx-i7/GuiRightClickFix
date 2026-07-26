package com.onyxi7.guirightclickfix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
    modid = GuiRightClickFix.MODID, 
    name = GuiRightClickFix.NAME, 
    version = GuiRightClickFix.VERSION, 
    acceptedMinecraftVersions = "[1.12.2]"
)
public class GuiRightClickFix {
    public static final String MODID = "guirightclickfix";
    public static final String NAME = "GUI Right Click Fix";
    public static final String VERSION = "1.0.0";
    
    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Initializing GUI Right Click Fix...");
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new GuiInputHandler());
        logger.info("GUI Right Click Fix loaded successfully");
    }
}