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

    /** class for Test AtcRadioListenHandler */
    private static class AtcRadioListenHandlerTester extends AtcRadioListenHandler{

        @Override
        protected AtcMessageData analyzeMessage(String freq, String receivedMsg){
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

    /** テスト対象がprotectedメソッドなので、リフレクションによる呼出しをする処理 */
    private AtcMessageData analyzeMessageTester(Control control, String message) {
        AtcMessageData result;
        try {
            Method method = AtcRadioListenHandlerTester.class.getDeclaredMethod("analyzeMessage", String.class, String.class);
            method.setAccessible(true);

            String freq = Control.CTL.equals(control) ? "272.7" : "222.2";
            AtcRadioListenHandlerTester targetClass = new AtcRadioListenHandlerTester();
            result = (AtcMessageData) method.invoke(targetClass, freq, message);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private final String allStationRadioCheck = "all station, gray27. This is radio check. How do you read?";

    @Test
    public void testSimpleMessageFrequency(){
        AtcMessageData result = analyzeMessageTester(Control.CTL, allStationRadioCheck);
        assertEquals("272.7", result.getFrequency());
    }

    @Test
    public void testSimpleMessageSender(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,allStationRadioCheck);
        assertEquals("gray27" ,result.getSender());
    }
    @Test
    public void testSimpleMessageReceiver(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,allStationRadioCheck);
        assertEquals("all station" ,result.getReceiver());
    }
    @Test
    public void testSimpleMessageBody(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,allStationRadioCheck);
        assertEquals("This is radio check. How do you read. " ,result.getMessageBody());
    }

    @Test
    public void testRadioCheckResponseCtl(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,allStationRadioCheck);
        assertEquals("loud and clear." ,result.getResponseBody());
    }
    @Test
    public void testRadioCheckIsNeedResponse(){
        AtcMessageData result = analyzeMessageTester(Control.CTL,"all station, gray27. This is radio check. How do you read?");
        assertTrue(result.isNeedResponse());
    }

    @Test
    public void testRadioCheckResponseTwr(){
        AtcMessageData result = analyzeMessageTester(Control.TWR,"all station, gray27. This is radio check. How do you read?");
        assertEquals("loud and clear." ,result.getResponseBody());
    }

    @Test
    public void testRequestResponseTwr(){
        AtcMessageData result = analyzeMessageTester(Control.TWR,
                "twr, gray27. we request landing.");
        assertEquals("cleared for landing.", result.getResponseBody());
    }

    @Test
    public void testRequestResponseNoPeriodTwr(){
        AtcMessageData result = analyzeMessageTester(Control.TWR,
                "twr, gray27. we request landing");
        assertEquals("cleared for landing.", result.getResponseBody());
    }

    @Test
    public void testRequestResponseLongRequestTwr(){
        AtcMessageData result = analyzeMessageTester(Control.TWR,
                "twr, gray27. we request approach and fly over rwy");
        assertEquals("cleared for approach and fly over rwy.", result.getResponseBody());
    }

}