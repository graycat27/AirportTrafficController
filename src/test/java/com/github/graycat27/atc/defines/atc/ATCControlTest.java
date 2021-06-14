package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.i.*;
import com.github.graycat27.atc.defines.sky.ATCArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATCControlTest {

    private final IPoint start = new ConcretePoint(0, 0, 0);
    private final IPoint end1 = new ConcretePoint(100, 100, 100);
    private final ATCArea area1 = new ATCArea(start, end1);
    private final IPoint end2 = new ConcretePoint(200, 200, 200);
    private final ATCArea area2 = new ATCArea(start, end2);

    @Test
    void testEquals() {
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        assertEquals(c1, c1);
    }

    @Test
    void testEqualsSameParam() {
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        assertEquals(c1, c2);
    }

    @Test
    void testEqualsSameParamNull() {
        ATCControl c1 = new ATCControl(
                null, null, null
        );
        ATCControl c2 = new ATCControl(
                null, null, null
        );
        assertEquals(c1, c2);
    }

    @Test
    void testNonEqualsCtrl(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.TWR, new ConcreteFrequency(222, 2), area1
        );
        assertNotEquals(c1, c2);
    }

    @Test
    void testNonEqualsCtrlNull(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                null, new ConcreteFrequency(222, 2), area1
        );
        assertNotEquals(c1, c2);
    }

    @Test
    void testNonEqualsFreq(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.CTL, new ConcreteFrequency(272, 7), area1
        );
        assertNotEquals(c1, c2);
    }

    @Test
    void testNonEqualsFreqNull(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.CTL, null, area1
        );
        assertNotEquals(c1, c2);
    }

    @Test
    void testNonEqualsArea(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area2
        );
        assertNotEquals(c1, c2);
    }

    @Test
    void testNonEqualsAreaNull(){
        ATCControl c1 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), area1
        );
        ATCControl c2 = new ATCControl(
                Control.CTL, new ConcreteFrequency(222, 2), null
        );
        assertNotEquals(c1, c2);
    }
}