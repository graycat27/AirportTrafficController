package com.github.graycat27.atc.defines.i;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPathTest {

    /*
     * class ConcretePath extends Path{ }
     */
    static class Po_0  { static final int x = 0; static final int y = 0; static final int z = 0; }
    static class Po_64 { static final int x = 0; static final int y = 64; static final int z = 0; }
    static class P1_64 { static final int x = 100; static final int y = 64; static final int z = 100; }
    static class P2_64 { static final int x = 200; static final int y = 64; static final int z = 200; }
    static class P3_120 { static final int x = 270; static final int y = 120; static final int z = 270; }
    static class P1n64 { static final int x = -100; static final int y = 64; static final int z = -100; }
    
    @Test
    void length() {
    }

    @Test
    void levelLength() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testToString() {
    }


    @Test
    void isClosed() {
        //TODO make test
        // is..がtrueのケース
        // is..がfalseのケース
    }

    @Test
    void isOnPath() {
        //TODO make test
        // t/f の2ケース
    }

    @Test
    void isOnLevelPath() {
        //TODO make test
        // 三次元true, 水平面true, false の3ケース
    }

    /* levelEquals */
    /** 正常系 始終点が完全一致（equals()でもtrueのもの） */
    @Test
    void levelEquals_1() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /** 準正常系 始点が水平方向で不一致 */
    @Test
    void levelEquals_1_f1() {
        IPoint ps = new ConcretePoint(P1n64.x, P1n64.y, P1n64.z);
        IPoint psd = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(psd);    //不一致ポイント
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertFalse(path1.levelEquals(path2));
        assertFalse(path2.levelEquals(path1));
    }

    /** 準正常系 終点が水平面で不一致 */
    @Test
    void levelEquals_1_f2() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint ped = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(ped);    //不一致ポイント

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertFalse(path1.levelEquals(path2));
        assertFalse(path2.levelEquals(path1));
    }

    /** 正常系 始終点が高度違いで完全一致（equals()ではfalseのもの） */
    @Test
    void levelEquals_2() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pe2 = new ConcretePoint(P1_64.x, 0, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pe2);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /** 正常系 始終点が完全一致、経由点(1つ)が高度違い */
    @Test
    void levelEquals_3() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(P1_64.x, 0, P1_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pw2);
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /** 準正常系 始終点が一致、経由点(1つ)は不一致 */
    @Test
    void levelEquals_3_f1() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pw2);    //不一致
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertFalse(path1.levelEquals(path2));
        assertFalse(path2.levelEquals(path1));
    }

    /** 正常系 始終点が逆順一致、経由点(1つ)が一致 */
    @Test
    void levelEquals_4() {
        IPoint ps = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        IPoint pe0 = new ConcretePoint(P2_64.x, 0, P2_64.z);
        IPoint pw10 = new ConcretePoint(P1_64.x, 0, P1_64.z);
        IPoint ps0 = new ConcretePoint(Po_64.x, 0, Po_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(pe0);     //逆順 y=0
        plist2.add(pw10);
        plist2.add(ps0);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /** 正常系 始終点、経由点(複数) 高度違い */
    @Test
    void levelEquals_5() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(P3_120.x, P3_120.y, P3_120.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);
        IPoint ps_2 = new ConcretePoint(Po_0.x, 27, Po_0.z);
        IPoint pw1_2 = new ConcretePoint(P1_64.x, 27, P1_64.z);
        IPoint pw2_2 = new ConcretePoint(P3_120.x, 27, P3_120.z);
        IPoint pe_2 = new ConcretePoint(P2_64.x, 27, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pw2);
        plist1.add(pe);
        plist2.add(ps_2);
        plist2.add(pw1_2);
        plist2.add(pw2_2);
        plist2.add(pe_2);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /** 正常系 始終点、経由点(複数) 逆順一致 高度違い */
    @Test
    void levelEquals_6() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(P3_120.x, P3_120.y, P3_120.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);
        IPoint ps_2 = new ConcretePoint(Po_0.x, 27, Po_0.z);
        IPoint pw1_2 = new ConcretePoint(P1_64.x, 27, P1_64.z);
        IPoint pw2_2 = new ConcretePoint(P3_120.x, 27, P3_120.z);
        IPoint pe_2 = new ConcretePoint(P2_64.x, 27, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pw2);
        plist1.add(pe);
        plist2.add(pe_2);     //逆順
        plist2.add(pw2_2);
        plist2.add(pw1_2);
        plist2.add(ps_2);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertTrue(path1.levelEquals(path2));
        assertTrue(path2.levelEquals(path1));
    }

    /* equals */
    /** 正常系 始終点が完全一致 */
    @Test
    void equals_1() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);

    }

    /** 準正常系 始点が不一致 */
    @Test
    void equals_1_f1() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint psd = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(psd);    //不一致ポイント
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertNotEquals(path1, path2);
        assertNotEquals(path2, path1);
    }

    /** 準正常系 終点が不一致 */
    @Test
    void equals_1_f2() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint ped = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(ped);    //不一致ポイント

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertNotEquals(path1, path2);
        assertNotEquals(path2, path1);
    }

    /** 正常系 始終点が逆順一致 */
    @Test
    void equals_2() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pe);
        plist2.add(pe); //逆順
        plist2.add(ps);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);

    }

    /** 正常系 始終点と経由点(1つ)が完全一致 */
    @Test
    void equals_3() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pw1);
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);
    }

    /** 準正常系 始終点が一致、経由点(1つ)は不一致 */
    @Test
    void equals_3_f1() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pw2);    //不一致
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertNotEquals(path1, path2);
        assertNotEquals(path2, path1);
    }

    /** 正常系 始終点が逆順一致、経由点(1つ)が一致 */
    @Test
    void equals_4() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pe);
        plist2.add(pe);     //逆順
        plist2.add(pw1);    //経由一致
        plist2.add(ps);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);
    }

    /** 正常系 始終点、経由点(複数) 完全一致 */
    @Test
    void equals_5() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(P3_120.x, P3_120.y, P3_120.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pw2);
        plist1.add(pe);
        plist2.add(ps);
        plist2.add(pw1);
        plist2.add(pw2);
        plist2.add(pe);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);
    }

    /** 正常系 始終点、経由点(複数) 逆順一致 */
    @Test
    void equals_6() {
        IPoint ps = new ConcretePoint(Po_0.x, Po_0.y, Po_0.z);
        IPoint pw1 = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
        IPoint pw2 = new ConcretePoint(P3_120.x, P3_120.y, P3_120.z);
        IPoint pe = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);

        List<IPoint> plist1 = new ArrayList<>();
        List<IPoint> plist2 = new ArrayList<>();
        plist1.add(ps);
        plist1.add(pw1);
        plist1.add(pw2);
        plist1.add(pe);
        plist2.add(pe);     //逆順
        plist2.add(pw2);
        plist2.add(pw1);
        plist2.add(ps);

        AbstractPath path1 = new ConcretePath(plist1);
        AbstractPath path2 = new ConcretePath(plist2);

        assertEquals(path1, path2);
        assertEquals(path2, path1);
    }

