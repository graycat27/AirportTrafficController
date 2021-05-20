package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.consts.Control;
import com.github.graycat27.atc.defines.airport.Airport;
import com.github.graycat27.atc.defines.i.ConcretePoint;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.sky.ATCArea;

public class CommandArea {

    public static void mod(String[] args) throws IllegalArgumentException {
        // /atc manage mod area <airportName:Sample> <controlType:TWR> <pos:100,64,200> <r:200>
        String airportName;
        Control controlType;
        IPoint centerPos;
        Integer radius;
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
        Integer argIntCenterX, argIntCenterY, argIntCenterZ;
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
    }

    public static void info(String param){
        //TODO make this
    }
}
