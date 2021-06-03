package com.github.graycat27.atc.defines.i;

import java.util.Objects;

/** 通信周波数 */
public abstract class AbstractFrequency implements IFrequency {

    //フィールド
    /* 周波数値 */
    private Integer freq_n;
    private Integer freq_d;
    @Override
    public String getFreq(){
        return freq_n + "." + freq_d;
    }
    public Integer getFreqN(){
        return Integer.valueOf(freq_n);
    }
    public Integer getFreqD(){
        return Integer.valueOf(freq_d);
    }

    //コンストラクタ
    public AbstractFrequency(int n, int d){
        setFreq(n,d);
    }
    public AbstractFrequency(String freq){
        setFreq(freq);
    }

    //メソッド
    /**
     * 周波数を設定する（本メソッドでは変更不可）
     * 整数部と小数部の各値を指定
     * @param n 周波数nnn.d のn値
     * @param d 周波数nnn.d のd値
     */
    private void setFreq(int n, int d){
        //検証
        if(freq_n != null || freq_d != null){
            /* finalに準ずる制限 */
            throw new IllegalStateException("周波数値が設定済みです:"+ getFreq());
        }
        if(n < MIN_FREQ_N || MAX_FREQ_N < n){
            throw new IllegalArgumentException("指定された整数部周波数値が許容範囲外です:" + n);
        }
        if(d < MIN_FREQ_D || MAX_FREQ_D < d){
            throw new IllegalArgumentException("指定された小数部周波数値が許容範囲外です:" + d);
        }

        //セット
        this.freq_n = n;
        this.freq_d = d;
    }

    /**
     * 周波数を設定する（本メソッドでは変更不可）
     * <code>123.4</code>の書式で指定する（小数以下省略可）
     * @param freq 設定する周波数値
     */
    private void setFreq(String freq){
        int n;  //整数部
        int d;  //小数部
        //文字列検証
        try{
            Objects.requireNonNull(freq);
            String[] freqAry = freq.split("\\.");
            if(freqAry.length > 2){
                throw new IllegalArgumentException("ドット.の数が多すぎます:" + freq);
            }
            //小数部
            if(freqAry.length == 1){
                d = 0;
            }else{
                try{
                    d = Integer.parseInt(freqAry[1]);
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("小数部が数値変換できません", e);
                }
            }
            //整数部
            try{
                n = Integer.parseInt(freqAry[0]);
            }catch(NumberFormatException e){
                throw new IllegalArgumentException("整数部が数値変換できません", e);
            }
        }catch(Exception e){
            throw new IllegalArgumentException("指定された周波数文字列が不正です:", e);
        }
        //セット（数値範囲検証は委譲）
        setFreq(n, d);
    }

    @Override
    abstract public AbstractFrequency clone();

    //private ...

}
