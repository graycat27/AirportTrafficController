package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.consts.Control;

public class CtlBot extends AtcBot {

    public CtlBot(){
        super(Control.CTL.name());
    }

    @Override
    public String analyzeMessage(String received){
        //TODO make this
        String receivedBody = received.toLowerCase();

        if(receivedBody.contains("radio check")){
            return "loud and clear.";
        }

        return null;
    }

}
