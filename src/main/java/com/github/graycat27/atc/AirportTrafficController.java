package com.github.graycat27.atc;

import com.github.graycat27.atc.components.data.DataManager;
import com.github.graycat27.atc.components.data.defines.IDataManager;
import com.github.graycat27.atc.components.data.json.JsonDataManager;
import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AirportTrafficController extends JavaPlugin {

    private static LunaChatAPI lcApi;
    public static LunaChatAPI getLcApi(){
        return lcApi;
    }

    private static IDataManager dataManager;
    public static IDataManager getDataManager(){
        return dataManager;
    }

    /** Plugin startup logic */
    @Override
    public void onEnable() {
        this.getCommand(CommandWord.ATC_full).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC_full).setTabCompleter(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC).setTabCompleter(new AtcCommandHandler(this));

        if ( getServer().getPluginManager().isPluginEnabled("LunaChat") ) {
            LunaChatBukkit lunaChat = (LunaChatBukkit) getServer().getPluginManager().getPlugin("LunaChat");
            lcApi = lunaChat.getLunaChatAPI();
        }

        dataManager = new JsonDataManager();
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() {

        lcApi = null;
    }
}
