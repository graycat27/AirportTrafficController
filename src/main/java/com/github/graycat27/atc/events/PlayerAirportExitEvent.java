package com.github.graycat27.atc.events;

import com.github.graycat27.atc.defines.airport.Airport;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerAirportExitEvent {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Airport airport;

    public PlayerAirportExitEvent(Player player, Airport airport) {
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
