package me.cqptain.jef.datatypes;

import me.cqptain.jef.Jef;

public class JefNumber implements JefDataType {

    public static String name = "JefNumber";

    public String value;

    public Boolean check(String value, Jef jef){
        return isNumeric(value);
    }

    public Object getValue(String value, Jef jef){
        return Double.parseDouble(value);
    }

    private Boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
