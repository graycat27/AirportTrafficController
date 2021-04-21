package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class AtcAreaObject implements IJsonDataObject {

    private IPoint minPoint;

    private IPoint maxPoint;

    public AtcAreaObject(ATCArea original){
        minPoint = original.getMinPoint();
        maxPoint = original.getMaxPoint();
    }

}
