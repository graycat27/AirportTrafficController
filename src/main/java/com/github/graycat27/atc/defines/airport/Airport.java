package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 空港が持つ情報を定義します
 */
public class Airport implements IMaster {

    //定義情報
    /** 空港名 */
    private final String name;
    public String getName(){
        return name;
    }

    /* 施設系 */
    /** 滑走路 */
    private List<Runway> runways = new ArrayList<>();
    public List<Runway> getRunways(){
        return new ArrayList<>(runways);
    }
    public void addRunway(Runway newOne){
        try{
            Objects.requireNonNull(newOne);
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
        if(hasSameRunway(newOne)) {
            throw new IllegalArgumentException("already has same runway");
        }
        runways.add(newOne);
    }

    /** 誘導路 */
    private List<Taxiway> taxiways = new ArrayList<>();
    public List<Taxiway> getTaxiways(){
        return new ArrayList<>(taxiways);
    }
    public void addTaxiway(Taxiway newOne){
        try{
            Objects.requireNonNull(newOne);
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
        if(hasSameTaxiway(newOne)) {
            throw new IllegalArgumentException("already has same taxiway");
        }
        taxiways.add(newOne);
    }

    /** 駐機位置（スポット） */
    private List<Spot> spots = new ArrayList<>();;
    public List<Spot> getSpots(){
        return new ArrayList<>(spots);
    }
    public void addSpot(Spot newOne){
        try{
            Objects.requireNonNull(newOne);
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
        if(hasSameSpot(newOne)) {
            throw new IllegalArgumentException("already has same spot");
        }
        spots.add(newOne);
    }

    /** 管制塔 */
    private Tower tower = null;
    public Tower getTower(){
        return tower.clone();
    }
    public void setTower(Tower newOne){
        if(tower != null){
            throw new IllegalStateException("already not null");
        }
        try{
            Objects.requireNonNull(newOne);
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
        tower = newOne;
    }

    /* 管制系 */
    /** 管制空域 */
    private List<ATCControl> areas = new ArrayList<>();;
    public List<ATCControl> getAtcArea(){
        return new ArrayList<>(areas);
    }
    public void addControl(ATCControl newOne){
        try{
            Objects.requireNonNull(newOne);
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
        if(hasSameControlType(newOne)) {
            throw new IllegalArgumentException("already has same control");
        }
        areas.add(newOne);
    }

    //コンストラクタ
    private Airport(String name, List<Runway> runway, List<Taxiway> taxiway,
                    List<Spot> spot, Tower tower, List<ATCControl> control){
        // not null check はしない。
        // privateコンストラクタとして適正値であることが担保されているため。

        this.name = name;
        this.runways = runway;
        this.taxiways = taxiway;
        this.spots = spot;
        this.tower = tower;
        this.areas = control;
    }

    public Airport(String name){
        this.name = name;
    }

    //メソッド
    public boolean hasSameRunway(Runway runway){
        for(Runway r : runways){
            if(r.equals(runway)){
                return true;
            }
        }
        return false;
    }

    public boolean hasSameTaxiway(Taxiway taxiway){
        for(Taxiway t : taxiways){
            if(t.equals(taxiway)){
                return true;
            }
        }
        return false;
    }

    public boolean hasSameSpot(Spot spot){
        for(Spot s : spots){
            if(s.equals(spot)){
                return true;
            }
        }
        return false;
    }

    public boolean hasSameControlType(ATCControl control){
        for(ATCControl c : areas){
            if(c.getControl().equals(control.getControl())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Airport clone(){
        return new Airport(getName(), getRunways(), getTaxiways(),
                getSpots(), getTower(), getAtcArea() );
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Tower: ");
        if(tower == null){
            sb.append("is unset (null)");
        }else {
            sb.append("at{").append(tower.getX()).append(", ").append(tower.getY())
                    .append(", ").append(tower.getZ()).append("}");
        }
        sb.append(System.lineSeparator());
        sb.append("ControlArea : ");
        if(areas == null || areas.size() == 0){
            sb.append("unset");
        }else{
            sb.append("[");
            for(ATCControl c : areas){
                sb.append(c.toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
        }
        sb.append(System.lineSeparator());
        sb.append("Runways : ");
        if(runways == null || runways.size() == 0){
            sb.append("unset");
        }else{
            sb.append("[");
            for(Runway r : runways){
                sb.append(r.toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
        }
        sb.append(System.lineSeparator());
        sb.append("Taxiways : ");
        if(taxiways == null || taxiways.size() == 0){
            sb.append("unset");
        }else{
            sb.append("[");
            for(Taxiway t : taxiways){
                sb.append(t.toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
        }
        sb.append(System.lineSeparator());
        sb.append("Spots : ");
        if(spots == null || spots.size() == 0){
            sb.append("unset");
        }else{
            sb.append("[");
            for(Spot s : spots){
                sb.append(s.toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
        }
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
