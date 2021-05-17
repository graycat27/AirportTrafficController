package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private CommandHelp(){}

    public static void showHelp(CommandSender sender){
        AtcCommandHandler.sendMessage(sender, "ATC commands");
        // /atc freq
        String atcFreq = CommandWord.ATC + " " + CommandWord.Freq.FREQ + " ";
        AtcCommandHandler.sendMessage(sender, "[/"+ atcFreq + CommandWord.Freq.MONITOR + " 123.4]" +
                " 周波数123.4に接続します");
        AtcCommandHandler.sendMessage(sender, "[/"+ atcFreq+ CommandWord.Freq.CUT + " 123.4]" +
                " 周波数123.4から切断します");

        // /atc manage
        String atcManage = CommandWord.ATC + " " + CommandWord.Manage.MANAGE + " ";
        String actions = CommandWord.Manage.ADD + "|" + CommandWord.Manage.MODIFY + "|" + CommandWord.Manage.INFO;
        String targets = CommandWord.Target.AIRPORT + "|" + CommandWord.Target.AREA;
        AtcCommandHandler.sendMessage(sender, "[/" + atcManage + actions + " " + targets + "]" +
                " 管理コマンド群");

    }
}
