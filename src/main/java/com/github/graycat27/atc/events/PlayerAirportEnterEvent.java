package com.github.graycat27.atc.events;

import com.github.graycat27.atc.defines.airport.Airport;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAirportEnterEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Airport airport;

    public PlayerAirportEnterEvent(Player player, Airport airport) {
        this.player = player;
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
