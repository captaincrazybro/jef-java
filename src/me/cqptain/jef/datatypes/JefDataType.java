package me.cqptain.jef.datatypes;

import me.cqptain.jef.Jef;

public interface JefDataType {

    static String name = "";
    Boolean check(String value, Jef jef);
    Object getValue(String value, Jef jef);

}
