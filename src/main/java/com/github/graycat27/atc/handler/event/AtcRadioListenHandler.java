package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.defines.atc.AtcMessageData;
import com.github.graycat27.atc.defines.events.AtcRadioSpeakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AtcRadioListenHandler implements Listener {

    @EventHandler
    public void onAtcRadioListen(AtcRadioSpeakEvent event){

        //TODO make this
        System.out.println("radio listened as: " + event.getMessage());

    }

    private AtcMessageData analyzeMessage(String receivedMsg){

        AtcMessageData msg = new AtcMessageData();

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
        }

        //本文の読解と返答の設定
        if(msg.getMessageBody().contains("radio check")){
            msg.setResponseBody("loud and clear.");
        }

        return msg;
    }

}
