package com.github.graycat27.atc;

import com.github.graycat27.atc.components.data.DataManager;
import com.github.graycat27.atc.components.data.defines.IDataManager;
import com.github.graycat27.atc.components.data.json.JsonDataManager;
import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import com.github.graycat27.atc.handler.command.AtcTabCompleteHandler;
import com.github.graycat27.atc.handler.event.AtcDataUpdateHandler;
import com.github.graycat27.atc.handler.event.LcChatHandler;
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

    /** Plugin startup logic */
    @Override
    public void onEnable() {
        super.onEnable();

        this.getCommand(CommandWord.ATC_full).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC_full).setTabCompleter(new AtcTabCompleteHandler(this));
        this.getCommand(CommandWord.ATC).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC).setTabCompleter(new AtcTabCompleteHandler(this));

        getServer().getPluginManager().registerEvents(new AtcDataUpdateHandler(), this);
        getServer().getPluginManager().registerEvents(new LcChatHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveHandler(), this);

        if ( getServer().getPluginManager().isPluginEnabled("LunaChat") ) {
            LunaChatBukkit lunaChat = (LunaChatBukkit) getServer().getPluginManager().getPlugin("LunaChat");
            lcApi = lunaChat.getLunaChatAPI();
        }

        dataManager = new JsonDataManager();
        dataManager.read();
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() {
        super.onDisable();

        dataManager.save();
        lcApi = null;
    }
}