//    /**
//     * 正常系 * 同じ線形で、経由点数が異なるパターン
//     * 経由点数、[0,1] */
//    @Test
//    void equals_7() {
//        IPoint ps = new ConcretePoint(P1n64.x, P1n64.y, P1n64.z);
//        IPoint pw1 = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
//        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
//
//        List<IPoint> plist1 = new ArrayList<>();
//        List<IPoint> plist2 = new ArrayList<>();
//        plist1.add(ps);
//        //plist1.add(pw1);
//        plist1.add(pe);
//        plist2.add(ps);
//        plist2.add(pw1);
//        plist2.add(pe);
//
//        Path path1 = new ConcretePath(plist1);
//        Path path2 = new ConcretePath(plist2);
//
//        assertTrue(path1.equals(path2));
//        assertTrue(path2.equals(path1));
//    }
//
//    /**
//     * 準正常系 * 異なる線形で、経由点数が同じパターン
//     * 経由点数、[1,1] */
//    @Test
//    void equals_8() {
//        IPoint ps = new ConcretePoint(P1n64.x, P1n64.y, P1n64.z);
//        IPoint pw1 = new ConcretePoint(Po_64.x, Po_64.y, Po_64.z);
//        IPoint pw2 = new ConcretePoint(P2_64.x, P2_64.y, P2_64.z);
//        IPoint pe = new ConcretePoint(P1_64.x, P1_64.y, P1_64.z);
//
//        List<IPoint> plist1 = new ArrayList<>();
//        List<IPoint> plist2 = new ArrayList<>();
//        plist1.add(ps);
//        plist1.add(pw1);
//        plist1.add(pe);
//        plist2.add(ps);
//        plist2.add(pw2);    //経由地点が異なる
//        plist2.add(pe);
//
//        Path path1 = new ConcretePath(plist1);
//        Path path2 = new ConcretePath(plist2);
//
//        assertFalse(path1.equals(path2));
//        assertFalse(path2.equals(path1));
//    }

}