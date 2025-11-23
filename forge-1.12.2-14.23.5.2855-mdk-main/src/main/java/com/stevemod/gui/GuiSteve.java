package com.stevemod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import com.stevemod.entity.EntitySteve;
import com.stevemod.SteveMod;
import com.stevemod.network.StartBlowjobMessage;

public class GuiSteve extends GuiContainer {

    private final EntitySteve steve;
    private GuiButton blowjobButton;

    public GuiSteve(InventoryPlayer playerInv, EntitySteve steve) {
        super(new ContainerSteve(Minecraft.getMinecraft().player, steve));
        this.steve = steve;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        updateButton();
    }

    private void updateButton() {
        boolean onCooldown = steve.isOnCooldown();
        int seconds = steve.getCooldownSeconds();

        String text;
        if (!onCooldown) {
            text = "Start action";
        } else if (seconds > 60) {
            int min = seconds / 60;
            int sec = seconds % 60;
            text = String.format("Wait %d:%02d", min, sec);
        } else {
            text = "Wait " + seconds + " sec";
        }

        if (this.buttonList.isEmpty()) {
            this.blowjobButton = new GuiButton(0, this.guiLeft + this.xSize / 2 - 60, this.guiTop + this.ySize / 2 - 10, 120, 20, text);
            this.buttonList.add(this.blowjobButton);
        } else {
            this.blowjobButton.displayString = text;
        }
        this.blowjobButton.enabled = !onCooldown;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        updateButton();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            // send packet to server to start action
            if (SteveMod.NETWORK != null && !steve.isOnCooldown()) {
                SteveMod.NETWORK.sendToServer(new StartBlowjobMessage(steve.getEntityId()));
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Action requested"));
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
