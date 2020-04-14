package me.cqptain.jef.compilers;

import me.cqptain.jef.Jef;
import me.cqptain.jef.Outcome;
import me.cqptain.jef.OutcomeType;

import java.io.*;
import java.util.HashMap;

public class FunctionCompiler implements Compiler {

    public static String name = "FunctionCompiler";
    private Jef jef;

    public FunctionCompiler(Jef jef){
        System.out.println(name + " compiler has been initialzed");
        this.jef = jef;
    }

    public Boolean check(Integer line, String[] lines){

        if(lines[line].contains("(") && lines[line].contains(")")){
            Character character = '(';
            if(this.jef.functions.functions.containsKey(lines[line].split(character.toString())[0])){
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Outcome run(Integer line, String[] lines) {

        Outcome outcome = new Outcome(OutcomeType.SUCCESS);
        outcome.message = "this is just a test";

        return outcome;

    }

}
