package com.mitosv.detectcolision.server;

import com.mitosv.detectcolision.config.ConfigManager;
import com.mitosv.detectcolision.net.PacketHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerInit implements DedicatedServerModInitializer {

    public static boolean active = false;
    public static int force = 0;
    private static ConfigManager configManager;
    public static boolean isLoaded = false;

    @Override
    public void onInitializeServer() {
        ServerTickEvents.START_SERVER_TICK.register(new ServerTick());
    }

    public static void onlyServer(MinecraftServer server){
        Path path = Paths.get(server.getRunDirectory().getAbsolutePath(),
                "config","mitos_config.json");
        configManager = new ConfigManager(path.toFile());
    }

    public static void setValues(ServerPlayerEntity player){
        PacketHandler.sendEventForce(player,force,active);
    }

    public static void setValues(int force1, boolean active1){
        force = force1;
        active = active1;
    }
}
