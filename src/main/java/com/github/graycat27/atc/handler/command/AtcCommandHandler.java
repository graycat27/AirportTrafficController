package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.action.CommandAirport;
import com.github.graycat27.atc.handler.command.action.CommandArea;
import com.github.graycat27.atc.handler.command.action.CommandFreq;
import com.github.graycat27.atc.handler.command.action.CommandHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * コマンドの受理部品
 */
public class AtcCommandHandler implements CommandExecutor, TabCompleter {

    // フィールド
    private static Plugin plugin;

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
        if(args.length == 0 || CommandWord.HELP.equalsIgnoreCase(args[0])){
            // /atc のみの場合
            CommandHelp.showHelp(sender);
            return true;
        }
        /* /atc freq xxx */
        if(CommandWord.Freq.FREQ.equalsIgnoreCase(args[0])){
            if(args.length < 2){
                sendMessage(sender, "less parameter for command [/atc freq]");
                this.sendMessageUnknownCommand(sender);
                return true;
            }
            //args[1] で処理分岐
            if(CommandWord.Freq.TUNING.equalsIgnoreCase(args[1])){
                String targetChannel = (args.length >= 3) ? args[2] : null;
                CommandFreq.runTuningCommand(sender, targetChannel);
                return true;
            }
            if(CommandWord.Freq.CUT.equalsIgnoreCase(args[1])){
                String targetChannel = (args.length >= 3) ? args[2] : null;
                CommandFreq.runCutCommand(sender, targetChannel);
            }
        }

        /* /atc add xxx */
        if(CommandWord.Method.ADD.equalsIgnoreCase(args[0])){
            if(args.length < 2){
                sendMessage(sender, "less parameter for command [/atc add]");
                this.sendMessageUnknownCommand(sender);
                return true;
            }
            switch(args[1]){
                case CommandWord.Target.AIRPORT:
                    String name = (args.length >= 3) ? args[2] : null;
                    CommandAirport.add(name);
                    break;
                case CommandWord.Target.AREA:
                    String freq = (args.length >= 3) ? args[2] : null;
                    CommandArea.add(freq);
                    break;
                default:
                    sendMessage(sender, "unknown param for command [/atc add]");
                    this.sendMessageUnknownCommand(sender);
            }
            return true;
        }

        /* /atc mod xxx */
        if(CommandWord.Method.MODIFY.equalsIgnoreCase(args[0])){
            if(args.length < 2){
                sendMessage(sender, "less parameter for command [/atc mod]");
                this.sendMessageUnknownCommand(sender);
                return true;
            }
            switch(args[1]){
                case CommandWord.Target.AIRPORT:
                    CommandAirport.mod(args);
                    break;
                case CommandWord.Target.AREA:
                    CommandArea.mod(args);
                    break;
                default:
                    sendMessage(sender, "unknown param for command [/atc mod]");
                    this.sendMessageUnknownCommand(sender);
            }
            return true;
        }

        /* /atc info xxx */
        if(CommandWord.Method.INFO.equalsIgnoreCase(args[0])){
            if(args.length < 2){
                sendMessage(sender, "less parameter for command [/atc info]");
                this.sendMessageUnknownCommand(sender);
                return true;
            }
            String param = (args.length >= 3) ? args[2] : null;
            switch (args[1]){
                case CommandWord.Target.AIRPORT:
                    CommandAirport.info(param);
                    break;
                case CommandWord.Target.AREA:
                    CommandArea.info(param);
                    break;
                default:
                    sendMessage(sender, "unknown param for command [/atc add]");
                    this.sendMessageUnknownCommand(sender);
            }
            return true;
        }

        this.sendMessageUnknownCommand(sender);
        return true;
    }

    private void sendMessageUnknownCommand(CommandSender sender){
        sendMessage(sender, "wrong command. use [/atc help]");
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

    /**
     * メッセージを表示させる。
     * コンソールとゲーム内プレイヤーの違いを意識しないで処理するために作成
     * @param sender コマンドの発行者
     * @param message 表示させるメッセージ文。本処理内で冒頭に<code>[ATC] </code>を付与する
     */
    public static void sendMessage(final CommandSender sender, String message){
        String msg = "[ATC] " + message;
        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(msg);
        }else{
            plugin.getLogger().info(msg);
        }
    }



}
