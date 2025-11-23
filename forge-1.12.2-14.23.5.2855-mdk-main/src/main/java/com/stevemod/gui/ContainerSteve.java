package com.stevemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import com.stevemod.entity.EntitySteve;

public class ContainerSteve extends Container {

    public final EntitySteve steve;

    public ContainerSteve(EntityPlayer player, EntitySteve steve) {
        this.steve = steve;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public boolean isOnCooldown() {
        return steve.isOnCooldown();
    }

    public int getCooldownSeconds() {
        return steve.getCooldownSeconds();
    }
}