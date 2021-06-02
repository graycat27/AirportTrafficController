package com.github.graycat27.atc.components;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.consts.LcConst;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.IFrequency;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

/** 周波数に関する処理部品 */
public class FrequencyUtil {

    private FrequencyUtil(){ /* インスタンス化防止 */ }

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
        HashMap<String, ATCControl> resultHash = getAtcControlWithAirportName(freq);
        for(String apNm : resultHash.keySet()){
            return resultHash.get(apNm);
        }
        return null;
    }

    public static HashMap<String,ATCControl> getAtcControlWithAirportName(IFrequency freq){
        HashMap<String, ATCControl> result = new HashMap<>();

        String freqVal = freq.getFreq();
        List<String> airportNameList = DataUtil.getAirportNameList();
        for(String apNm : airportNameList){
            Airport ap = DataUtil.getAirportByName(apNm);
            List<ATCControl> controls = ap.getAtcArea();
            for(ATCControl control : controls){
                IFrequency ctrlFreq = control.getFrequency();
                if(ctrlFreq == null){
                    continue;
                }
                if(freqVal.equals(ctrlFreq.getFreq())) {

                    result.put(apNm, control);
                    return result;
                }
            }
        }
        return result;
    }

    public static String getChannelName(@NotNull String freq){
        String underFreq = freq.replaceAll("\\.", LcConst.ATC_CHANNEL_FREQ_CHAR);
        return LcConst.ATC_CHANNEL_NAME_PREFIX + underFreq;
    }
}
