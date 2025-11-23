package com.stevemod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import com.stevemod.entity.EntitySteve;
import com.stevemod.network.StartBlowjobHandler;
import com.stevemod.network.StartBlowjobMessage;
import com.stevemod.network.JennyPoseMessage;

@Mod(modid = SteveMod.MODID, name = SteveMod.NAME, version = SteveMod.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class SteveMod {
    public static final String MODID = "stevemod";
    public static final String NAME = "Steve Mod";
    public static final String VERSION = "1.0";
    public static final int GUI_STEVE = 0;

    @Mod.Instance(MODID)
    public static SteveMod instance;

    @SidedProxy(clientSide = "com.stevemod.client.ClientProxy", serverSide = "com.stevemod.CommonProxy")
    public static CommonProxy proxy;

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "steve"), EntitySteve.class, "steve", 1, this, 80, 3, true, 0x33CCFF, 0xFF6633);

        NETWORK.registerMessage(StartBlowjobHandler.class, StartBlowjobMessage.class, 0, Side.SERVER);
        NETWORK.registerMessage(JennyPoseMessage.Handler.class, JennyPoseMessage.class, 1, Side.CLIENT);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        for (Biome biome : Biome.REGISTRY) {
            if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) {
                EntityRegistry.addSpawn(EntitySteve.class, 15, 1, 3, net.minecraft.entity.EnumCreatureType.CREATURE, biome);
            }
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new com.stevemod.gui.GuiHandler());
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandJennyToggle());
    }
}