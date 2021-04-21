package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;

import java.util.List;

public class AirportObject implements IDataObject {

    private String name;

    private List<ATCControl> areaList;

    public AirportObject(final Airport airport){
        name = airport.getName();
        areaList = airport.getAtcArea();
    }

}
