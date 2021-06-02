package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.components.bot.CtlBot;
import com.github.graycat27.atc.components.bot.IAtcBot;
import com.github.graycat27.atc.components.bot.TwrBot;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.atc.AtcMessageData;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AtcRadioListenHandlerTest {

    private static class AtcRadioListenHandlerTester extends AtcRadioListenHandler{

        public AtcMessageData analyzeMessage(String freq, String receivedMsg){
            return super.analyzeMessage(freq, receivedMsg);
        }

        @Override
        protected IAtcBot getBot(String freq){
            switch (freq){
                case "272.7":
                    return new CtlBot();
                case "222.2":
                    return new TwrBot();
            }
            return null;
        }
    }

    private AtcMessageData analyzeMessageTester(Control control, String message) {
        AtcMessageData result;
        try {
            Method method = AtcRadioListenHandlerTester.class.getDeclaredMethod("analyzeMessage", String.class, String.class);
            method.setAccessible(true);

            String freq = control.name().equals(Control.CTL.name()) ? "272.7" : "222.2";
            AtcRadioListenHandlerTester targetClass = new AtcRadioListenHandlerTester();
            result = (AtcMessageData) method.invoke(targetClass, freq, message);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Test
    public void testSimpleMessageFrequency(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertEquals("272.7", result.getFrequency());
    }

    @Test
    public void testSimpleMessageSender(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertEquals("gray27" ,result.getSender());
    }
    @Test
    public void testSimpleMessageReceiver(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertEquals("all station" ,result.getReceiver());
    }
    @Test
    public void testSimpleMessageBody(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertEquals("This is radio check. How do you read. " ,result.getMessageBody());
    }

    @Test
    public void testRadioCheckResponse(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertEquals("loud and clear." ,result.getResponseBody());
    }
    @Test
    public void testRadioCheckIsNeedResponse(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertTrue(result.isNeedResponse());
    }

}