package com.stevemod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import com.stevemod.entity.EntitySteve;

public class ModelSteve extends ModelBase {
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;

    public ModelSteve() {
        textureWidth = 64;
        textureHeight = 64;

        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
        bipedHead.setRotationPoint(0F, 0F, 0F);

        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
        bipedBody.setRotationPoint(0F, 0F, 0F);

        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4);
        bipedRightArm.setRotationPoint(-5F, 2F, 0F);

        bipedLeftArm = new ModelRenderer(this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4);
        bipedLeftArm.setRotationPoint(5F, 2F, 0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        bipedHead.render(scale);
        bipedBody.render(scale);
        bipedRightArm.render(scale);
        bipedLeftArm.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntitySteve steve = (EntitySteve) entityIn;

        // Сброс в idle pose
        bipedHead.rotateAngleX = 0F;
        bipedHead.rotateAngleY = 0F;
        bipedRightArm.rotateAngleX = 0F;
        bipedLeftArm.rotateAngleX = 0F;
        bipedBody.rotateAngleX = 0F;

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        float progress = steve.getAnimationProgress(0);

        if (!steve.isFinishing()) {
            switch (steve.getCurrentAnimation()) {
                case BLOWJOB_1:
                    bipedHead.rotateAngleX = -0.5F * progress;
                    bipedRightArm.rotateAngleX = -0.3F + 0.2F * MathHelper.sin(ageInTicks * 0.2F);
                    bipedLeftArm.rotateAngleX = -1.8F + 0.6F * MathHelper.sin(ageInTicks * 0.25F);
                    bipedLeftArm.rotateAngleZ = -0.8F;
                    bipedBody.rotateAngleX = 0.15F * progress;
                    break;

                case BLOWJOB_2:
                    bipedHead.rotateAngleX = 0.8F * progress;
                    bipedHead.rotateAngleZ = 0.12F * (float)Math.sin(ageInTicks * 0.35F * progress);
                    bipedRightArm.rotateAngleX = 0.5F * progress;
                    bipedLeftArm.rotateAngleX = -1.6F;
                    bipedLeftArm.rotateAngleZ = -0.6F;
                    break;

                case BLOWJOB_3:
                    bipedHead.rotateAngleX = 0.35F * MathHelper.sin(ageInTicks * 0.18F * progress);
                    bipedRightArm.rotateAngleX = -1.3F + 0.9F * MathHelper.sin(ageInTicks * 0.28F * progress);
                    bipedRightArm.rotateAngleZ = 0.5F * progress;
                    bipedLeftArm.rotateAngleX = -1.7F + 0.4F * MathHelper.sin(ageInTicks * 0.22F * progress);
                    bipedLeftArm.rotateAngleZ = -0.7F;
                    break;
            }
        }

        // Финальный толчок
        if (steve.isFinishing()) {
            float fp = steve.getFinishProgress(); 

            bipedRightArm.rotateAngleX = -2.3F - 0.3F * fp;
            bipedRightArm.rotateAngleZ = -1.0F;
            bipedLeftArm.rotateAngleX = -2.3F - 0.3F * fp;
            bipedLeftArm.rotateAngleZ = 1.0F;
bipedHead.rotateAngleX = 1.4F + 0.4F * (fp * 6F); // рывок
            bipedBody.rotateAngleX = 0.4F;

            if (steve.getFinishHold() > 0.5F) {
                bipedHead.rotateAngleX = 1.8F; // пик
            }
        }
    }
}