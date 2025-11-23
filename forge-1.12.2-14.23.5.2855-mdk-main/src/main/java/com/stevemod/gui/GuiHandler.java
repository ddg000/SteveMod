package com.stevemod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import com.stevemod.SteveMod;
import com.stevemod.entity.EntitySteve;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SteveMod.GUI_STEVE) {
            EntitySteve steve = (EntitySteve) world.getEntityByID(x);
            return new ContainerSteve(player.inventory, steve);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SteveMod.GUI_STEVE) {
            EntitySteve steve = (EntitySteve) world.getEntityByID(x);
            return new GuiSteve(player.inventory, steve);
        }
        return null;
    }
}