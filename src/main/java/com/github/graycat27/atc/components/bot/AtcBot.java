package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.components.PropertyUtil;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;
import net.md_5.bungee.api.chat.BaseComponent;

abstract public class AtcBot extends ChannelMember implements IAtcBot {

    protected final String name;
    protected String apName = "";

    protected Channel ch = null;

    AtcBot(String name){
        this.name = name;
    }

    public void setAirportName(String airportName){
        apName = airportName;
    }

    @Override
    abstract public String analyzeMessage(String received);

    /* override from ChannelMember */
    @Override
    public String getName(){
        return apName +" "+ name;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public String getDisplayName() {
        return "[ATC]["+ getName() +"]";
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
        return getName();
    }

    @Override
    public boolean isPermissionSet(String node) {
        return true;
    }

    @Override
    public void chat(String message) {
        System.out.println(message);
    }
}
