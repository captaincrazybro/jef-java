package me.cqptain.jef.datatypes;

import me.cqptain.jef.Jef;

public class JefBoolean implements JefDataType {

    public static String name = "JefBoolean";

    public String value;

    public Boolean check(String value, Jef jef){
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no");
    }

    public Object getValue(String value, Jef jef){
        return "test";
    }

}