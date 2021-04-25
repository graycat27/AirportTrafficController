package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.consts.LcConst;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.utils.LunaChatDummyMember;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.japanize.JapanizeType;

/** LunaChatのチャンネルと連携する情報を保持する */
public class LunaChatChannelFrequency extends AbstractFrequency {

    /* フィールド */


    //LunaChatのチャンネル情報
    private String channelName;
    private transient Channel lcChannel;

    /* コンストラクタ */
    public LunaChatChannelFrequency(int n, int d){
        super(n,d);
        channelName = LcConst.ATC_CHANNEL_NAME_PREFIX + getFreqN() + LcConst.ATC_CHANNEL_FREQ_CHAR + getFreqD();
        createLcChannelIfNonExists();
    }
    public LunaChatChannelFrequency(String freq){
        super(freq);
        channelName = LcConst.ATC_CHANNEL_NAME_PREFIX + getFreqN() + LcConst.ATC_CHANNEL_FREQ_CHAR + getFreqD();
        createLcChannelIfNonExists();
    }


    /* メソッド */
    @Override
    public LunaChatChannelFrequency clone(){
        return new LunaChatChannelFrequency(getFreq());
    }

    /** LunaChatのチャンネルを作成する */
    private void createLcChannelIfNonExists(){

        if(!AirportTrafficController.getLcApi().isExistChannel(channelName)){
            AirportTrafficController.getLcApi().createChannel(channelName);
        }
        lcChannel = AirportTrafficController.getLcApi().getChannel(channelName);
        lcChannel.addMember(new LunaChatDummyMember());
        setLcChannelConfig();
    }

    private void setLcChannelConfig(){
        updateLcChannelFormat();
        lcChannel.setJapanizeType(JapanizeType.NONE);
    }

    public void updateLcChannelFormat(){
        ATCControl atc = FrequencyUtil.getAtcControl(this);
        String FORMAT = "&a[ATC" + getFreq() +"]"
                + ((atc == null) ? "" : "&6[" + atc.getControl() + "]")
                + "&f%msg #2A2A2A%username";
        lcChannel.setFormat(FORMAT);
    }
}
