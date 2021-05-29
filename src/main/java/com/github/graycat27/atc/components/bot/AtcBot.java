package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.defines.atc.LunaChatChannelFrequency;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;

abstract public class AtcBot implements IAtcBot {

    protected final String name;
    protected String apName = "";

    protected Channel ch = null;

    AtcBot(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return apName +" "+ name;
    }

    @Override
    public void joinFreq(LunaChatChannelFrequency freq){
        ch = AirportTrafficController.getLcApi().getChannel(FrequencyUtil.getChannelName(freq.getFreq()));
        ch.addMember(ChannelMember.getChannelMember("$f")); //FIXME
    }
}
