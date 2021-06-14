package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.FrequencyUtil;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.atc.ATCControl;
import com.github.graycat27.atc.defines.i.*;
import com.github.graycat27.atc.defines.sky.ATCArea;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.github.graycat27.atc.handler.command.AtcCommandHandler.sendMessage;

public class CommandArea {

    public static void mod(String[] args) throws IllegalArgumentException {
        // /atc manage mod area <airportName:Sample> <controlType:TWR> <pos:100,64,200> <r:200>
        String airportName;
        Control controlType;
        IPoint centerPos;
        int radius;
        ATCArea area;

        String argApName = (args.length < 4) ? null : args[3];
        if(argApName == null || argApName.length() == 0){
            throw new IllegalArgumentException("airport name is required");
        }
        Airport ap = DataUtil.getAirportByName(argApName);
        airportName = ap.getName();

        String argControl = (args.length < 5) ? null : args[4];
        if(argControl == null || argControl.length() == 0){
            throw new IllegalArgumentException("control type is required");
        }
        controlType = Control.valueOf(argControl);

        String argCenter = (args.length < 6) ? null : args[5];
        if(argCenter == null || argCenter.length() == 0){
            throw new IllegalArgumentException("center location is required");
        }
        String argCenterX = null, argCenterY = null, argCenterZ = null;
        String[] argCenterPos = argCenter.split(",");
        if(argCenterPos.length < 2 || 3 < argCenterPos.length){
            throw new IllegalArgumentException("center location format is wrong");
        }
        if(argCenterPos.length == 2){
            argCenterX = argCenterPos[0];
            argCenterY = "64";
            argCenterZ = argCenterPos[1];
        }
        if(argCenterPos.length == 3){
            argCenterX = argCenterPos[0];
            argCenterY = argCenterPos[1];
            argCenterZ = argCenterPos[2];
        }
        int argIntCenterX, argIntCenterY, argIntCenterZ;
        try{
            argIntCenterX = Integer.parseInt(argCenterX);
            argIntCenterY = Integer.parseInt(argCenterY);
            argIntCenterZ = Integer.parseInt(argCenterZ);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("center location format is wrong");
        }
        centerPos = new ConcretePoint(argIntCenterX, argIntCenterY, argIntCenterZ);
        String argRadius = (args.length < 7) ? null : args[6];
        try {
            radius = Integer.parseInt(argRadius);
        }catch(NumberFormatException | NullPointerException e){
            throw new IllegalArgumentException("radius value is wrong");
        }
        area = new ATCArea(centerPos, radius);

        DataUtil.setAtcAreaToAirport(airportName, controlType, area);
        AirportTrafficController.getDataManager().save();
    }

    /**
     * areaの情報を表示します<br>
     * <p>引数が無い場合、コマンド実行場所（in-game player）または全て（console）の管制情報を表示します</p>
     * <p>引数が指定されている場合、指定の周波数値の管制空港情報を表示します</p>
     */
    public static void info(CommandSender sender, String param){
        // /atc manage info area xxx
        if(param == null || param.length() == 0){
            if(sender instanceof Player){
                showAreasInfoByLocation((Player) sender);
            }else{
                showAllAreasInfo((ConsoleCommandSender) sender);
            }
        }else{
            showAreaInfoByFreq(sender, param);
        }
    }

    /* private */

    private static void showAreasInfoByLocation(Player sender){
        IPoint position = IPoint.getByLocation(sender.getLocation());
        List<String> airportNameList = DataUtil.getAirportNameList();
        sendMessage(sender, "===== Control area where you are in =====");
        for(String apNm : airportNameList){
            Airport ap = DataUtil.getAirportByName(apNm);
            List<ATCControl> controls = ap.getAtcArea();
            for(ATCControl control : controls){
                ATCArea area = control.getArea();
                if(area == null){
                    continue;
                }
                if(area.isIn(position)){
                    String freq = (control.getFrequency() == null) ? "" : "{"+ control.getFrequency().getFreq() +"}";
                    String ctrl = (control.getControl() == null) ? "" : "{"+ control.getControl().toString() +"}";
                    sendMessage(sender, apNm + ctrl + freq);
                    sendMessage(sender, "   "+ getSimpleAreaString(control.getArea()));
                }
            }
        }
        sendMessage(sender, "===== ============================= =====");
    }

    private static void showAllAreasInfo(ConsoleCommandSender sender){
        List<String> airportNameList = DataUtil.getAirportNameList();
        sendMessage(sender, "===== Airport and their control area =====");
        for(String apNm : airportNameList){
            Airport ap = DataUtil.getAirportByName(apNm);
            List<ATCControl> controls = ap.getAtcArea();
            ATCControl twr = null, ctl = null;
            for(ATCControl control : controls){
                if(control.getControl().equals(Control.TWR)){
                    twr = control;
                }
                if(control.getControl().equals(Control.CTL)){
                    ctl = control;
                }
            }
            sendMessage(sender, "Airport: " + apNm);
            if(twr != null) {
                StringBuilder twrResult = new StringBuilder("  ").append(Control.TWR);
                if(twr.getFrequency() != null){
                    twrResult.append("(").append(twr.getFrequency().getFreq()).append(")");
                }
                twrResult.append(":").append(getSimpleAreaString(twr.getArea()));
                sendMessage(sender, twrResult.toString());
            }
            if(ctl != null) {
                StringBuilder ctlResult = new StringBuilder("  ").append(Control.CTL);
                if(ctl.getFrequency() != null){
                    ctlResult.append("(").append(ctl.getFrequency().getFreq()).append(")");
                }
                ctlResult.append(":").append(getSimpleAreaString(ctl.getArea()));
                sendMessage(sender, ctlResult.toString());
            }
        }
        sendMessage(sender, "===== ============================== =====");
    }

    private static String getSimpleAreaString(IArea area){
        if(area == null){
            return "unset";
        }
        StringBuilder sb = new StringBuilder("[{");
        IPoint minP = area.getMinPoint();
        sb.append(minP.getX()).append(",").append(minP.getY()).append(",").append(minP.getZ()).append("},{");
        IPoint maxP = area.getMaxPoint();
        sb.append(maxP.getX()).append(",").append(maxP.getY()).append(",").append(maxP.getZ()).append("}]");
        return sb.toString();
    }

    private static void showAreaInfoByFreq(CommandSender sender, String inputFreq){
        IFrequency freqObj;
        try {
            freqObj = new ConcreteFrequency(inputFreq);
        }catch(IllegalArgumentException e){
            sendMessage(sender, "wrong frequency value format");
            return;
        }
        HashMap<String, ATCControl> controls = FrequencyUtil.getAtcControlWithAirportName(freqObj);
        Set<String> apNameSet = controls.keySet();
        String apNm = apNameSet.iterator().next();
        ATCControl control = controls.get(apNm);

        sendMessage(sender, "===== Airport and its control area =====");
        sendMessage(sender, "frequency "+ freqObj.getFreq());
        sendMessage(sender, "  airport name: "+ apNm);
        sendMessage(sender, "  control type: "+ control.getControl());
        sendMessage(sender, "  manage area: "+ getSimpleAreaString(control.getArea()));
        sendMessage(sender, "===== ============================ =====");
    }

}
