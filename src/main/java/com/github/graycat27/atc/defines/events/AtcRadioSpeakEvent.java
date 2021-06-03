package com.github.graycat27.atc.defines.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class AtcRadioSpeakEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList(){
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }

    private final String message;
    private final String freq;

    public AtcRadioSpeakEvent(String freq, String message){
        super(true);
        this.freq = freq;
        this.message = message;
    }

    public String getFreq(){
        return freq;
    }
    public String getMessage(){
        return message;
    }
}
