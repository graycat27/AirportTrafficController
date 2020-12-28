package com.github.graycat27.atc.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneticCodeTest {

    @Test
    void getPhoneticCodeCharA() {
        assertEquals("Alpha", PhoneticCode.getPhoneticCode('A'));
    }

    @Test
    void getPhoneticCodeStrZ() {
        assertEquals("Zulu", PhoneticCode.getPhoneticCode("Z"));
    }

    @Test
    void getPhoneticCodeStrGray27(){
        assertEquals("Golf Romeo Alpha Yankee Two Seven", PhoneticCode.getPhoneticCode("Gray27"));
    }

    @Test
    void getPhoneticCodeStrWithJP(){
        assertEquals("これは、テストです。", PhoneticCode.getPhoneticCode("これは、テストです。"));
    }

    @Test
    void getPhoneticCodeStrMix(){
        assertEquals("これはTango Echo Sierra Tango Two Seven ﾃﾞス"
                , PhoneticCode.getPhoneticCode("これはtEｓＴ2７ﾃﾞス"));
    }

}