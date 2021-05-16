package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.json.IJsonDataObject;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IPoint;

public class PointObject implements IJsonDataObject {

    // フィールド
    private final Integer posX;
    private final Integer posY;
    private final Integer posZ;

    // コンストラクタ
    public PointObject(final IPoint original){
        this.posX = (original == null) ? null : original.getX();
        this.posY = (original == null) ? null : original.getY();
        this.posZ = (original == null) ? null : original.getZ();
    }

    // メソッド
    @Override
    public String toString(){
        return toJsonString();
    }

    @Override
    public IPoint getOriginal() {
        return new ConcretePoint(posX, posY, posZ);
    }
}
