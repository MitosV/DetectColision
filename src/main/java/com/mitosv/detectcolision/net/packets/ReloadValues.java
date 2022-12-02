package com.mitosv.detectcolision.net.packets;

import com.simibubi.create.Create;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class ReloadValues {
    public static void receive(MinecraftClient client,
                               ClientPlayNetworkHandler clientPlayNetworkHandler,
                               PacketByteBuf buf, PacketSender packetSender) {

        int[] values = buf.readIntArray();

        PlayerCollisionEvent.setMaxY(values[0]);
        PlayerCollisionEvent.setMinY(values[1]);
        Create.maxBlock = values[2];

    }
}
