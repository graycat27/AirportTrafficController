package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.defines.atc.AtcMessageData;
import com.github.graycat27.atc.defines.events.AtcRadioSpeakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AtcRadioListenHandler implements Listener {

    @EventHandler
    public void onAtcRadioListen(AtcRadioSpeakEvent event){
        AirportTrafficController.pushAtcResponseTask(analyzeMessage(event.getFreq(), event.getMessage()));
    }

    private AtcMessageData analyzeMessage(String freq, String receivedMsg){

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
        if(msg.getMessageBody().contains("radio check")){
            msg.setResponseBody("loud and clear.");
        }

        return msg;
    }

}
