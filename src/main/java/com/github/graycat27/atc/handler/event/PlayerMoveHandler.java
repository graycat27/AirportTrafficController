package com.github.graycat27.atc.handler.event;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.events.AtcDataUpdateEvent;
import com.github.graycat27.atc.defines.i.IArea;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.ucchyocean.lc3.channel.Channel;
import com.github.ucchyocean.lc3.member.ChannelMember;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class PlayerMoveHandler extends TimerTask implements Listener {

    /* key is "freq" like "222.2"  */
    HashMap<String, IArea> areaMap = new HashMap<>();
    String[] areaKeys = new String[0];

    @EventHandler
    public void onDataUpdated(AtcDataUpdateEvent event){
        List<String> names = DataUtil.getAirportNameList();
        for(int idx = 0; idx < names.size(); idx++){
            Airport ap = DataUtil.getAirportByName(names.get(idx));
            for(ATCControl area : ap.getAtcArea()){
                IFrequency freq = area.getFrequency();
                if(freq != null) {
                    String key = freq.getFreq();
                    areaMap.put(key, area.getArea());
                }
            }
        }
        areaKeys = areaMap.keySet().toArray(new String[]{});
    }

    public void run(){
        for(Player player : Bukkit.getOnlinePlayers()) {
            IPoint playerLocation = IPoint.getByLocation(player.getLocation());
            for(int idx=0; idx < areaKeys.length; idx++){
                IArea area = areaMap.get(areaKeys[idx]);
                if (area != null && area.isIn(playerLocation)) {
                    forceJoinChannel(areaKeys[idx], player);
                }
            }
        }
    }

    private void forceJoinChannel(String freq, Player player){
        Channel ch = AirportTrafficController.getLcApi().getChannel(FrequencyUtil.getChannelName(freq));
        ch.addMember(ChannelMember.getChannelMember("$"+player.getUniqueId()));
        //NOTE: 既にメンバーの場合、addMember内でスルーされるため、こちらでチェックはしない
    }
}
