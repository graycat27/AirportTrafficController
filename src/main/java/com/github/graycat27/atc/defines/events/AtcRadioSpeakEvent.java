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

    public AtcRadioSpeakEvent(String message){
        super(true);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
