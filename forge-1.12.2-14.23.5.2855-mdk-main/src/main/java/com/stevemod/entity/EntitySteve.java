package com.stevemod.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import com.stevemod.SteveMod;
import com.stevemod.network.JennyPoseMessage;

import java.util.Random;

public class EntitySteve extends EntityCreature {

    public static final String JENNY_NBT_TAG = "IsJenny";

    // Анимация
    private Animation currentAnimation = Animation.NONE;
    private int animationTick = 0;
    private boolean finishing = false;
    private int finishTimer = 0;

    // Диалог
    private int dialogueStep = -1;
    private int dialogueTimer = 0;
    private static final int DIALOGUE_DELAY = 30; // 1.5 сек
    private static final String[] DIALOGUE_LINES = {
        "§dJenny: §fХэй! Путник!",
        "§dJenny: §fНе хочешь развлечься?",
        "§bSteve: §fКак насчёт минета?",
        "§dJenny: §fHmm??"
    };

    // Кулдаун
    private int cooldownTimer = 0;

    // Текущая Jenny
    private EntityPlayer currentJenny = null;

    public EntitySteve(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this,