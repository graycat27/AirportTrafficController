package com.github.graycat27.atc.defines.i;

/** 周波数インターフェース */
public interface IFrequency {

    //定数値
    /* 周波数値 100.0～999.9の範囲を許容する */
    int MIN_FREQ_N = 100;
    int MAX_FREQ_N = 999;
    int MIN_FREQ_D = 0;
    int MAX_FREQ_D = 9;


    //メソッド定義
    String getFreq();

}
