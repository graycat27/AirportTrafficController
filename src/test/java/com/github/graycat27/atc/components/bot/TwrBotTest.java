package com.github.graycat27.atc.components.bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwrBotTest {

    private TwrBot bot = new TwrBot();
    @Test
    void analyzeMessageNon() {
        assertNull(bot.analyzeMessage(""));
    }
    @Test
    void analyzeMessageNon1() {
        assertNull(bot.analyzeMessage("test"));
    }

    @Test
    void analyzeMessageRadioCheck() {
        assertEquals("loud and clear.", bot.analyzeMessage("radio check"));
    }
    @Test
    void analyzeMessageRadioCheck1(){
        assertEquals("loud and clear.", bot.analyzeMessage("thisisradio checkHowDoYouRead?"));
    }
    @Test
    void analyzeMessageRadioCheckCaps(){
        assertEquals("loud and clear.", bot.analyzeMessage("this is RADIO CHECK. HOW DO YOU READ?"));
    }

    @Test
    void analyzeMessageRequest(){
        assertEquals("cleared for TEST.", bot.analyzeMessage("request TEST"));
    }
    @Test
    void analyzeMessageRequestWithDotEnd(){
        assertEquals("cleared for TEST.", bot.analyzeMessage("request TEST."));
    }
    @Test
    void analyzeMessageRequestCaps(){
        assertEquals("cleared for TEST.", bot.analyzeMessage("REQUEST TEST."));
    }

    @Test
    void analyzeMessageWillAction(){
        assertEquals("roger.", bot.analyzeMessage("we will TEST"));
    }
    @Test
    void analyzeMessageWillActionLong(){
        assertEquals("roger.", bot.analyzeMessage("WE WILL GO AROUND THEN RETRY APCH"));
    }
}