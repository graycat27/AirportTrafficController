package com.github.graycat27.atc.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyUtilTest {

    @Test
    public void getChannelNameEmpty(){
        assertEquals("atc_freq_", FrequencyUtil.getChannelName(""));
    }

    @Test
    public void getChannelNameZero(){
        assertEquals("atc_freq_0", FrequencyUtil.getChannelName("0"));
    }

    @Test
    public void getChannelNameNormal(){
        assertEquals("atc_freq_999_9", FrequencyUtil.getChannelName("999.9"));
    }

    @Test
    public void getChannelNameAbnormal(){
        assertEquals("atc_freq___", FrequencyUtil.getChannelName(".."));
    }

    @Test
    public void getFreqFromChannelNameEmpty(){
        assertEquals("", FrequencyUtil.getFreqFromChannelName("atc_freq_"));
    }
    @Test
    public void getFreqFromChannelNameZero(){
        assertEquals("0", FrequencyUtil.getFreqFromChannelName("atc_freq_0"));
    }
    @Test
    public void getFreqFromChannelNameNormal(){
        assertEquals("999.9", FrequencyUtil.getFreqFromChannelName("atc_freq_999_9"));
    }
    @Test
    public void getFreqFromChannelNameAbnormal(){
        assertEquals("..", FrequencyUtil.getFreqFromChannelName("atc_freq___"));
    }

}