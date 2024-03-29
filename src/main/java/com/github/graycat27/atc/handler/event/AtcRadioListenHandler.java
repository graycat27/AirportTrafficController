package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.LunaChatUtil;
import com.github.graycat27.atc.components.bot.IAtcBot;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.atc.AtcMessageData;
import com.github.graycat27.atc.defines.events.AtcRadioSpeakEvent;
import com.github.graycat27.atc.defines.i.ConcreteFrequency;

public class AtcRadioListenHandler {

    public void onAtcRadioListen(AtcRadioSpeakEvent event){
        AirportTrafficController.pushAtcResponseTask(analyzeMessage(event.getFreq(), event.getMessage()));
    }

    protected AtcMessageData analyzeMessage(String freq, String receivedMsg){

        AtcMessageData msg = new AtcMessageData();

        //基本データの設定
        msg.setFrequency(freq);

        //発話者・宛先の解読
        String[] phrases = receivedMsg.split("[.,?!。、｡､]");    //句読点で区切る

        if(phrases.length >= 3){
            msg.setReceiver(phrases[0].trim());
            msg.setSender(phrases[1].trim());
            StringBuilder body = new StringBuilder();
            int bodyIdx = 2;
            for( ; bodyIdx<phrases.length; bodyIdx++){
                body.append(phrases[bodyIdx].trim()).append(". ");
            }
            msg.setMsgBody(body.toString());
        }else{
            //FIXME 解読パターンの追加
            msg.setMsgBody(receivedMsg);
        }

        //本文の読解と返答の設定
        IAtcBot atcBot = getBot(freq);
        if(atcBot != null) {
            msg.setResponseBody(atcBot.analyzeMessage(msg.getMessageBody()));
        }

        return msg;
    }

    protected IAtcBot getBot(String freq){
        ATCControl control = FrequencyUtil.getAtcControl(new ConcreteFrequency(freq));
        return LunaChatUtil.getChannelMember(control);
    }

}
