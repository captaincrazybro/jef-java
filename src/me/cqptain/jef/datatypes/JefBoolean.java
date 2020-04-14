package me.cqptain.jef.datatypes;

public class JefBoolean implements JefDataType {

    public static String name = "JefBoolean";

    public String value;

    public Boolean check(String value){
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no");
    }

    public Object getValue(String value){
        return "test";
    }

}