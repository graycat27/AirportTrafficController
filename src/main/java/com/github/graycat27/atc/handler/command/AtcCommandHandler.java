package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.action.CommandHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * コマンドの受理部品
 */
public class AtcCommandHandler implements CommandExecutor, TabCompleter {

    // フィールド
    Plugin plugin;

    // コンストラクタ
    public AtcCommandHandler(Plugin plugin){
        this.plugin = plugin;
    }

    // メソッド
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

    /**
     * Tab補完支援
     * @param sender コマンド入力者
     * @param command コマンド(<code>atc</code>のはず)
     * @param alias 使用された別名(<code>atc</code>のはず)
     * @param args コマンドの引数部分<br>プレイヤー入力値「/atc freq tuning」の<br>freq, tuning が配列の[0][1]にそれぞれ順に格納される
     * @return 補完候補のList。<code>null</code>の場合あり
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        //TODO make this
        return null;
    }



}
