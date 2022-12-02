package com.mitosv.detectcolision.net;

import com.mitosv.detectcolision.DetectColision;
import com.mitosv.detectcolision.net.packets.C2SChangeProperties;
import com.mitosv.detectcolision.net.packets.OpenGui;
import com.mitosv.detectcolision.net.packets.ReloadValues;
import com.mitosv.detectcolision.net.packets.SendKnockback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class PacketHandler {

    public static final Identifier NET_ID =
            new Identifier(DetectColision.MOD_ID,"networking");

    public static final Identifier GUI_NET =
            new Identifier(DetectColision.MOD_ID,"net_gui");

    public static final Identifier RELOAD_NET =
            new Identifier(DetectColision.MOD_ID,"net_reload");

    public static void registerClient(){
        ClientPlayNetworking.registerGlobalReceiver(GUI_NET, OpenGui::receive);
        ClientPlayNetworking.registerGlobalReceiver(NET_ID, SendKnockback::receive);
        ClientPlayNetworking.registerGlobalReceiver(RELOAD_NET, ReloadValues::receive);
    }

    public static void registerServer(){
        ServerPlayNetworking.registerGlobalReceiver(NET_ID, C2SChangeProperties::receive);
        ServerPlayNetworking.registerGlobalReceiver(GUI_NET, C2SChangeProperties::values);
    }


    public static void sendServerValues(int force, boolean active){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(force);
        buf.writeBoolean(active);
        ClientPlayNetworking.send(GUI_NET,buf);
    }

    public static void sendToC2S(BlockPos pos, int speed){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIntArray(new int[]{pos.getX(),pos.getY(),pos.getZ()});
        buf.writeInt(speed);
        ClientPlayNetworking.send(NET_ID,buf);
    }

    public static void sendReload(ServerPlayerEntity player, int maxY, int minY, int maxBlocks){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIntArray(new int[]{maxY,minY,maxBlocks});
        ServerPlayNetworking.send(player,RELOAD_NET,buf);
    }

    public static void sendGuiOpen(ServerPlayerEntity player, BlockPos pos){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIntArray(new int[]{pos.getX(),pos.getY(),pos.getZ()});
        ServerPlayNetworking.send(player,GUI_NET,buf);
    }

    public static void sendEventForce(ServerPlayerEntity player, int force, boolean active){
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeInt(force);
        buf.writeBoolean(active);

        ServerPlayNetworking.send(player,NET_ID,buf);
    }



}
