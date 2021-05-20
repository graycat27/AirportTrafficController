package com.github.graycat27.atc.defines.sky;

import com.github.graycat27.atc.defines.i.AbstractArea;
import com.github.graycat27.atc.defines.i.IPoint;

/** 管制空域 */
public class ATCArea extends AbstractArea {

    // フィールド


    // コンストラクタ
    public ATCArea(IPoint pos1, IPoint pos2){
        super(pos1, pos2);
        //FIXME
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
