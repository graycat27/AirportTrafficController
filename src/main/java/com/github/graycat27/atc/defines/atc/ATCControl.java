package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IArea;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IPoint;
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
    public ATCArea getArea(){
        return area;
    }

    // コンストラクタ
    @Deprecated
    public ATCControl(){
        control = Control.TWR;
        frequency = new LunaChatChannelFrequency(222,2);
        IPoint pos1 = new ConcretePoint(1,2,3);
        IPoint pos2 = new ConcretePoint(111,222,333);
        area = new ATCArea(pos1, pos2);
    }

}
