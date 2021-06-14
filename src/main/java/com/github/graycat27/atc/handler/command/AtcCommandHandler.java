package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.consts.PermissionNode;
import com.github.graycat27.atc.handler.command.action.CommandAirport;
import com.github.graycat27.atc.handler.command.action.CommandArea;
import com.github.graycat27.atc.handler.command.action.CommandFreq;
import com.github.graycat27.atc.handler.command.action.CommandHelp;
import com.github.graycat27.atc.setting.PropertySettings;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * コマンドの受理部品
 */
public class AtcCommandHandler implements CommandExecutor {

    // フィールド
    private static Plugin plugin;

    private final String wrongCommandMsg = ChatColor.RED + "wrong command." + ChatColor.WHITE + " use [/atc help]";
    private final String permissionErrorMsg = ChatColor.RED + "you don`t have permission";
    private final String denyWorldMsg = "only in world ["+ PropertySettings.worldName() +"] available";

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
            String lessParamMsg = ChatColor.RED + "less parameter" + ChatColor.WHITE + " for command [/atc manage]";
            if(args.length < 2){
                sendMessage(sender, lessParamMsg);
                return true;
            }

            if(sender instanceof Player p){
                if(!PropertySettings.worldName().equals(p.getLocation().getWorld().getName())){
                    sendMessage(sender, denyWorldMsg);
                    return true;
                }
            }

            /* /atc manage add xxx */
            if (CommandWord.Manage.ADD.equalsIgnoreCase(args[1])) {
                if(!sender.hasPermission(PermissionNode.ATC_MANAGE_CHANGE)){
                    sendMessageNoPermission(sender);
                    return true;
                }
                if (args.length < 3) {
                    sendMessage(sender, lessParamMsg);
                    return true;
                }
                try {
                    switch (args[2]) {
                        case CommandWord.Target.AIRPORT:
                            String name = (args.length >= 4) ? args[3] : null;
                            CommandAirport.add(name);
                            break;
                        case CommandWord.Target.AREA:
                            //goto default
                        default:
                            sendMessage(sender, "unknown param for command [/atc manage add]");
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
                    return true;
                }
                String param = (args.length >= 4) ? args[3] : null;
                switch (args[2]) {
                    case CommandWord.Target.AIRPORT:
                        CommandAirport.info(sender, param);
                        break;
                    case CommandWord.Target.AREA:
                        CommandArea.info(sender, param);
                        break;
                    default:
                        sendMessage(sender, "unknown param for command [/atc manage info]");
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
     * メッセージを表示させる。
     * コンソールとゲーム内プレイヤーの違いを意識しないで処理するために作成
     * @param sender コマンドの発行者
     * @param message 表示させるメッセージ文。本処理内で冒頭に<code>[ATC] </code>を付与する
     */
    public static void sendMessage(final CommandSender sender, String message){
        String msg = "[ATC] " + message;
        if(sender instanceof Player p){
            p.sendMessage(msg);
        }else{
            plugin.getLogger().info(msg);
        }
    }



}
