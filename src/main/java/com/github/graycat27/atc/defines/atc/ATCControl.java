package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.i.IArea;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class ATCControl {

    //周波数と、空域の情報を持たせる
    //参加しているユーザ情報はLCのチャンネルメンバー。
    //管制官役のプレイヤーを将来的には持たせたい

    /** 管制の役割 */
    private Control control;
    public Control getControl(){
        return control;
    }

    private IFrequency frequency;
    public IFrequency getFrequency(){
        return frequency;
    }

    private ATCArea area;
    public IArea getArea(){
        return area;
    }

}
