package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.utils.ICloneable;

/** 周波数インターフェース */
public interface IFrequency extends IMaster {

    //定数値
    /* 周波数値 100.0～999.9の範囲を許容する */
    int MIN_FREQ_N = 100;
    int MAX_FREQ_N = 999;
    int MIN_FREQ_D = 0;
    int MAX_FREQ_D = 9;


    //メソッド定義
    String getFreq();

    @Override
    IFrequency clone();

}
