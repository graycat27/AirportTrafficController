package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.defines.airport.Airport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/** データを操作するstaticメソッドを持つUtilクラス<br>
 * 空港名をキーに、各種操作をするAPIを持つ */
public class DataUtil {

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
    public static List<String> getAirportNameList(){
        Enumeration<String> keys = data().getAirportList().keys();
        return new ArrayList<>((Collection<? extends String>) keys);
    }

    public static void addAirport(Airport newOne){
        data().addAirport(newOne);
    }

    public static boolean hasSameNameAirport(String name){
        return data().getAirportList().containsKey(name);
    }

    public static Airport getAirportByName(String name){
        return data().getAirportList().get(name).clone();
    }




}
