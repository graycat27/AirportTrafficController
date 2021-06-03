package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.consts.Control;

public class TwrBot extends AtcBot {

    public TwrBot(){
        super(Control.TWR.name());
    }

    @Override
    public String analyzeMessage(String received){
        //TODO make this

        if(received.contains("radio check")){
            return "loud and clear.";
        }

        if(received.contains("request ")){
            String requestedThing = received.substring(received.indexOf("request ")+ "request ".length());
            return "cleared for "+ requestedThing +".";
        }

        if(received.contains("we will ")){
            return "roger.";
        }

        return null;
    }

    @Override
    public String nonIdentifiedSender() {
        return "say your call sign please";
    }
}
