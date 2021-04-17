package com.github.graycat27.atc.defines.i;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    /* コンストラクタのテスト */
    @Test
    public void minMax(){
        IPoint min = new ConcretePoint(-100, 0, -150);
        IPoint max = new ConcretePoint(200, 255, 250);
        IArea target = new ConcreteArea(min, max);
        assertAll(
                () -> assertEquals(-100, target.getMinPoint().getX()),
                () -> assertEquals(0, target.getMinPoint().getY()),
                () -> assertEquals(-150, target.getMinPoint().getZ()),
                () -> assertEquals(200, target.getMaxPoint().getX()),
                () -> assertEquals(255, target.getMaxPoint().getY()),
                () -> assertEquals(250, target.getMaxPoint().getZ())
        );
    }

    @Test
    public void maxMin(){
        IPoint min = new ConcretePoint(-100, 0, -150);
        IPoint max = new ConcretePoint(200, 255, 250);
        IArea target = new ConcreteArea(max, min);
        assertAll(
                () -> assertEquals(-100, target.getMinPoint().getX()),
                () -> assertEquals(0, target.getMinPoint().getY()),
                () -> assertEquals(-150, target.getMinPoint().getZ()),
                () -> assertEquals(200, target.getMaxPoint().getX()),
                () -> assertEquals(255, target.getMaxPoint().getY()),
                () -> assertEquals(250, target.getMaxPoint().getZ())
        );
    }
}