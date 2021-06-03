package com.github.graycat27.atc.components;

import com.github.graycat27.atc.consts.LcConst;
import com.github.ucchyocean.lc3.channel.Channel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LunaChatUtil {

    /** チャンネル名をもとに、ATCのチャンネルであるか判定します
     * @return ATCのチャンネルの場合<code>true</code> */
    public static boolean isAtcChannel(Channel ch){
        String chName = ch.getName();

        if(chName.startsWith(LcConst.ATC_CHANNEL_NAME_PREFIX)){
            // prefixが一致した場合のみ正規表現でチェック(処理速度考慮)
            Pattern pt = Pattern.compile(LcConst.ATC_CHANNEL_NAME_PREFIX + "[0-9]{1,3}_[0-9]");
            Matcher mt = pt.matcher(chName);
            return mt.matches();
        }
        return false;
    }
}
