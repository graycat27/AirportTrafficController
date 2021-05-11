package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.i.IMaster;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/** DataUtilを介して使用すること */
public class MasterData implements IMaster {

    //フィールド
    private Hashtable<String, Airport> airportList;
    Hashtable<String, Airport> getAirportList(){
        return new Hashtable<>(airportList);
    }

    //コンストラクタ
    private MasterData(Hashtable<String, Airport> apList){
        this.airportList = apList;
    }

    public MasterData(){
        airportList = new Hashtable<>();
    }

    //メソッド
    void addAirport(@NotNull Airport newOne){
        airportList.put(newOne.getName(), newOne);
    }


    @Override
    public MasterData clone() {
        return new MasterData(getAirportList());
    }
}
