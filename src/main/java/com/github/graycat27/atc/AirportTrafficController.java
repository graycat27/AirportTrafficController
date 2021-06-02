package com.github.graycat27.atc;

import com.github.graycat27.atc.components.PropertyUtil;
import com.github.graycat27.atc.components.bot.AtcResponseTask;
import com.github.graycat27.atc.components.data.DataManager;
import com.github.graycat27.atc.components.data.defines.IDataManager;
import com.github.graycat27.atc.components.data.json.JsonDataManager;
import com.github.graycat27.atc.defines.atc.AtcMessageData;
import com.github.graycat27.atc.handler.event.AtcRadioListenHandler;
import com.github.graycat27.atc.utils.AtcDictionary;
import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import com.github.graycat27.atc.handler.command.AtcTabCompleteHandler;
import com.github.graycat27.atc.handler.event.AtcDataUpdateHandler;
import com.github.graycat27.atc.handler.event.LcChatHandler;
import com.github.graycat27.atc.handler.event.PlayerMoveHandler;
import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AirportTrafficController extends JavaPlugin {

    private static LunaChatAPI lcApi;
    public static LunaChatAPI getLcApi(){
        return lcApi;
    }

    private static IDataManager dataManager;
    public static DataManager getDataManager(){
        return (DataManager) dataManager;
    }

    private static AtcResponseTask atcResponseTask = new AtcResponseTask();
    public static void pushAtcResponseTask(AtcMessageData newResponse){
        atcResponseTask.push(newResponse);
    }

    /** Plugin startup logic */
    @Override
    public void onEnable() {
        super.onEnable();

        /* command handlers */
        AtcCommandHandler atcCommandHandler = new AtcCommandHandler(this);
        AtcTabCompleteHandler atcTabCompleteHandler = new AtcTabCompleteHandler(this);

        /* event handlers */
        AtcDataUpdateHandler atcDataUpdateHandler = new AtcDataUpdateHandler();
        AtcRadioListenHandler atcRadioListenHandler = new AtcRadioListenHandler();
        LcChatHandler lcChatHandler = new LcChatHandler();
        PlayerMoveHandler playerMoveHandler = new PlayerMoveHandler();

        final int TPS = 20;
        int radarSecond = 2; //プレイヤー検知レーダーの照射頻度秒数    //TODO プロパティに外だし
        getServer().getScheduler().scheduleSyncRepeatingTask(this, playerMoveHandler, 0, radarSecond * TPS);
        int radioSpeakSecond = 1;
        getServer().getScheduler().scheduleSyncRepeatingTask(this, atcResponseTask, 0, radioSpeakSecond * TPS);

        this.getCommand(CommandWord.ATC_full).setExecutor(atcCommandHandler);
        this.getCommand(CommandWord.ATC_full).setTabCompleter(atcTabCompleteHandler);
        this.getCommand(CommandWord.ATC).setExecutor(atcCommandHandler);
        this.getCommand(CommandWord.ATC).setTabCompleter(atcTabCompleteHandler);

        getServer().getPluginManager().registerEvents(atcDataUpdateHandler, this);
        getServer().getPluginManager().registerEvents(atcRadioListenHandler, this);
        getServer().getPluginManager().registerEvents(lcChatHandler, this);
        getServer().getPluginManager().registerEvents(playerMoveHandler, this);

        if ( getServer().getPluginManager().isPluginEnabled("LunaChat") ) {
            LunaChatBukkit lunaChat = (LunaChatBukkit) getServer().getPluginManager().getPlugin("LunaChat");
            lcApi = lunaChat.getLunaChatAPI();
        }

        PropertyUtil.deployPropFile();

        dataManager = new JsonDataManager();
        dataManager.read();

        AtcDictionary.updateDictionary();
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() {
        super.onDisable();

        dataManager.save();
        lcApi = null;
    }
}
