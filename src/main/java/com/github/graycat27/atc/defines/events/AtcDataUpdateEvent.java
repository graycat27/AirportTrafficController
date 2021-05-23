package com.github.graycat27.atc.defines.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 本プラグインのデータが更新されたときに発生するイベント
 * <p>DataManagerからのデータ読み直しが必要なものをhandlerで更新処理する</p>
 */
public class AtcDataUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList(){
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
}
