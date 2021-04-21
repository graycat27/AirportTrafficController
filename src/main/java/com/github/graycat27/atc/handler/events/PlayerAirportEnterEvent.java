package com.github.graycat27.atc.handler.events;

import com.github.graycat27.atc.defines.airport.Airport;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class PlayerAirportEnterEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Airport airport;

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

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
