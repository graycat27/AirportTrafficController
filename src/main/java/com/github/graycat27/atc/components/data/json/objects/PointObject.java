package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.IPoint;

public class PointObject implements IJsonDataObject {

    private final Integer posX;
    private final Integer posY;
    private final Integer posZ;

    public PointObject(IPoint original){
        this.posX = original.getX();
        this.posY = original.getY();
        this.posZ = original.getZ();
    }

    private PointObject(){
        posX = null;
        posY = null;
        posZ = null;
    }
}
