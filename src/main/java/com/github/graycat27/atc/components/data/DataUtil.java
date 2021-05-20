package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.airport.Runway;
import com.github.graycat27.atc.defines.airport.Spot;
import com.github.graycat27.atc.defines.airport.Taxiway;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.sky.ATCArea;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/** データを操作するstaticメソッドを持つUtilクラス<br>
 * 空港名をキーに、各種操作をするAPIを持つ */
public final class DataUtil {

    // フィールド

    // コンストラクタ
    private DataUtil(){
        //for not be instanced
    }

    // メソッド
    private static MasterData data(){
        return AirportTrafficController.getDataManager().getData();
    }

    // API
    /* Airport */
    public static List<String> getAirportNameList(){
        Enumeration<String> keys = data().getAirportList().keys();
        return Collections.list(keys);
    }

    public static void addAirport(Airport newOne){
        data().addAirport(newOne);
    }

    public static boolean hasSameNameAirport(String airportName){
        if(airportName == null || airportName.length() == 0){
            throw new IllegalArgumentException("name param is empty");
        }
        return data().getAirportList().containsKey(airportName);
    }

    public static Airport getAirportByName(String airportName){
        Airport ap = data().getAirportList().get(airportName);
        if(ap == null){
            throw new IllegalArgumentException("there is no such name airport");
        }
        return ap.clone();
    }

    public static Airport getAirportByAtcName(String atcName){
        for(Airport ap : data().getAirportList().values()){
            if(atcName.equals(ap.getAtcName())){
                return ap.clone();
            }
        }
        throw new IllegalArgumentException("there is no such atc-name airport");
    }

    public static void addRunwayToAirport(String airportName, Runway newOne){
        data().getAirportList().get(airportName).addRunway(newOne);
    }

    public static void addTaxiwayToAirport(String airportName, Taxiway newOne){
        data().getAirportList().get(airportName).addTaxiway(newOne);
    }

    public static void addSpotToAirport(String airportName, Spot newOne){
        data().getAirportList().get(airportName).addSpot(newOne);
    }

    public static void setAtcFreqToAirport(String airportName, Control control, IFrequency freq){
        data().getAirportList().get(airportName).setFreqToAtcControl(control, freq);
    }

    public static void setAtcAreaToAirport(String airportName, Control control, ATCArea area){
        data().getAirportList().get(airportName).setAreaToAtcControl(control, area);
    }

    public static void setAtcNameToAirport(String airportName, String atcName){
        data().getAirportList().get(airportName).setAtcName(atcName);
    }

    public static boolean hasSameAtcNameAirport(String atcName){
        if(atcName == null || atcName.length() == 0){
            return false;
        }
        for(Airport ap : data().getAirportList().values()){
            if(atcName.equals(ap.getAtcName())){
                return true;
            }
        }
        return false;
    }

}
