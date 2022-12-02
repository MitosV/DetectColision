package com.mitosv.detectcolision.client;

import com.mitosv.detectcolision.net.PacketHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class DetectColisionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PacketHandler.registerClient();
    }

    public static void openScreen(Screen screen){
        MinecraftClient.getInstance().execute(()->{
            MinecraftClient.getInstance().setScreen(screen);
        });
    }


}
