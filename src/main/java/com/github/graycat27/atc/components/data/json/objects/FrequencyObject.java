package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;

public class FrequencyObject implements IJsonDataObject {

    private final Integer freq_n;
    private final Integer freq_d;

    public FrequencyObject(IFrequency original){
        AbstractFrequency concreteFreq = (AbstractFrequency) original;
        freq_n = (concreteFreq == null) ? null : concreteFreq.getFreqN();
        freq_d = (concreteFreq == null) ? null :concreteFreq.getFreqD();
    }

    @Override
    public String toString(){
        return toJsonString();
    }
}