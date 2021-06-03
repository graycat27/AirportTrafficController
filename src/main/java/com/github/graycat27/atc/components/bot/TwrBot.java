package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.consts.Control;

public class TwrBot extends AtcBot {

    public TwrBot(){
        super(Control.TWR.name());
    }

    @Override
    public String analyzeMessage(final String received){
        //TODO make this
        String receivedBody = received.toLowerCase();

        if(receivedBody.contains("radio check")){
            return "loud and clear.";
        }

        if(receivedBody.contains("request ")){
            String requestedThing = received.substring(receivedBody.indexOf("request ")+ "request ".length());
            if(!requestedThing.endsWith(".")){
                requestedThing += ".";
            }
            return "cleared for "+ requestedThing;
        }

        if(receivedBody.contains("we will ")){
            return "roger.";
        }

        return null;
    }

    @Override
    public String nonIdentifiedSender() {
        return "say your call sign please";
    }
}
