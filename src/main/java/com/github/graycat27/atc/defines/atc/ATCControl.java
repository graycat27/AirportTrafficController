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

    private IFrequency frequency;
    public IFrequency getFrequency(){
        return frequency.clone();
    }
    public void setFrequency(IFrequency frequency){
        if(this.frequency != null){
            throw new IllegalStateException("frequency is already set");
        }
        this.frequency = frequency;
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("AtcControl: {");
        sb.append("Control: ").append(control.toString()).append(", ");
        sb.append("Frequency: ").append(frequency == null ? "unset" : frequency.getFreq()).append(", ");
        sb.append("Area: ").append(area == null ? "unset" : area.toString());
        sb.append("}");
        return sb.toString();
    }
}
