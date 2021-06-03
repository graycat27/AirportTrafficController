package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.LunaChatUtil;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.atc.AtcMessageData;
import com.github.graycat27.atc.defines.i.ConcreteFrequency;
import com.github.ucchyocean.lc3.channel.Channel;

import java.util.LinkedList;
import java.util.TimerTask;

public class AtcResponseTask extends TimerTask {

    private LinkedList<AtcMessageData> queue = new LinkedList<>();

    public void push(AtcMessageData messageData){
        queue.addLast(messageData);
    }

    @Override
    public void run() {
        if(queue.isEmpty()){
            return;
        }

        AtcMessageData target = queue.getFirst();
        while (!target.isNeedResponse()) {
            queue.removeFirst();
            if(queue.isEmpty()){
                return;
            }
            target = queue.getFirst();
        }

        Channel ch = AirportTrafficController.getLcApi().getChannel(FrequencyUtil.getChannelName(target.getFrequency()));
        ATCControl control = FrequencyUtil.getAtcControl(new ConcreteFrequency(target.getFrequency()));

        AtcBot cm = LunaChatUtil.getChannelMember(control);
        String returnMessage = target.getSender() +", "+ cm.getName() +". "+ target.getResponseBody();
        ch.chat(cm, returnMessage);

        queue.removeFirst();
    }
}
