package com.stevemod.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import com.stevemod.entity.EntitySteve;
import com.stevemod.renderer.RenderSteve;

public class ClientProxy extends com.stevemod.CommonProxy {
    @Override
    public void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySteve.class, RenderSteve::new);
        MinecraftForge.EVENT_BUS.register(new JennyPoseRenderer());
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
    }
}