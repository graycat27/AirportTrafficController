package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class AtcControlObject implements IJsonDataObject {

    private Control control;

    private IFrequency frequency;

    private ATCArea atcArea;

    public AtcControlObject(ATCControl original){
        control = original.getControl();
        frequency = original.getFrequency();
        atcArea = original.getArea();
    }
}
