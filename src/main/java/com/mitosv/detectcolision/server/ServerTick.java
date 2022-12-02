package com.mitosv.detectcolision.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class ServerTick implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer minecraftServer){
        if (!ServerInit.isLoaded){
            ServerInit.isLoaded = true;
            ServerInit.onlyServer(minecraftServer);
        }

    }
}