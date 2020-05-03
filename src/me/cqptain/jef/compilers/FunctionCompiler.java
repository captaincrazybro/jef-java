package me.cqptain.jef.compilers;

import me.cqptain.jef.InvalidJefFileSyntax;
import me.cqptain.jef.Jef;
import me.cqptain.jef.Outcome;
import me.cqptain.jef.OutcomeType;
import me.cqptain.jef.datatypes.JefDataType;
import me.cqptain.jef.datatypes.JefDataTypesManager;
import me.cqptain.jef.functions.Function;
import me.cqptain.jef.functions.Functions;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FunctionCompiler implements Compiler {

    public static String name = "FunctionCompiler";
    private Jef jef;

    public FunctionCompiler(Jef jef){
        System.out.println(name + " compiler has been initialzed");
        this.jef = jef;
    }

    public Boolean check(Integer line, String[] lines){

        if(lines[line].contains("\\(") && lines[line].contains("\\)")){
            if(this.jef.functions.functions.containsKey(lines[line].split("\\(")[0])){
                System.out.println("hi");
                return true;
            } else {
                System.out.println("hi");
                return false;
            }
        } else {
            System.out.println("no");
            return false;
        }

    }

    public Outcome run(Integer line, String[] lines) {

        String name = lines[line].split("\\(")[0];

        String[] params = lines[line].split("\\(")[1].split("\\)")[0].replace(" ", "").split(",");

        if(this.jef.functions.functions.containsKey(name)){
            Function function = this.jef.functions.functions.get(name);
            if(function.params == params.length){
                JefDataType[] paramDatas = {};
                int i = 1;
                while(i <= params.length){
                    paramDatas[i] = JefDataTypesManager.getDataType(params[i], this.jef);
                    i++;
                }
                try {
                    function.run(paramDatas);
                } catch(InvalidJefFileSyntax e){
                    Outcome outcome = new Outcome(OutcomeType.ERROR);
                    outcome.exception = e;
                    return outcome;
                }
            } else {
                Outcome outcome = new Outcome(OutcomeType.ERROR);
                outcome.exception = new InvalidJefFileSyntax("Invalid number of parameters");
                return outcome;
            }
        } else {
            Outcome outcome = new Outcome(OutcomeType.ERROR);
            outcome.exception = new InvalidJefFileSyntax("This function does not exist");
            return outcome;
        }

        return new Outcome(OutcomeType.SUCCESS);

    }

}
