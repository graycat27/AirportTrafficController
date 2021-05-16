package com.github.graycat27.atc.handler.command.action;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.DataUtil;
import com.github.graycat27.atc.defines.airport.Airport;

public class CommandAirport{
    public static void add(String name) {
        Airport newOne = new Airport(name);
        DataUtil.addAirport(newOne);
        AirportTrafficController.getDataManager().save();
    }

    public static void mod(String[] args) {
        //TODO make this
    }

    public static void info(String param) {
        //TODO make this
    }
}
