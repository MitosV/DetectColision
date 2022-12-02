package com.mitosv.detectcolision.command;

import com.mitosv.detectcolision.net.PacketHandler;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;

public class Principal {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        /*
        dispatcher.register(CommandManager.literal("control")
                .then(CommandManager.argument("valor", DoubleArgumentType.doubleArg(0))
                        .executes(Principal::execute)));*/

        dispatcher.register(CommandManager.literal("control")
                .then(CommandManager.argument("pos", BlockPosArgumentType.blockPos())
                                .executes(Principal::execute)));

    }


    private static int execute(CommandContext<ServerCommandSource> command){
        BlockPos pos;
        try {
            pos = BlockPosArgumentType.getBlockPos(command,"pos");
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        PacketHandler.sendGuiOpen(command.getSource().getPlayer(), pos);

        return Command.SINGLE_SUCCESS;
    }

/*
    private static int execute2(CommandContext<ServerCommandSource> command){

        double force = DoubleArgumentType.getDouble(command,"valor");

        command.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> {
            PacketHandler.sendEventForce(player,force);
        });

        command.getSource().sendMessage(Text.of("Listo fuerza puesta a " + force));
        return Command.SINGLE_SUCCESS;
    }*/

}
