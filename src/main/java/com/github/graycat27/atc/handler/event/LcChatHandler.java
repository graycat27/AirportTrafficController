package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.LunaChatUtil;
import com.github.graycat27.atc.components.bot.ChatChecker;
import com.github.ucchyocean.lc3.bukkit.event.LunaChatBukkitChannelChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LcChatHandler implements Listener {

    @EventHandler
    public void onChat(LunaChatBukkitChannelChatEvent event){
        if(LunaChatUtil.isAtcChannel(event.getChannel())){
            String freq = FrequencyUtil.getFreqFromChannelName(event.getChannel().getName());
            event.setNgMaskedMessage(ChatChecker.getMessage(freq, event.getNgMaskedMessage()));
        }

    }

}
