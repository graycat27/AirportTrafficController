package com.github.graycat27.atc.components;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.consts.LcConst;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IFrequency;
import org.jetbrains.annotations.NotNull;

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
        String freq = String.format("%d.%d", n, d);
        return isFreqUsed(freq);
    }

    public static boolean isFreqUsed(String freq){
        return (AirportTrafficController.getLcApi().getChannel(getChannelName(freq)) != null);
    }

    public static ATCControl getAtcControl(IFrequency freq){
        //TODO 周波数をもとにATCを取得する
        return null;
    }

    public static String getChannelName(@NotNull String freq){
        String underFreq = freq.replaceAll("\\.", LcConst.ATC_CHANNEL_FREQ_CHAR);
        return LcConst.ATC_CHANNEL_NAME_PREFIX + underFreq;
    }
}
