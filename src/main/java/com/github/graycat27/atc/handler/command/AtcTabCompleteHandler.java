package com.github.graycat27.atc.handler.command;

import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.consts.Control;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtcTabCompleteHandler implements TabCompleter {

    // フィールド
    private static Plugin plugin;

    // コンストラクタ
    public AtcTabCompleteHandler(Plugin plugin){
        AtcTabCompleteHandler.plugin = plugin;
    }

    // メソッド
    /**
     * Tab補完支援
     * @param sender コマンド入力者
     * @param command コマンド(<code>atc</code>のはず)
     * @param alias 使用された別名(<code>atc</code>のはず)
     * @param args コマンドの引数部分<br>プレイヤー入力値「/atc freq tuning」の<br>freq, tuning が配列の[0][1]にそれぞれ順に格納される
     * @return 補完候補のList。<code>emptyList</code>の場合あり
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

        // /atc help x
        if(args[0].equals(CommandWord.HELP)){
            return Collections.emptyList();
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
            if(CommandWord.Manage.MODIFY.equalsIgnoreCase(args[1]) ||
                    CommandWord.Manage.INFO.equalsIgnoreCase(args[1]) ) {
                // addのとき表示しない
                targetList.add(CommandWord.Target.AREA);
            }
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

            if( CommandWord.Manage.ADD.equalsIgnoreCase(args[1]) &&
                CommandWord.Target.AIRPORT.equalsIgnoreCase(args[2])){
                List<String> hint = new ArrayList<>();
                hint.add("newAirportName");
                return hint;
            }

            if( CommandWord.Manage.MODIFY.equalsIgnoreCase(args[1]) &&
                CommandWord.Target.AIRPORT.equalsIgnoreCase(args[2])){
                // /atc manage mod airport <name> <meta> <value>
                if(args.length == 4){
                    List<String> result = new ArrayList<>();
                    List<String> apNameList = DataUtil.getAirportNameList();
                    for(String maybe : apNameList){
                        if(maybe.startsWith(args[3])){
                            result.add(maybe);
                        }
                    }
                    return result;
                }
                if(args.length == 5){
                    List<String> result = new ArrayList<>();
                    List<String> meta = new ArrayList<>();
                    meta.add(CommandWord.AirportMeta.ATC_Name);
                    meta.add(CommandWord.AirportMeta.CTL_FREQ);
                    meta.add(CommandWord.AirportMeta.TWR_FREQ);
                    for(String maybe : meta){
                        if(maybe.toLowerCase().startsWith(args[4].toLowerCase())) {
                            result.add(maybe);
                        }
                    }
                    return result;
                }
                if(args.length == 6){
                    if(CommandWord.AirportMeta.ATC_Name.equalsIgnoreCase(args[4])){
                        List<String> hint = new ArrayList<>();
                        hint.add("atc name only with alphabets and numbers");
                        return hint;
                    }
                    if( CommandWord.AirportMeta.TWR_FREQ.equalsIgnoreCase(args[4]) ||
                            CommandWord.AirportMeta.CTL_FREQ.equalsIgnoreCase(args[4])){
                        List<String> hint = new ArrayList<>();
                        hint.add("000.0");
                        hint.add("999.9");
                        return hint;
                    }

                }
            }

            if( CommandWord.Manage.MODIFY.equalsIgnoreCase(args[1]) &&
                CommandWord.Target.AREA.equalsIgnoreCase(args[2])){
                // /atc manage mod area <apName> <control> <centerPos> <radius>
                if(args.length == 4){
                    List<String> result = new ArrayList<>();
                    List<String> apNameList = DataUtil.getAirportNameList();
                    for(String maybe : apNameList){
                        if(maybe.startsWith(args[3])){
                            result.add(maybe);
                        }
                    }
                    return result;
                }
                if(args.length == 5) {
                    List<String> result = new ArrayList<>();
                    Control[] controls = Control.values();
                    for(Control maybe : controls){
                        if(maybe.toString().startsWith(args[4])){
                            result.add(maybe.toString());
                        }
                    }
                    return result;
                }
                if(args.length == 6){
                    String format = "center location like 100,70,200 or 100,200";
                    String guid = "if pattern x,z then y will be 64 for sea level";
                    List<String> hint = new ArrayList<>();
                    hint.add(format);
                    hint.add(guid);
                    return hint;
                }
                if(args.length == 7){
                    String guid = "radius from the center location";
                    List<String> hint = new ArrayList<>();
                    hint.add(guid);
                    return hint;
                }
            }

            if( CommandWord.Manage.INFO.equalsIgnoreCase(args[1]) &&
                    CommandWord.Target.AIRPORT.equalsIgnoreCase(args[2])){
                List<String> result = new ArrayList<>();
                List<String> apNameList = DataUtil.getAirportNameList();
                apNameList.add(" "); //for all AP info
                for(String maybe : apNameList){
                    if(maybe.startsWith(args[3])){
                        result.add(maybe);
                    }
                }
                return result;
            }
        }

        return Collections.emptyList();
    }
}
