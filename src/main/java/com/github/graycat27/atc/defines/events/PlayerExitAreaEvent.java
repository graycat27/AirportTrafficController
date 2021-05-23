package com.github.graycat27.atc.defines.events;

import com.github.graycat27.atc.defines.i.IArea;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerExitAreaEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList(){
        return handlers;
    }

    private final Player player;
    private final IArea area;

    public PlayerExitAreaEvent(Player p, IArea a){
        this.player = p;
        this.area = a;
    }

    @Override
    @NotNull
    public HandlerList getHandlers(){
        return getHandlerList();
    }

    public Player getPlayer(){
        return player;
    }

    public IArea getArea(){
        return area.clone();
    }

}
