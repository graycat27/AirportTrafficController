package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.LunaChatUtil;
import com.github.graycat27.atc.consts.LcConst;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.graycat27.atc.handler.command.AtcCommandHandler.sendMessage;

public class CommandFreq {

    public static void runMonitorCommand(CommandSender sender, String targetParam){
        if(!(sender instanceof Player)){
            sendMessage(sender, "this command is only allowed for in-game player");
            return;
        }

        if(targetParam == null || targetParam.length() == 0){
            sendMessage(sender, "you need to input frequency value to monitor");
            return;
        }

        String channelName = FrequencyUtil.getChannelName(targetParam);
        Channel channel = AirportTrafficController.getLcApi().getChannel(channelName);

        if(channel == null){
            sendMessage(sender, "there are not any ATC frequency matched");
            return;
        }

        String playerName = sender.getName();
        channel.addMember(ChannelMember.getChannelMember(playerName));
        AirportTrafficController.getLcApi().setDefaultChannel(playerName, channelName);

    }

    public static void runCutCommand(CommandSender sender, String targetParam){
        if(!(sender instanceof Player)){
            sendMessage(sender, "this command is only allowed for in-game player");
            return;
        }

        Channel targetChannel = null;
        String playerName = sender.getName();
        if(targetParam == null || targetParam.length() == 0){
            Channel curCh = AirportTrafficController.getLcApi().getDefaultChannel(playerName);
            if(curCh != null) {
                targetChannel = curCh;
            }
        }else{
            String targetFreq = targetParam.replace("\\.", LcConst.ATC_CHANNEL_FREQ_CHAR);
            String nameByParam = FrequencyUtil.getChannelName(targetFreq);
            Channel channel = AirportTrafficController.getLcApi().getChannel(nameByParam);
            if(channel != null){
                targetChannel = channel;
            }
        }
        if(targetChannel == null){
            sendMessage(sender, "that ATC frequency is not found");
            return;
        }
        if(!LunaChatUtil.isAtcChannel(targetChannel)){
            sendMessage(sender, "you can not leave by this command :"+ targetChannel.getName());
            return;
        }

        targetChannel.removeMember(ChannelMember.getChannelMember(sender.getName()));

    }

}
