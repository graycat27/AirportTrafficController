package com.github.graycat27.atc.defines.atc;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.LunaChatUtil;
import com.github.graycat27.atc.components.bot.AtcBot;
import com.github.graycat27.atc.consts.LcConst;
import com.github.graycat27.atc.defines.i.AbstractFrequency;
import com.github.graycat27.atc.utils.LunaChatDummyMember;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.japanize.JapanizeType;
import com.github.ucchyocean.lc3.member.ChannelMember;

import java.lang.reflect.Field;
import java.util.List;

/** LunaChatのチャンネルと連携する情報を保持する */
public class LunaChatChannelFrequency extends AbstractFrequency {

    /* フィールド */


    //LunaChatのチャンネル情報
    private final String channelName;
    private transient Channel lcChannel;

    /* コンストラクタ */
    public LunaChatChannelFrequency(int n, int d){
        super(n,d);
        channelName = LcConst.ATC_CHANNEL_NAME_PREFIX + getFreqN() + LcConst.ATC_CHANNEL_FREQ_CHAR + getFreqD();
        lcChannel = AirportTrafficController.getLcApi().getChannel(channelName);
    }
    public LunaChatChannelFrequency(String freq){
        super(freq);
        channelName = LcConst.ATC_CHANNEL_NAME_PREFIX + getFreqN() + LcConst.ATC_CHANNEL_FREQ_CHAR + getFreqD();
        lcChannel = AirportTrafficController.getLcApi().getChannel(channelName);
    }
    private LunaChatChannelFrequency(LunaChatChannelFrequency original){
        super(original.getFreq());
        channelName = LcConst.ATC_CHANNEL_NAME_PREFIX + getFreqN() + LcConst.ATC_CHANNEL_FREQ_CHAR + getFreqD();
        lcChannel = AirportTrafficController.getLcApi().getChannel(channelName);
        //createChannelしない
    }


    /* メソッド */
    @Override
    public LunaChatChannelFrequency clone(){
        return new LunaChatChannelFrequency(this);
    }

    /** LunaChatのチャンネルを作成する */
    public void createLcChannelIfNonExists(){
        if(!AirportTrafficController.getLcApi().isExistChannel(channelName)){
            AirportTrafficController.getLcApi().createChannel(channelName);
        }
        lcChannel = AirportTrafficController.getLcApi().getChannel(channelName);
        setLcChannelConfig();
        lcChannel.save();
    }

    public void setLcChannelConfig(){
        updateLcChannelFormat();
        lcChannel.setJapanizeType(JapanizeType.NONE);
        setChannelMember();
    }

    private void updateLcChannelFormat(){
        //TODO airportNameもformatに入れる
        ATCControl atc = FrequencyUtil.getAtcControl(this);
        String FORMAT = "&a[ATC" + getFreq() +"]"
                + ((atc == null) ? "" : "&6[" + atc.getControl() + "]")
                + "&f%msg #2A2A2A%username";
        lcChannel.setFormat(FORMAT);
    }

    private void setChannelMember(){
        List<ChannelMember> moderatorList = lcChannel.getModerator();
        try {
            String nameStr = lcChannel.getName();
            Field name = Channel.class.getDeclaredField("name");
            name.setAccessible(true);
            name.set(lcChannel, nameStr + ">");

            for(ChannelMember cm : moderatorList){
                lcChannel.removeMember(cm);
            }
            LunaChatDummyMember dummyAdmin = new LunaChatDummyMember();
            lcChannel.addMember(dummyAdmin);
            lcChannel.addModerator(dummyAdmin);

            ATCControl control = FrequencyUtil.getAtcControl(this);
            AtcBot bot = LunaChatUtil.getChannelMember(control);
            lcChannel.addMember(bot);
            lcChannel.addModerator(bot);

            name.set(lcChannel, nameStr);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(getClass().getName()).append(": {");
        sb.append("freq: ").append(getFreq()).append(", ");
        sb.append("channelName: ").append(channelName);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object another){
        if(!(another instanceof final LunaChatChannelFrequency anotherLcFreq)){
            return false;
        }
        return super.equals(anotherLcFreq); //privateの比較をしない
    }
}
