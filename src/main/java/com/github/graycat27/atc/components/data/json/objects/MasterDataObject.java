package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.MasterData;
import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.i.IMaster;

import java.util.Hashtable;

public class MasterDataObject implements IJsonDataObject {

    //フィールド
    private Hashtable<String, Airport> airportList;

    //コンストラクタ
    public MasterDataObject(){
        airportList = new Hashtable<>();
    }

    public MasterDataObject(final MasterData original){
        airportList = original.getAirportList();
    }

    //メソッド
    @Override
    public MasterData getOriginal() {
        return new MasterData();
    }

    @Override
    public MasterDataObject convertFromOriginal(IMaster original) {
        if(!(original instanceof MasterData)){
            throw new IllegalArgumentException();
        }
        return new MasterDataObject((MasterData) original);
    }
}