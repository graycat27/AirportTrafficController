package com.github.graycat27.atc.components;

import com.github.graycat27.atc.defines.Direction;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionUtilTest {


    /* getDirectionBetweenPoints */
    /** 北 */
    @Test
    void getDirectionBetweenPoints_North() {
        IPoint pBase = new ConcretePoint(0, 64, 0);
        IPoint pTarget = new ConcretePoint(0, 100, -100);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(360, dir.getValue());
    }
    /** 東 */
    @Test
    void getDirectionBetweenPoints_East() {
        IPoint pBase = new ConcretePoint(0, 64, 0);
        IPoint pTarget = new ConcretePoint(100, 100, 0);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(90, dir.getValue());
    }
    /** 南 */
    @Test
    void getDirectionBetweenPoints_South() {
        IPoint pBase = new ConcretePoint(0, 64, 0);
        IPoint pTarget = new ConcretePoint(0, 100, 100);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(180, dir.getValue());
    }
    /** 西 */
    @Test
    void getDirectionBetweenPoints_West() {
        IPoint pBase = new ConcretePoint(0, 64, 0);
        IPoint pTarget = new ConcretePoint(-100, 100, 0);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(270, dir.getValue());
    }
    /** 北東 */
    @Test
    void getDirectionBetweenPoints_NE() {
        IPoint pBase = new ConcretePoint(0, 64, 0);
        IPoint pTarget = new ConcretePoint(100, 100, -100);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(45, dir.getValue());
    }
    /** 南東 */
    @Test
    void getDirectionBetweenPoints_SE(){
        IPoint pBase = new ConcretePoint(100, 64, 100);
        IPoint pTarget = new ConcretePoint(200, 100, 200);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(90+45, dir.getValue());
    }
    /** 南西 */
    @Test
    void getDirectionBetweenPoints_SW(){
        IPoint pBase = new ConcretePoint(100, 64, 100);
        IPoint pTarget = new ConcretePoint(0, 100, 200);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(180+45, dir.getValue());
    }
    /** 北西 */
    @Test
    void getDirectionBetweenPoints_NW(){
        IPoint pBase = new ConcretePoint(100, 64, 100);
        IPoint pTarget = new ConcretePoint(-200, 100, -200);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pTarget);
        assertEquals(270+45, dir.getValue());
    }
    /** 同地点 */
    @Test
    void getDirectionBetweenPoints_Same(){
        IPoint pBase = new ConcretePoint(100, 64, 100);
        Direction dir = DirectionUtil.getDirectionBetweenPoints(pBase, pBase);
        assertEquals(360, dir.getValue());
    }
}