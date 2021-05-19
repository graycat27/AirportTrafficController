package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.consts.CommandWord;
import com.github.graycat27.atc.consts.Control;
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

    public static void mod(String[] args) throws IllegalArgumentException {
        // /atc manage mod airport xxx yyy zzz
        if(args.length < 4){
            throw new IllegalArgumentException("input airport name after your command");
        }
        String airportName = args[3];   //xxx
        if(!DataUtil.hasSameNameAirport(airportName)){
            throw new IllegalArgumentException("there are no such airport");
        }

        if(args.length < 5){
            throw new IllegalArgumentException("input subcommand after your command");
        }
        String subCommand = args[4];    //yyy
        if(args.length < 6){
            throw new IllegalArgumentException("input param after your command");
        }
        String commandParam = args[5];  //zzz

        if(CommandWord.AirportMeta.ATC_Name.equalsIgnoreCase(subCommand)){
            setAtcNameForAirport(airportName, commandParam);
            return;
        }
        if(CommandWord.AirportMeta.TWR_FREQ.equalsIgnoreCase(subCommand)){
            setTowerFreqForAirport(airportName, commandParam);
            return;
        }
        if(CommandWord.AirportMeta.CTL_FREQ.equalsIgnoreCase(subCommand)){
            setControlFreqForAirport(airportName, commandParam);
            return;
        }
        throw new IllegalArgumentException("unknown param for command [/atc manage mod airport]");

    }

    public static void info(CommandSender sender, String param) {
        if(param == null) {
            showAllAirportsList(sender);
        }else{
            showUniqueAirportInto(sender, param);
        }
    }


    //private method
    /* mod */
    private static void setAtcNameForAirport(String airportName, String atcName){
        //TODO make this
    }

    private static void setTowerFreqForAirport(String airportName, String freq){
        IFrequency freqCh = setupNewFrequency(freq);
        DataUtil.setAtcFreqToAirport(airportName, Control.TWR, freqCh);
    }

    private static void setControlFreqForAirport(String airportName, String freq){
        IFrequency freqCh = setupNewFrequency(freq);
        DataUtil.setAtcFreqToAirport(airportName, Control.CTL, freqCh);
    }

    private static IFrequency setupNewFrequency(String freq){
        if(FrequencyUtil.isFreqUsed(freq)){
            throw new IllegalArgumentException("that frequency is already used");
        }
        return new LunaChatChannelFrequency(freq);
    }

    /* info */
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
