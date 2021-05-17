package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IMaster;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class ATCControl implements IMaster {

    //周波数と、空域の情報を持たせる
    //参加しているユーザ情報はLCのチャンネルメンバー。
    //管制官役のプレイヤーを将来的には持たせたい

    /** 管制の役割 */
    private final Control control;
    public Control getControl(){
        return control;
    }

    private final IFrequency frequency;
    public IFrequency getFrequency(){
        return frequency.clone();
    }

    private final ATCArea area;
    public ATCArea getArea(){
        return area.clone();
    }

    //コンストラクタ
    public ATCControl(Control c, IFrequency f, ATCArea a){
        this.control = c;
        this.frequency = f;
        this.area = a;
    }

    //メソッド
    @Override
    public ATCControl clone(){
        return new ATCControl(this.getControl(), this.getFrequency(), this.getArea());
    }
}
