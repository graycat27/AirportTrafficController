package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * 空港が持つ情報を定義します
 */
public class Airport implements IMaster {

    //定義情報
    /** 空港名 */
    private String name = "";
    public String getName(){
        return name;
    }

    /* 施設系 */
    /** 滑走路 */
    private List<Runway> runways = new ArrayList<>();
    public List<Runway> getRunways(){
        return new ArrayList<>(runways);
    }

    /** 誘導路 */
    private List<Taxiway> taxiways = new ArrayList<>();
    public List<Taxiway> getTaxiways(){
        return new ArrayList<>(taxiways);
    }

    /** 駐機位置（スポット） */
    private List<Spot> spots = new ArrayList<>();;
    public List<Spot> getSpots(){
        return new ArrayList<>(spots);
    }

    /** 管制塔 */
    private Tower tower = null;
    public Tower getTower(){
        return tower.clone();
    }

    /* 管制系 */
    /** 管制空域 */
    private List<ATCControl> areas = new ArrayList<>();;
    public List<ATCControl> getAtcArea(){
        return new ArrayList<>(areas);
    }



    //コンストラクタ
    private Airport(String name, List<Runway> runway, List<Taxiway> taxiway,
                    List<Spot> spot, Tower tower, List<ATCControl> contol){
        this.name = name;
        this.runways = runway;
        this.taxiways = taxiway;
        this.spots = spot;
        this.tower = tower;
        this.areas = contol;
    }


    //メソッド
    @Override
    public Airport clone(){
        return new Airport(getName(), getRunways(), getTaxiways(),
                getSpots(), getTower(), getAtcArea() );
    }
}
