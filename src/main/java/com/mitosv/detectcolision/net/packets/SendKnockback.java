package com.mitosv.detectcolision.net.packets;

import com.mitosv.detectcolision.DetectColision;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

public class SendKnockback {


    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        PlayerCollisionEvent.setForce(buf.readInt());
        PlayerCollisionEvent.setEvent(buf.readBoolean());
    }

}
