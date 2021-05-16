package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.airport.Runway;
import com.github.graycat27.atc.defines.airport.Spot;
import com.github.graycat27.atc.defines.airport.Taxiway;
import com.github.graycat27.atc.defines.atc.ATCControl;

import java.util.ArrayList;
import java.util.Collection;
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
        return new ArrayList<>((Collection<? extends String>) keys);
    }

    public static void addAirport(Airport newOne){
        data().addAirport(newOne);
    }

    public static boolean hasSameNameAirport(String airportName){
        return data().getAirportList().containsKey(airportName);
    }

    public static Airport getAirportByName(String airportName){
        return data().getAirportList().get(airportName).clone();
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

    public static void addControlToAirport(String airportName, ATCControl newOne){
        data().getAirportList().get(airportName).addControl(newOne);
    }



}
