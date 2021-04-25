package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

public class PointObject implements IJsonDataObject {

    private final Integer posX;
    private final Integer posY;
    private final Integer posZ;

    public PointObject(final IPoint original){
        this.posX = (original == null) ? null : original.getX();
        this.posY = (original == null) ? null : original.getY();
        this.posZ = (original == null) ? null : original.getZ();
    }

    @Override
    public String toString(){
        return toJsonString();
    }
}
