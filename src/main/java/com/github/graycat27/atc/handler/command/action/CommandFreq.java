package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.ucchyocean.lc.channel.ChannelPlayer;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.graycat27.atc.handler.command.AtcCommandHandler.sendMessage;

public class CommandFreq {

    public static void runTuningCommand(CommandSender sender, String targetParam){
        if(!(sender instanceof Player)){
            sendMessage(sender, "this command is only allowed for in-game player");
            return;
        }

        if(targetParam == null || targetParam.length() == 0){
            sendMessage(sender, "you need to input frequency value to tuning in");
            return;
        }

        String channelName = FrequencyUtil.getChannelName(targetParam);
        sendMessage(sender,"[debug]channelName= "+ channelName);
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
        //TODO make this
        sendMessage(sender, "cut command is unavailable");
    }

}
