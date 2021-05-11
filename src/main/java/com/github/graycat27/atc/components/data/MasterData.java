package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.i.IMaster;

import java.util.ArrayList;
import java.util.List;

public class MasterData implements IMaster {

    //フィールド
    List<Airport> airportList;

    //コンストラクタ
    private MasterData(List<Airport> apList){
        this.airportList = apList;
    }

    public MasterData(){
        airportList = new ArrayList<>();
    }

    //メソッド

    @Override
    public MasterData clone() {
        return new MasterData(new ArrayList<>(airportList));
    }
}
