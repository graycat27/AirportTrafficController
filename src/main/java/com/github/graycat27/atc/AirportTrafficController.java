package com.github.graycat27.atc;

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
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() {

        lcApi = null;
    }
}
