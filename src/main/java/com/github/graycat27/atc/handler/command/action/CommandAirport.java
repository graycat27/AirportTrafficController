package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.atc.LunaChatChannelFrequency;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandAirport{
    public static void add(String name) throws IllegalArgumentException {
        if(DataUtil.hasSameNameAirport(name)){
            throw new IllegalArgumentException("there are same name airport already");
        }
        Airport newOne = new Airport(name);
        newOne.addControl(new ATCControl(Control.TWR, null, null));
        newOne.addControl(new ATCControl(Control.CTL, null, null));
        DataUtil.addAirport(newOne);
        AirportTrafficController.getDataManager().save();
    }

    public static void mod(String[] args) {
        //TODO make this
    }

    public static void info(CommandSender sender, String param) {
        if(param == null) {
            showAllAirportsList(sender);
        }else{
            showUniqueAirportInto(sender, param);
        }
    }

    private static void showUniqueAirportInto(CommandSender sender, String name){
        Airport ap = DataUtil.getAirportByName(name);
        AtcCommandHandler.sendMessage(sender, "===== Airport Information =====");
        AtcCommandHandler.sendMessage(sender, ap.toString());
        AtcCommandHandler.sendMessage(sender, "===== =================== =====");
    }

    private static void showAllAirportsList(CommandSender sender){
        List<String> nameList = DataUtil.getAirportNameList();
        AtcCommandHandler.sendMessage(sender, "===== Airport List =====");
        for(String name : nameList){
            Airport ap = DataUtil.getAirportByName(name);
            String loc = (ap.getTower() == null) ? "" : " at " + ap.getTower().getLocation();
            AtcCommandHandler.sendMessage(sender, name + loc);
        }
        AtcCommandHandler.sendMessage(sender, "===== ============ =====");
    }
}
