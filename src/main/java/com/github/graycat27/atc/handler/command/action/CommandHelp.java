package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private CommandHelp(){}

    public static void showHelp(CommandSender sender){
        //TODO コマンドヘルプ一覧を送出する
        AtcCommandHandler.sendMessage(sender, "help will show in the future");
    }
}
