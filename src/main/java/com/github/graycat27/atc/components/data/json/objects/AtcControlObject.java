package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.sky.ATCArea;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

public class AtcControlObject implements IJsonDataObject {

    private final Control control;

    private final FrequencyObject frequency;

    private final AtcAreaObject atcArea;

    public AtcControlObject(ATCControl original){
        control = original.getControl();
        frequency = new FrequencyObject(original.getFrequency());
        atcArea = new AtcAreaObject(original.getArea());
    }

    public AtcControlObject(){
        control = null;
        frequency = null;
        atcArea = null;
    }

    @Override
    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
