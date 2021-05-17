package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.ConcreteFrequency;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.sky.ATCArea;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    //フィールド
    String name = "4irρ0rt";
    List<IPoint> rwy18_36 = new ArrayList<>(){
        {
            add(new ConcretePoint(100, 64, 100));
            add(new ConcretePoint(100, 64, 200));
        }
    };
    List<IPoint> rwy09_27 = new ArrayList<>(){
        {
            add(new ConcretePoint(150, 64, 100));
            add(new ConcretePoint(250, 64, 100));
        }
    };
    ATCArea area = new ATCArea(
            new ConcretePoint(0, 0, 0),
            new ConcretePoint(200, 255, 200)
    );
    ATCArea area2 = new ATCArea(
            new ConcretePoint(100, 64, 100),
            new ConcretePoint(250, 200, 250)
    );

    //テスト
    /* Runway */
    @Test
    void addRunway1() {
        Airport ap = new Airport(name);
        Runway rw = new Runway("A", rwy18_36);
        ap.addRunway(rw);
        assertEquals(rw, ap.getRunways().get(0));
    }

    @Test
    void hasSameRunway1() {
        Airport ap = new Airport(name);
        Runway rw = new Runway("A", rwy18_36);
        ap.addRunway(rw);
        assertTrue(ap.hasSameRunway(rw));
    }

    @Test
    void hasSameRunway2() {
        Airport ap = new Airport(name);
        Runway rw = new Runway("A", rwy18_36);
        ap.addRunway(rw);
        Runway rw2 = new Runway("B", rwy18_36);
        assertTrue(ap.hasSameRunway(rw2));
    }

    @Test
    void hasSameRunway3() {
        Airport ap = new Airport(name);
        Runway rw = new Runway("A", rwy18_36);
        ap.addRunway(rw);
        Runway rw2 = new Runway("B", rwy09_27);
        assertFalse(ap.hasSameRunway(rw2));
    }

    @Test
    void addRunway2() {
        Airport ap = new Airport(name);
        Runway rw = new Runway("A", rwy18_36);
        ap.addRunway(rw);
        Runway rw2 = new Runway("B", rwy18_36);
        assertThrows(IllegalArgumentException.class, ()->{ap.addRunway(rw2);});
    }

    @Test
    void addRunway3(){
        Airport ap = new Airport(name);
        assertThrows(IllegalArgumentException.class, ()->{ap.addRunway(null);});
    }

    /* Taxiway */
    @Test
    void addTaxiway1() {
        Airport ap = new Airport(name);
        Taxiway twy = new Taxiway("A", rwy18_36);
        ap.addTaxiway(twy);
        assertEquals(twy, ap.getTaxiways().get(0));
    }

    @Test
    void hasSameTaxiway1() {
        Airport ap = new Airport(name);
        Taxiway twy = new Taxiway("A", rwy18_36);
        ap.addTaxiway(twy);
        assertTrue(ap.hasSameTaxiway(twy));
    }

    @Test
    void hasSameTaxiway2() {
        Airport ap = new Airport(name);
        Taxiway twy = new Taxiway("A", rwy18_36);
        ap.addTaxiway(twy);
        Taxiway twy2 = new Taxiway("B", rwy18_36);
        assertTrue(ap.hasSameTaxiway(twy2));
    }

    @Test
    void hasSameTaxiway3() {
        Airport ap = new Airport(name);
        Taxiway twy = new Taxiway("A", rwy18_36);
        ap.addTaxiway(twy);
        Taxiway twy2 = new Taxiway("B", rwy09_27);
        assertFalse(ap.hasSameTaxiway(twy2));
    }

    @Test
    void addTaxiway2() {
        Airport ap = new Airport(name);
        Taxiway twy = new Taxiway("A", rwy18_36);
        ap.addTaxiway(twy);
        Taxiway twy2 = new Taxiway("B", rwy18_36);
        assertThrows(IllegalArgumentException.class, ()->{ap.addTaxiway(twy2);});
    }

    @Test
    void addTaxiway3(){
        Airport ap = new Airport(name);
        assertThrows(IllegalArgumentException.class, ()->{ap.addTaxiway(null);});
    }

    /* Spot */
    @Test
    void addSpot1() {
        Airport ap = new Airport(name);
        Spot sp = new Spot("1", 75, 64, 75);
        ap.addSpot(sp);
        assertEquals(sp, ap.getSpots().get(0));
    }

    @Test
    void hasSameSpot1() {
        Airport ap = new Airport(name);
        Spot sp = new Spot("1", 75, 64, 75);
        ap.addSpot(sp);
        assertTrue(ap.hasSameSpot(sp));
    }

    @Test
    void hasSameSpot2() {
        Airport ap = new Airport(name);
        Spot sp = new Spot("1", 75, 64, 75);
        ap.addSpot(sp);
        Spot sp2 = new Spot("1", 75, 64, 75);
        assertTrue(ap.hasSameSpot(sp2));
    }

    @Test
    void hasSameSpot3() {
        Airport ap = new Airport(name);
        Spot sp = new Spot("1", 75, 64, 75);
        ap.addSpot(sp);
        Spot sp2 = new Spot("2", 175, 64, 175);
        assertFalse(ap.hasSameSpot(sp2));
    }

    @Test
    void addSpot2() {
        Airport ap = new Airport(name);
        Spot sp = new Spot("1", 75, 64, 75);
        ap.addSpot(sp);
        Spot sp2 = new Spot("2", 75, 64, 75);
        assertThrows(IllegalArgumentException.class, ()->{ap.addSpot(sp2);});
    }

    @Test
    void addSpot3(){
        Airport ap = new Airport(name);
        assertThrows(IllegalArgumentException.class, ()->{ap.addSpot(null);});
    }

    /* Tower */
    @Test
    void setTower1() {
        Airport ap = new Airport(name);
        Tower tw = new Tower(80, 100, 80);
        ap.setTower(tw);
        assertEquals(tw, ap.getTower());
    }

    @Test
    void setTower2(){
        Airport ap = new Airport(name);
        Tower tw = new Tower(80, 100, 80);
        ap.setTower(tw);
        assertThrows(IllegalStateException.class, ()->{ap.setTower(tw);});
    }

    @Test
    void setTower3(){
        Airport ap = new Airport(name);
        assertThrows(IllegalArgumentException.class, ()->{ap.setTower(null);});
    }

    /* Control */
    @Test
    void addControl1() {
        Airport ap = new Airport(name);
        ATCControl ctrl = new ATCControl(Control.TWR, new ConcreteFrequency(222,2), area);
        ap.addControl(ctrl);
        assertEquals(ctrl, ap.getAtcArea().get(0));
    }

    @Test
    void hasSameControlType1() {
        Airport ap = new Airport(name);
        ATCControl ctrl = new ATCControl(Control.TWR, new ConcreteFrequency(222,2), area);
        ap.addControl(ctrl);
        assertTrue(ap.hasSameControlType(ctrl));
    }

    @Test
    void hasSameControlType2(){
        Airport ap = new Airport(name);
        ATCControl ctrl = new ATCControl(Control.TWR, new ConcreteFrequency(222,2), area);
        ap.addControl(ctrl);
        ATCControl ctrl2 = new ATCControl(Control.TWR, new ConcreteFrequency(272, 7), area2);
        assertTrue(ap.hasSameControlType(ctrl2));
    }

    @Test
    void hasSameControlType3(){
        Airport ap = new Airport(name);
        ATCControl ctrl = new ATCControl(Control.TWR, new ConcreteFrequency(222,2), area);
        ap.addControl(ctrl);
        ATCControl ctrl2 = new ATCControl(Control.CTL, new ConcreteFrequency(222,2), area);
        assertFalse(ap.hasSameControlType(ctrl2));
    }
}