package com.github.graycat27.atc.defines.atc;

/** 通信周波数。LunaChatのチャンネルと紐づく */
public class Frequency {

    /* 周波数値 000.0～999.9の範囲を許容する */
    int freq_n;
    int freq_d;

    public String getFreq(){
        return freq_n + "." + freq_d;
    }


    //LunaChatのチャンネル情報の値
    //private ...

}
