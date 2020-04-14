package me.cqptain.jef.compilers;

import me.cqptain.jef.Outcome;

public interface Compiler {

    public static String name = "";
    Boolean check(Integer line, String[] lines);
    Outcome run(Integer line, String[] lines);

}
