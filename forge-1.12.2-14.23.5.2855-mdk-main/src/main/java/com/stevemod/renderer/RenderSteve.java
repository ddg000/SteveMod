package com.stevemod.renderer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import com.stevemod.entity.EntitySteve;
import com.stevemod.client.model.ModelSteve;

public class RenderSteve extends RenderLiving<EntitySteve> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("stevemod:textures/entity/steve.png");

    public RenderSteve(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSteve(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySteve entity) {
        return TEXTURE;
    }
}