package com.stevemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import com.stevemod.client.JennyPoseRenderer;

public class JennyPoseMessage implements IMessage {

    private int playerId;
    private boolean enable;

    public JennyPoseMessage() {}
    public JennyPoseMessage(int playerId, boolean enable) {
        this.playerId = playerId;
        this.enable = enable;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerId = buf.readInt();
        enable = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerId);
        buf.writeBoolean(enable);
    }

    public static class Handler implements IMessageHandler<JennyPoseMessage, IMessage> {
        @Override
        public IMessage onMessage(JennyPoseMessage msg, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                EntityPlayer player = Minecraft.getMinecraft().world.getEntityByID(msg.playerId);
                if (player == Minecraft.getMinecraft().player) {
                    JennyPoseRenderer.isInBlowjobPose = msg.enable;
                }
            });
            return null;
        }
    }
}