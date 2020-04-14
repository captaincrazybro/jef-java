package me.cqptain.jef.compilers;

import me.cqptain.jef.Outcome;
import me.cqptain.jef.OutcomeType;

import java.io.*;
import java.util.HashMap;

public class Variable implements Compiler {

    public HashMap<String, Object> variables;

    public static String name = "Variable";

    public Variable(){
        System.out.println(name + " compiler has been initialzed");
    }

    public Boolean check(Integer line, String[] lines){
        String lineValue = lines[line];
        String[] lineRows = lineValue.split(" ");

        if(lineRows.length >= 3){
            Boolean isFirstCheck = (lineRows[0] == "set" && lineRows[2] == "to");
            Boolean isSecondCheck = (lineRows[0] == "set" && lineRows[2] == "=");
            Boolean isThirdCheck = (lineRows[1] == "=");
            if(isFirstCheck || isSecondCheck || isThirdCheck){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public Outcome run(Integer line, String[] lines) {

        String lineValue = lines[line];
        String[] lineRows = lineValue.split(" ");

        Boolean isFirstCheck = (lineRows[0] == "set" && lineRows[2] == "to");
        Boolean isSecondCheck = (lineRows[0] == "set" && lineRows[2] == "=");
        Boolean isThirdCheck = (lineRows[1] == "=");

        String variableName;
        Object value;

        if(isFirstCheck || isSecondCheck) {
            variableName = lineRows[1];
            value = lineRows[3];
        } else if(isThirdCheck) {
            variableName = lineRows[0];
            value = lineRows[2];
        } else {
            variableName = null;
            value = null;
        }

        variables.put(variableName, value);

        Outcome outcome = new Outcome(OutcomeType.RETURN);
        outcome.returns = line++;

        return outcome;
    }

}
