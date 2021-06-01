package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.defines.events.AtcRadioSpeakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AtcRadioListenHandler implements Listener {

    @EventHandler
    public void onAtcRadioListen(AtcRadioSpeakEvent event){

        //TODO make this
        System.out.println("radio listened as: " + event.getMessage());

    }
}
