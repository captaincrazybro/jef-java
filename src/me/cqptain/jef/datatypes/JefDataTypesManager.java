package me.cqptain.jef.datatypes;

import me.cqptain.jef.Jef;

import java.util.ArrayList;

public class JefDataTypesManager {

    public static ArrayList<JefDataType> dataTypes = new ArrayList<>();

    public void register(JefDataType dataType){
        dataTypes.add(dataType);
    }

    public JefDataType getDataType(String value, Jef jef){

        JefDataType[] returnDataType = {};

        dataTypes.forEach(dataType -> {
            if(dataType.check(value, jef)){
                returnDataType[0] = dataType;
            }
        });

        return returnDataType[0];
    }

}
