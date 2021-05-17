package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.IMaster;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class AtcAreaObject implements IJsonDataObject {

    // フィールド
    private final PointObject minPoint;
    private final PointObject maxPoint;

    // コンストラクタ
    public AtcAreaObject(final ATCArea original){
        minPoint = (original == null) ? null : new PointObject(original.getMinPoint());
        maxPoint = (original == null) ? null : new PointObject(original.getMaxPoint());
    }

    // メソッド
    @Override
    public String toString(){
        return toJsonString();
    }

    @Override
    public ATCArea getOriginal() {
        return new ATCArea(minPoint.getOriginal(), maxPoint.getOriginal());
    }

    @Override
    public AtcAreaObject convertFromOriginal(IMaster original) {
        if(!(original instanceof ATCArea)){
            throw new IllegalArgumentException();
        }
        return AtcAreaObject.convertFromOriginal((ATCArea) original);
    }

    public static AtcAreaObject convertFromOriginal(ATCArea original){
        return new AtcAreaObject(original);
    }
}
