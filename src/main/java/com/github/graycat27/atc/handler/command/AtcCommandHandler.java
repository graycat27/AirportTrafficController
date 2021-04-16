package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.action.CommandHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * コマンドの受理部品
 */
public class AtcCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //TODO コマンド文解析処理
        // 実際の処理動作はactionパッケージ配下に切り出して、可読性を確保すること

        /* /atc */
        if (!command.getName().equalsIgnoreCase(CommandWord.ATC)){
            return false;
        }
        if(args.length == 0){
            // /atc のみの場合
            CommandHelp.showHelp(sender);
            return true;
        }
        /* /atc freq xxx */
        if(CommandWord.Freq.FREQ.equalsIgnoreCase(args[0])){

            //args[1] で処理分岐
        }


        return true;
    }



}
