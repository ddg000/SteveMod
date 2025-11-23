package com.stevemod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import com.stevemod.entity.EntitySteve;

public class CommandJennyToggle extends CommandBase {
    @Override
    public String getName() { return "jennytoggle"; }
    @Override
    public String getUsage(ICommandSender sender) { return "/jennytoggle"; }
    @Override
    public int getRequiredPermissionLevel() { return 0; }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            NBTTagCompound data = player.getEntityData();
            boolean isJenny = !data.getBoolean(EntitySteve.JENNY_NBT_TAG);
            data.setBoolean(EntitySteve.JENNY_NBT_TAG, isJenny);
            player.sendMessage(new TextComponentString("§aТы теперь " + (isJenny ? "Jenny! §d❤️" : "парень.")));
        }
    }
}