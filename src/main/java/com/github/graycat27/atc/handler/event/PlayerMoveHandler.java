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
    }

    public void run(){
        System.out.println("task is running!");
        for(Player player : Bukkit.getOnlinePlayers()) {
            IPoint playerLocation = IPoint.getByLocation(player.getLocation());

            for (String areaKey : areaMap.keySet()) {
                IArea area = areaMap.get(areaKey);
                if (area != null && area.isIn(playerLocation)) {
                    forceJoinChannel(areaKey, player);
                }
            }
        }
    }

    private void forceJoinChannel(String freq, Player player){

        System.out.println("forced>> "+ freq);

        Channel ch = AirportTrafficController.getLcApi().getChannel(FrequencyUtil.getChannelName(freq));
        ch.addMember(ChannelMember.getChannelMember(player.getUniqueId().toString()));
        //NOTE: 既にメンバーの場合、addMember内でスルーされるため、こちらでチェックはしない
    }
}
