package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.defines.atc.AtcMessageData;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AtcRadioListenHandlerTest {


    private AtcMessageData analyzeMessageTester(String message) {
        AtcMessageData result;
        try {
            Method method = AtcRadioListenHandler.class.getDeclaredMethod("analyzeMessage", String.class);
            method.setAccessible(true);

            AtcRadioListenHandler targetClass = new AtcRadioListenHandler();
            result = (AtcMessageData) method.invoke(targetClass, message);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Test
    public void testSimpleMessageSender(){
        AtcMessageData result = analyzeMessageTester("all station, gray27. This is radio check. How do you read?");
        assertEquals("gray27" ,result.getSender());
    }
    @Test
    public void testSimpleMessageReceiver(){
        AtcMessageData result = analyzeMessageTester("all station, gray27. This is radio check. How do you read?");
        assertEquals("all station" ,result.getReceiver());
    }
    @Test
    public void testSimpleMessageBody(){
        AtcMessageData result = analyzeMessageTester("all station, gray27. This is radio check. How do you read?");
        assertEquals("This is radio check. How do you read. " ,result.getMessageBody());
    }

    @Test
    public void testRadioCheckResponse(){
        AtcMessageData result = analyzeMessageTester("all station, gray27. This is radio check. How do you read?");
        assertEquals("loud and clear." ,result.getResponseBody());
    }
    @Test
    public void testRadioCheckIsNeedResponse(){
        AtcMessageData result = analyzeMessageTester("all station, gray27. This is radio check. How do you read?");
        assertTrue(result.isNeedResponse());
    }

}