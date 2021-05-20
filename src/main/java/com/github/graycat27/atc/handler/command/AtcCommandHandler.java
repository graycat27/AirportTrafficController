package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.consts.PermissionNode;
import com.github.graycat27.atc.handler.command.action.CommandAirport;
import com.github.graycat27.atc.handler.command.action.CommandArea;
import com.github.graycat27.atc.handler.command.action.CommandFreq;
import com.github.graycat27.atc.handler.command.action.CommandHelp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * コマンドの受理部品
 */
public class AtcCommandHandler implements CommandExecutor, TabCompleter {

    // フィールド
    private static Plugin plugin;

    private final String wrongCommandMsg = ChatColor.RED + "wrong command." + ChatColor.WHITE + " use [/atc help]";
    private final String permissionErrorMsg = ChatColor.RED + "you don`t have permission";

    // コンストラクタ
    public AtcCommandHandler(Plugin plugin){
        AtcCommandHandler.plugin = plugin;
    }

    // メソッド
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {

        // 実際の処理動作はactionパッケージ配下に切り出して、可読性を確保すること

        /* /atc */
        if (!command.getName().equalsIgnoreCase(CommandWord.ATC)){
            return false;
        }
        if(args.length == 0 || CommandWord.HELP.equalsIgnoreCase(args[0])){
            // /atc のみの場合・helpコマンドの場合
            if(!sender.hasPermission(PermissionNode.ATC_HELP)){
                sendMessageNoPermission(sender);
                return true;
            }
            CommandHelp.showHelp(sender);
            return true;
        }
        /* /atc freq xxx */
        if(CommandWord.Freq.FREQ.equalsIgnoreCase(args[0])){
            if(!sender.hasPermission(PermissionNode.ATC_FREQ)){
                sendMessageNoPermission(sender);
                return true;
            }
            if(args.length < 2){
                sendMessage(sender, "less parameter for command [/atc freq]");
                this.sendMessageUnknownCommand(sender);
                return true;
            }
            //args[1] で処理分岐
            if(CommandWord.Freq.MONITOR.equalsIgnoreCase(args[1])){
                String targetChannel = (args.length >= 3) ? args[2] : null;
                CommandFreq.runMonitorCommand(sender, targetChannel);
                return true;
            }
            if(CommandWord.Freq.CUT.equalsIgnoreCase(args[1])){
                String targetChannel = (args.length >= 3) ? args[2] : null;
                CommandFreq.runCutCommand(sender, targetChannel);
                return true;
            }
        }

        /* /atc manage xxx */
        if (CommandWord.Manage.MANAGE.equalsIgnoreCase(args[0])){
            /* /atc manage add xxx */
            if (CommandWord.Manage.ADD.equalsIgnoreCase(args[1])) {
                if(!sender.hasPermission(PermissionNode.ATC_MANAGE_CHANGE)){
                    sendMessageNoPermission(sender);
                    return true;
                }
                if (args.length < 3) {
                    sendMessage(sender, "less parameter for command [/atc manage add]");
                    this.sendMessageUnknownCommand(sender);
                    return true;
                }
                try {
                    switch (args[2]) {
                        case CommandWord.Target.AIRPORT:
                            String name = (args.length >= 4) ? args[3] : null;
                            CommandAirport.add(name);
                            break;
                        case CommandWord.Target.AREA:
                            String freq = (args.length >= 4) ? args[3] : null;
                            CommandArea.add(freq);
                            break;
                        default:
                            sendMessage(sender, "unknown param for command [/atc manage add]");
                            this.sendMessageUnknownCommand(sender);
                    }
                }catch(IllegalArgumentException e){
                    sendMessage(sender, e.getMessage());
                }
                return true;
            }

            /* /atc manage mod xxx */
            if (CommandWord.Manage.MODIFY.equalsIgnoreCase(args[1])) {
                if(!sender.hasPermission(PermissionNode.ATC_MANAGE_CHANGE)){
                    sendMessageNoPermission(sender);
                    return true;
                }
                if (args.length < 3) {
                    sendMessage(sender, "less parameter for command [/atc manage mod]");
                    this.sendMessageUnknownCommand(sender);
                    return true;
                }
                try {
                    switch (args[2]) {
                        case CommandWord.Target.AIRPORT:
                            CommandAirport.mod(args);
                            break;
                        case CommandWord.Target.AREA:
                            CommandArea.mod(args);
                            break;
                        default:
                            sendMessage(sender, "unknown param for command [/atc manage mod]");
                            this.sendMessageUnknownCommand(sender);
                    }
                }catch(IllegalArgumentException|IllegalStateException e){
                    sendMessage(sender, e.getMessage());
                }
                return true;
            }

            /* /atc manage info xxx */
            if (CommandWord.Manage.INFO.equalsIgnoreCase(args[1])) {
                if(!sender.hasPermission(PermissionNode.ATC_MANAGE_INFO)){
                    sendMessageNoPermission(sender);
                    return true;
                }
                if (args.length < 3) {
                    sendMessage(sender, "less parameter for command [/atc manage info]");
                    this.sendMessageUnknownCommand(sender);
                    return true;
                }
                String param = (args.length >= 4) ? args[3] : null;
                switch (args[2]) {
                    case CommandWord.Target.AIRPORT:
                        CommandAirport.info(sender, param);
                        break;
                    case CommandWord.Target.AREA:
                        CommandArea.info(param);
                        break;
                    default:
                        sendMessage(sender, "unknown param for command [/atc manage info]");
                        this.sendMessageUnknownCommand(sender);
                }
                return true;
            }
        }

        this.sendMessageUnknownCommand(sender);
        return true;
    }

