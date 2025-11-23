package com.stevemod.network;

import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraft.entity.player.EntityPlayerMP;
import com.stevemod.entity.EntitySteve;

public class StartBlowjobHandler implements IMessageHandler<StartBlowjobMessage, IMessage> {
    @Override
    public IMessage onMessage(StartBlowjobMessage message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        EntitySteve steve = (EntitySteve) player.world.getEntityByID(message.entityId);

        if (steve != null && EntitySteve.isPlayerJenny(player) && !steve.isOnCooldown()) {
            steve.startDialogue(player);
        }
        return null;
    }
}