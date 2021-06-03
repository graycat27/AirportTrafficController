package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.defines.events.AtcRadioSpeakEvent;
import com.github.graycat27.atc.utils.AtcDictionary;

public class ChatChecker {

    public static String getMessage(String freq, String original){
        String convertMsg = wordConverter(original);
        AirportTrafficController.getRadioListener().onAtcRadioListen(new AtcRadioSpeakEvent(freq, convertMsg));
        return convertMsg;
    }

    private static String wordConverter(String original){
        String[] words = original.split(" ");
        StringBuilder result = new StringBuilder();

        int len = words.length;
        for(int i = 0; i < len; i++){
            result.append(AtcDictionary.get(words[i])).append(" ");
        }
        return result.toString();
    }

}
