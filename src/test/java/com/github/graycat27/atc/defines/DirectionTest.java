package com.github.graycat27.atc.defines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Directionのテスト */
class DirectionTest {

    /* コンストラクタのテスト */
    /** 正常系 * 最小値(境界) */
    @Test
    void direction1(){
        int v = 1;
        Direction d = new Direction(v);
        assertEquals(v, d.getValue());
    }
    /** 正常系 * 最大値(境界) */
    @Test
    void direction360(){
        int v = 360;
        Direction d = new Direction(v);
        assertEquals(v, d.getValue());
    }
    /** 正常系 * 任意有効値 */
    @Test
    void directionNormal(){
        int v = 27;
        Direction d = new Direction(v);
        assertEquals(v, d.getValue());
    }

    /** 準正常系 * 正方向過大 */
    @Test
    void directionOverflow361(){
        int v = 361;
        Direction d = new Direction(v);
        assertEquals(v-360, d.getValue());
    }
    /** 準正常系 * 正方向過大(27周) */
    @Test
    void directionOverflow(){
        int v = 360*27 + 27;
        Direction d = new Direction(v);
        assertEquals(27, d.getValue());
    }

    /** 準正常系 * ゼロ */
    @Test
    void directionZero(){
        int v = 0;
        Direction d = new Direction(v);
        assertEquals(360, d.getValue());
    }

    /** 準正常系 * 負方向過少 */
    @Test
    void directionN1(){
        int v = -1;
        Direction d = new Direction(v);
        assertEquals(359, d.getValue());
    }
    /** 準正常系 * 負方向過少(27周) */
    @Test
    void directionNUnderflow(){
        int v = -360*27 - 27;
        Direction d = new Direction(v);
        assertEquals(360-27, d.getValue());
    }
}