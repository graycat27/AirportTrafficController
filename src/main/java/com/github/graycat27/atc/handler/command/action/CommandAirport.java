package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.handler.command.AtcCommandHandler;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandAirport{
    public static void add(String name) {
        Airport newOne = new Airport(name);
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
