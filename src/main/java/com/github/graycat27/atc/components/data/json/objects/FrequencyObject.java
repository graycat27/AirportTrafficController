package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.defines.i.ConcreteFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IMaster;

public class FrequencyObject implements IJsonDataObject {

    // フィールド
    private final Integer freq_n;
    private final Integer freq_d;

    // コンストラクタ
    public FrequencyObject(final IFrequency original){
        AbstractFrequency concreteFreq = (AbstractFrequency) original;
        freq_n = (concreteFreq == null) ? null : concreteFreq.getFreqN();
        freq_d = (concreteFreq == null) ? null :concreteFreq.getFreqD();
    }

    // メソッド
    @Override
    public String toString(){
        return toJsonString();
    }

    @Override
    public IFrequency getOriginal() {
        return new ConcreteFrequency(freq_n, freq_d);
    }

    @Override
    public FrequencyObject convertFromOriginal(IMaster original) {
        if(!(original instanceof IFrequency)){
            throw new IllegalArgumentException();
        }
        return new FrequencyObject((IFrequency) original);
    }
}
