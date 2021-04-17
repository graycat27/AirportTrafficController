package com.github.graycat27.atc;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class AirportTraficController extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand(CommandWord.ATC).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC).setTabCompleter(new AtcCommandHandler(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
