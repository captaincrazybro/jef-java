package me.cqptain.jef.datatypes;

public class JefString implements JefDataType {

    public static String name = "JefString";

    public Boolean check(String value){
        if((value.startsWith("\"") && value.endsWith("\"")) || ((value.startsWith("'") && value.endsWith("'")))){
            return true;
        } else {
            return false;
        }
    }

    public Object getValue(String value){
        value.replace("\"", "");
        return value;
    }

}
