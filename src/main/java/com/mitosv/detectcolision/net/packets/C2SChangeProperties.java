package com.mitosv.detectcolision.net.packets;

import com.mitosv.detectcolision.net.PacketHandler;
import com.mitosv.detectcolision.server.ServerInit;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.MechanicalBearingTileEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class C2SChangeProperties {
    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity player,
                               ServerPlayNetworkHandler net, PacketByteBuf buf,
                               PacketSender packetSender) {
        if (player.getWorld().isClient)return;


        int[] coord = buf.readIntArray();
        BlockPos pos = new BlockPos(coord[0],coord[1],coord[2]);
        ServerWorld world = player.getWorld();
        BlockEntity block = world.getWorldChunk(pos).getBlockEntity(pos);
        if (block instanceof MechanicalBearingTileEntity mech){
            mech.setSpeed(buf.readInt());
        }

    }

    public static void values(MinecraftServer minecraftServer, ServerPlayerEntity player,
                              ServerPlayNetworkHandler net, PacketByteBuf buf,
                              PacketSender packetSender) {
        int force = buf.readInt();
        boolean active = buf.readBoolean();

        ServerInit.setValues(force, active);
        minecraftServer.getPlayerManager().getPlayerList().forEach(p->{
            PacketHandler.sendEventForce(p,force,active);
        });

    }
}
