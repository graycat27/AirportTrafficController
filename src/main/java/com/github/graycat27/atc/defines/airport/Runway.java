package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.Path;

public class Runway extends Path {

    /* フィールド */
    /** 滑走路番号
     * <code>18-36</code>, <code>09R-27L</code> など */
    private final String rwyNumber;


    /* コンストラクタ */
    Runway(){
        super(null);
        //cause IllegalArgumentException
        //FIXME
        rwyNumber = "18-36";
    }


    /* メソッド */



}
