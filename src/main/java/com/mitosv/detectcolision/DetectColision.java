package com.mitosv.detectcolision;

import com.mitosv.detectcolision.blocks.RegisterBlocks;
import com.mitosv.detectcolision.command.Principal;
import com.mitosv.detectcolision.command.ReloadCommand;
import com.mitosv.detectcolision.listener.OnCollision;
import com.mitosv.detectcolision.net.PacketHandler;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DetectColision implements ModInitializer {

    public static final String MOD_ID = "detectcolision";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {

        commandsRegister();
        handlersRegister();
    }

    private void handlersRegister(){
        RegisterBlocks.registerModBlocks();
        PacketHandler.registerServer();
        OnCollision.register();
    }

    private void commandsRegister(){
        CommandRegistrationCallback.EVENT.register(Principal::register);
        CommandRegistrationCallback.EVENT.register(ReloadCommand::register);
    }



}
