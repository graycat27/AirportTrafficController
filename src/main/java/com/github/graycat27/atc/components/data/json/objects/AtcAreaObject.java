package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.AbstractPoint;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.sky.ATCArea;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

public class AtcAreaObject implements IJsonDataObject {

    private final PointObject minPoint;

    private final PointObject maxPoint;

    public AtcAreaObject(ATCArea original){
        minPoint = new PointObject(original.getMinPoint());
        maxPoint = new PointObject(original.getMaxPoint());
    }

    @Override
    public String toString(){
        return toJsonString();
    }

}
