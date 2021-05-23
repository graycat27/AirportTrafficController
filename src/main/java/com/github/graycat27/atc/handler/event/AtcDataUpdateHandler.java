package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.defines.events.AtcDataUpdateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AtcDataUpdateHandler implements Listener {

    @EventHandler
    public void onDataUpdated(AtcDataUpdateEvent event){
        //TODO make this

        System.out.println("update event handled");

    }
}
