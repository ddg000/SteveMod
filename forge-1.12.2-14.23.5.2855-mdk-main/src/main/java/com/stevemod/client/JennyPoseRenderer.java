package com.stevemod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JennyPoseRenderer {

    public static boolean isInBlowjobPose = false;

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        EntityPlayer player = event.getEntityPlayer();
        if (!isInBlowjobPose || player != Minecraft.getMinecraft().player) return;

        ModelBiped model = event.getRenderer().getMainModel();

        // Тело слегка назад
        model.bipedBody.rotateAngleX = 0.5F;

        // Голова наклонена вперёд
        model.bipedHead.rotateAngleX = -0.35F;

        // Руки вытянуты вперёд и вниз
        model.bipedRightArm.rotateAngleX = -1.9F;
        model.bipedRightArm.rotateAngleZ = -0.5F;
        model.bipedLeftArm.rotateAngleX = -1.9F;
        model.bipedLeftArm.rotateAngleZ = 0.5F;

        // Ноги: сидим на попе, колени согнуты, голени назад и в стороны
        model.bipedRightLeg.rotateAngleX = -2.4F;
        model.bipedRightLeg.rotateAngleZ = -0.9F;
        model.bipedLeftLeg.rotateAngleX = -2.4F;
        model.bipedLeftLeg.rotateAngleZ = 0.9F;

        // Опускаем хитбокс и камеру (чтобы не застревала голова в Стиве)
        player.height = 1.35F;
        player.eyeHeight = 0.85F;
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        if (!isInBlowjobPose) return;
        
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && event.getEntityPlayer() == player) {
            // Возвращаем нормальный рост после рендера
            player.height = 1.8F;
            player.eyeHeight = 1.62F;
        }
    }

    // Лёгкое пока