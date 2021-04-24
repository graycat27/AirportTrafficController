package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

public class FrequencyObject implements IJsonDataObject {

    private Integer freq_n;
    private Integer freq_d;

    public FrequencyObject(IFrequency original){
        AbstractFrequency concreteFreq = (AbstractFrequency) original;
        freq_n = concreteFreq.getFreqN();
        freq_d = concreteFreq.getFreqD();
    }

    @Override
    public String toString(){
        return toJsonString();
    }
}
