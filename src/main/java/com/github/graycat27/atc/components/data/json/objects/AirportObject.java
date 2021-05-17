package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IMaster;

import java.util.List;

public class AirportObject implements IJsonDataObject {

    // フィールド
    private String name;

    private List<ATCControl> areaList;

    // コンストラクタ
    public AirportObject(final Airport airport){
        name = airport.getName();
        areaList = airport.getAtcArea();
    }

    // メソッド
    @Override
    public String toString(){
        return toJsonString();
    }

    @Override
    public Airport getOriginal(){
        //FIXME
        return new Airport(name);
    }

    @Override
    public AirportObject convertFromOriginal(IMaster original) {
        if(!(original instanceof Airport)){
            throw new IllegalArgumentException();
        }
        return AirportObject.convertFromOriginal((Airport) original);
    }

    public static AirportObject convertFromOriginal(Airport original){
        return new AirportObject(original);
    }
}
