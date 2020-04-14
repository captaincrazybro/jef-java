package me.cqptain.jef.datatypes;

import java.util.ArrayList;

public class JefDataTypesManager {

    public static ArrayList<JefDataType> dataTypes = new ArrayList<>();

    public void register(JefDataType dataType){
        dataTypes.add(dataType);
    }

    public JefDataType getDataType(String value){

        JefDataType[] returnDataType = {};

        dataTypes.forEach(dataType -> {
            if(dataType.check(value)){
                returnDataType[0] = dataType;
            }
        });

        return returnDataType[0];
    }

}
