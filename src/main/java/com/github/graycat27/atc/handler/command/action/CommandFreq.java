package com.github.graycat27.atc.handler.command.action;

import org.bukkit.command.CommandSender;

import static com.github.graycat27.atc.handler.command.AtcCommandHandler.sendMessage;

public class CommandFreq {

    public static void runTuningCommand(CommandSender sender, String targetParam){
        //TODO make this
        AtcCommandHandler.sendMessage(sender, "tuning command is unavailable");
    }

    public static void runCutCommand(CommandSender sender, String targetParam){
        //TODO make this
        sendMessage(sender, "cut command is unavailable");
    }

}
