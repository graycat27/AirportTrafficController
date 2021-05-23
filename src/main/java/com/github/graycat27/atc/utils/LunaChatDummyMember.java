package com.github.graycat27.atc.utils;

import com.github.graycat27.atc.components.PropertyUtil;
import com.github.ucchyocean.lc3.member.ChannelMember;
import net.md_5.bungee.api.chat.BaseComponent;

public class LunaChatDummyMember extends ChannelMember {

    private final String NAME = "[ATC]admin";

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDisplayName() {
        return "[ATC]管理者";
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public String getSuffix() {
        return "";
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void sendMessage(BaseComponent[] message) {
        for ( BaseComponent comp : message ) {
            System.out.print(comp.toLegacyText());
        }
        System.out.println();
    }

    @Override
    public String getWorldName() {
        return PropertyUtil.getWorld().getName();
    }

    @Override
    public String getServerName() {
        return "server";
    }

    @Override
    public boolean hasPermission(String node) {
        return true;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public boolean isPermissionSet(String node) {
        return true;
    }

    @Override
    public void chat(String message) {
        // nothing to do
    }
}
