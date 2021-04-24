package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.AbstractArea;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IPoint;

public class FrequencyObject implements IJsonDataObject {

    private Integer freq_n;
    private Integer freq_d;

    private class ConcreteFreq extends AbstractArea{
        public ConcreteFreq() {
            super(new ConcretePoint(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE),
                    new ConcretePoint(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        public Integer getFreqN(){
            return freq_n;
        }
        public Integer getFreqD(){
            return freq_d;
        }

        @Override
        public AbstractArea clone() {
            return null;
        }
    }
    public FrequencyObject(IFrequency original){
        ConcreteFreq concreteFreq = (ConcreteFreq) original;
        freq_n = concreteFreq.getFreqN();
        freq_d = concreteFreq.getFreqD();
    }


}
