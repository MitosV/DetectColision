package com.mitosv.detectcolision.net.packets;

import com.mitosv.detectcolision.gui.EditGUI;
import com.mitosv.detectcolision.gui.EditScreen;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.MechanicalBearingTileEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class OpenGui {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {

        assert client.player != null;
        int[] coord = buf.readIntArray();
        BlockPos pos = new BlockPos(coord[0],coord[1],coord[2]);
        BlockEntity block = client.player.getWorld().getBlockEntity(pos);
        if (block instanceof MechanicalBearingTileEntity mecha){
            client.execute(()->{
                client.setScreen(new EditScreen(new EditGUI(mecha,pos)));
            });
        }

    }
}
