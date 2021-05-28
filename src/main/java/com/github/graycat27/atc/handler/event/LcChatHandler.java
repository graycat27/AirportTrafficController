package com.github.graycat27.atc.handler.event;

import com.github.ucchyocean.lc3.bukkit.event.LunaChatBukkitChannelChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LcChatHandler implements Listener {

    @EventHandler
    public void onChat(LunaChatBukkitChannelChatEvent event){
        //TODO make this

        System.out.println("chat event handled");

        System.out.println(event.getMember());

    }

}
