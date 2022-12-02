package com.mitosv.detectcolision.command;

import com.mitosv.detectcolision.DetectColision;
import com.mitosv.detectcolision.config.ConfigManager;
import com.mitosv.detectcolision.net.PacketHandler;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ReloadCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("mitosReload")
                .executes(ReloadCommand::execute));

    }

    private static int execute(CommandContext<ServerCommandSource> command){
        ConfigManager.Config config = ConfigManager.getInstance().getCurrentConfig();
        command.getSource().sendMessage(Text.of("Max Y: "+config.getMaxY()
                        +", Min Y: "+config.getMinY()+", Max Blocks: "+config.getMaxBlocks()));

        command.getSource().getServer().getPlayerManager().getPlayerList()
                .forEach(player -> {
                    PacketHandler.sendReload(player,config.getMaxY(),config.getMinY()
                    ,config.getMaxBlocks());
                });

        return Command.SINGLE_SUCCESS;
    }

}
