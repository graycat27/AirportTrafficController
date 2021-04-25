package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class AtcAreaObject implements IJsonDataObject {

    private final PointObject minPoint;

    private final PointObject maxPoint;

    public AtcAreaObject(final ATCArea original){
        minPoint = (original == null) ? null : new PointObject(original.getMinPoint());
        maxPoint = (original == null) ? null : new PointObject(original.getMaxPoint());
    }

    @Override
    public String toString(){
        return toJsonString();
    }

}
