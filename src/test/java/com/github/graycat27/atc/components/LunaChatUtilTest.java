package com.github.graycat27.atc.components;

import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.channel.StandaloneChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** LunaChatのinstanceが必要なので、コードだけでは実行できない */
class LunaChatUtilTest {

    //@Test
    void testIsNotAtcChannel() {
        Channel c = new StandaloneChannel("test");
        assertFalse(LunaChatUtil.isAtcChannel(c));
    }

    //@Test
    void testIsAtcChannel0_0(){
        Channel c = new StandaloneChannel("atc_freq_0_0");
        assertTrue(LunaChatUtil.isAtcChannel(c));
    }

    //@Test
    void testIsAtcChannel999_9(){
        Channel c = new StandaloneChannel("atc_freq_999_9");
        assertTrue(LunaChatUtil.isAtcChannel(c));
    }
}