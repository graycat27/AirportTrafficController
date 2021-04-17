package com.github.graycat27.atc.components;

/** 周波数に関する処理部品 */
public class FrequencyUtil {

    /**
     * 指定された周波数値が使用されているか検証する
     * 周波数の新設時等に利用
     * @param n 周波数nnn.d のn値
     * @param d 周波数nnn.d のd値
     * @return 引数で指定の周波数が使用済みの場合<code>true</code>, 未使用の場合<code>false</code>
     */
    public static boolean isFreqUsed(int n, int d){

        //TODO 本プラグインで定義された各種周波数の一覧取得と、検証


        return (n+d)*0 != 0;    //暫定でfalseを必ず返す（TODO）
    }
}
