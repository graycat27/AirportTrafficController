package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.defines.i.Frequency;

/** LunaChatのチャンネルと連携する情報を保持する */
public class LunaChatChannelFrequency extends Frequency {

    /* フィールド */


    //LunaChatのチャンネル情報


    /* コンストラクタ */
    LunaChatChannelFrequency(int n, int d){
        super(n,d);
    }
    LunaChatChannelFrequency(String freq){
        super(freq);
    }



    /* メソッド */
    @Override
    public LunaChatChannelFrequency clone(){
        return new LunaChatChannelFrequency(getFreq());
    }

}
