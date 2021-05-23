package com.github.graycat27.atc.defines.sky;

import com.github.graycat27.atc.defines.i.AbstractArea;
import com.github.graycat27.atc.defines.i.IPoint;

/** 管制空域 */
public class ATCArea extends AbstractArea {

    // フィールド


    // コンストラクタ
    public ATCArea(final IPoint pos1, final IPoint pos2){
        super(pos1, pos2);
    }

    public ATCArea(final IPoint centerPos, final int radius){
        super(centerPos, radius);
    }

    // メソッド
    @Override
    public ATCArea clone(){
        return new ATCArea(getMinPoint(), getMaxPoint());
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
