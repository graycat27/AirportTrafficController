package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.defines.i.ConcreteFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyTest {

    /* abstract class Frequency */
    @Test
    void getFreq_normal() {
        IFrequency f = new ConcreteFrequency(123, 4);
        assertEquals("123.4", f.getFreq());
    }
    @Test
    void getFreq_min() {
        IFrequency f = new ConcreteFrequency(100, 0);
        assertEquals("100.0", f.getFreq());
    }
    @Test
    void getFreq_max() {
        IFrequency f = new ConcreteFrequency(999,9);
        assertEquals("999.9", f.getFreq());
    }

    @Test
    void setFreq_NG1() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency(IFrequency.MIN_FREQ_N - 1, IFrequency.MIN_FREQ_D)); //99.0
    }
    @Test
    void setFreq_NG2() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency(IFrequency.MAX_FREQ_N + 1, IFrequency.MIN_FREQ_D)); //1000.0
    }
    @Test
    void setFreq_NG3() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency(IFrequency.MAX_FREQ_N, IFrequency.MIN_FREQ_D - 1)); //999.-1
    }
    @Test
    void setFreq_NG4() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency(IFrequency.MAX_FREQ_N, IFrequency.MAX_FREQ_D + 1)); //999.10
    }
    @Test
    void setFreq_OK1() {
        assertDoesNotThrow(() -> new ConcreteFrequency(IFrequency.MIN_FREQ_N, IFrequency.MIN_FREQ_D));
    }
    @Test
    void setFreq_OK2() {
        assertDoesNotThrow(() -> new ConcreteFrequency(IFrequency.MAX_FREQ_N, IFrequency.MAX_FREQ_D));
    }

    @Test
    void setFreqStr_NG1() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("abc.def"));
    }
    @Test
    void setFreqStr_NG2() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("abc.123"));
    }
    @Test
    void setFreqStr_NG3() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("123.def"));
    }
    @Test
    void setFreqStr_NG4() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("abc"));
    }
    @Test
    void setFreqStr_NG5() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("123.456.789"));
    }
    @Test
    void setFreqStr_NG6() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("123.45"));
    }
    @Test
    void setFreqStr_NG7() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConcreteFrequency("1234.5"));
    }
    @Test
    void setFreqStr_OK1_min() {
        String val = "100.0";
        IFrequency f = new ConcreteFrequency(val);
        assertEquals(val, f.getFreq());
    }
    @Test
    void setFreqStr_OK2_max() {
        String val = "999.9";
        IFrequency f = new ConcreteFrequency(val);
        assertEquals(val, f.getFreq());
    }
    @Test
    void setFreqStr_OK3() {

        String val = "123.4";
        IFrequency f = new ConcreteFrequency(val);
        assertEquals(val, f.getFreq());
    }
    @Test
    void setFreqStr_OK4() {
        String val = "123";
        IFrequency f = new ConcreteFrequency(val);
        assertEquals("123.0", f.getFreq());
    }

    /* LunaChat */
}