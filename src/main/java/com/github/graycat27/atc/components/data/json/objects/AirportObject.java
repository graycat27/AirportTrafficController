package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

import java.util.List;

public class AirportObject implements IJsonDataObject {

    private String name;

    private List<ATCControl> areaList;

    public AirportObject(final Airport airport){
        name = airport.getName();
        areaList = airport.getAtcArea();
    }

    @Override
    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
