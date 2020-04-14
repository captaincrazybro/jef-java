package me.cqptain.jef.datatypes;

import me.cqptain.jef.Jef;

public class JefString implements JefDataType {

    public static String name = "JefString";

    public Boolean check(String value, Jef jef){
        if((value.startsWith("\"") && value.endsWith("\"")) || ((value.startsWith("'") && value.endsWith("'")))){
            return true;
        } else {
            return false;
        }
    }

    public Object getValue(String value, Jef jef){
        value = value.replace("\"", "");
        return value;
    }

}
