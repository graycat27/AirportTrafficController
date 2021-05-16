package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.atc.ATCControl;

public class AtcControlObject implements IJsonDataObject {

    // フィールド
    private final Control control;
    private final FrequencyObject frequency;
    private final AtcAreaObject atcArea;

    // コンストラクタ
    public AtcControlObject(final ATCControl original){
        control = original.getControl();
        frequency = new FrequencyObject(original.getFrequency());
        atcArea = new AtcAreaObject(original.getArea());
    }

    public AtcControlObject(){
        control = null;
        frequency = null;
        atcArea = null;
    }

    // メソッド
    @Override
    public String toString(){
        return toJsonString();
    }

    @Override
    public ATCControl getOriginal() {
        return new ATCControl(control, frequency.getOriginal(), atcArea.getOriginal());
    }
}
