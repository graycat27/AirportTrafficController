package com.github.graycat27.atc;

import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class AirportTrafficController extends JavaPlugin {

    /** Plugin startup logic */
    @Override
    public void onEnable() {
        this.getCommand(CommandWord.ATC).setExecutor(new AtcCommandHandler(this));
        this.getCommand(CommandWord.ATC).setTabCompleter(new AtcCommandHandler(this));
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() {

    }
}
