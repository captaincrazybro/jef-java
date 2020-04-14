package me.cqptain.jef.datatypes;

public interface JefDataType {

    static String name = "";
    Boolean check(String value);
    Object getValue(String value);

}
