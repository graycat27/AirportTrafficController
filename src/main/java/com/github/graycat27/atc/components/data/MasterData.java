package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.i.IMaster;
import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

/** DataUtilを介して使用すること */
public class MasterData implements IMaster {

    //フィールド
    private Hashtable<String, Airport> airportList;
    public Hashtable<String, Airport> getAirportList(){
        return new Hashtable<>(airportList);
    }

    //コンストラクタ
    public MasterData(Hashtable<String, Airport> apList){
        this.airportList = apList;
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