    private void sendMessageUnknownCommand(CommandSender sender){
        sendMessage(sender, wrongCommandMsg);
    }

    private void sendMessageNoPermission(CommandSender sender){
        sendMessage(sender, permissionErrorMsg);
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
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, String alias, String[] args) {
        if (!(alias.equalsIgnoreCase(CommandWord.ATC) || alias.equalsIgnoreCase(CommandWord.ATC_full))) {
            return null;
        }
        List<String> firstCommandList = new ArrayList<>();    //atc xxx
        firstCommandList.add(CommandWord.Freq.FREQ);
        firstCommandList.add(CommandWord.Manage.MANAGE);
        firstCommandList.add(CommandWord.HELP);

        if(args.length == 0 || args[0].equals("")){
            //全候補提供
            return firstCommandList;
        }

        if(args.length == 1){
            // /atc xx」状態
            List<String> resultList = new ArrayList<>();
            for(String maybe : firstCommandList){
                if(maybe.toLowerCase().startsWith(args[0].toLowerCase())){
                    resultList.add(maybe);
                }
            }
            return resultList;
        }

        // /atc freq xxx
        if (args[0].equals(CommandWord.Freq.FREQ)) {
            List<String> freqCmdList = new ArrayList<>();
            freqCmdList.add(CommandWord.Freq.MONITOR);
            freqCmdList.add(CommandWord.Freq.CUT);
            if(args.length == 2){
                // /atc freq xx[tab]
                List<String> resultList = new ArrayList<>();
                for(String maybe : freqCmdList){
                    if(maybe.toLowerCase().startsWith(args[1].toLowerCase())){
                        resultList.add(maybe);
                    }
                }
                return resultList;
            }
            if(args.length == 3) {
                // 接続(可能な|中の)周波数値を候補出せたらいいけど、
                // 労力に見合わなさそう。
                List<String> formatList = new ArrayList<>();
                formatList.add("000.0");
                formatList.add("999.9");
                return formatList;
            }
            return new ArrayList<>();
        }

        // /atc manage xxx
        if (args[0].equals(CommandWord.Manage.MANAGE)) {
            List<String> manageCmdList = new ArrayList<>();
            manageCmdList.add(CommandWord.Manage.ADD);
            manageCmdList.add(CommandWord.Manage.MODIFY);
            manageCmdList.add(CommandWord.Manage.INFO);
            if(args.length == 2){
                // /atc manage xx[tab]
                List<String> resultList = new ArrayList<>();
                for(String maybe : manageCmdList){
                    if(maybe.toLowerCase().startsWith(args[1].toLowerCase())){
                        resultList.add(maybe);
                    }
                }
                if(!(resultList.size() == 1 && resultList.get(0).equalsIgnoreCase(args[1]))){
                    return resultList;
                }   // length==2 で入力補完済みの場合、次の補完へ。

            }

            List<String> targetList = new ArrayList<>();
            targetList.add(CommandWord.Target.AIRPORT);
            targetList.add(CommandWord.Target.AREA);
            if(args.length == 2){
                // /atc manage add|mod|info [tab]
                return targetList;
            }
            if(args.length == 3){
                // /atc manage add|mod|info xx[tab]
                List<String> resultList = new ArrayList<>();
                for(String maybe : targetList){
                    if(maybe.toLowerCase().startsWith(args[2].toLowerCase())){
                        resultList.add(maybe);
                    }
                }
                return resultList;
            }
        }

        if(args[0].equals(CommandWord.HELP)){
            return Collections.emptyList();
        }

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
