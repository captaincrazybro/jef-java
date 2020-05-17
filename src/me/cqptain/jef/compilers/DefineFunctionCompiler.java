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

public class DefineFunctionCompiler implements Compiler {

    public static String name = "CreateFunctionCompiler";
    private Jef jef;

    public DefineFunctionCompiler(Jef jef){
        System.out.println(name + " compiler has been initialzed");
        this.jef = jef;
    }

    public Boolean check(Integer line, String[] lines){

        if(lines[line].contains("\\(") && lines[line].contains("\\)") && hasEnding(line, lines)){
            //if(!this.jef.functions.functions.containsKey(lines[line].split("\\(")[0])){
                return true;
            //} else {
            //    return false;
            //}
        } else {
            return false;
        }

    }

    public Outcome run(Integer line, String[] lines) {

        String name = lines[line].split("\\(")[0].replace("function ", "");

        String[] params = lines[line].split("\\(")[1].split("\\)")[0].replace(" ", "").split(",");

        if(!this.jef.functions.functions.containsKey(name)){
            int i = line + 1;
            String retrievedCode = "";
            while(!lines[i].contains("|") && i < params.length){
              retrievedCode += lines[i] + "\n";
              i++;
            }
            String[] code = {retrievedCode};
            Function function = new Function(name, ps -> {
              int i2 = 0;
              while(i2 < ps.length){
                if(ps[i2] instanceof String){
                  ps[i2] = "\"" + ps[i2] + "\"";
                }
                code[i2] = params[i2] + " = " + ps[i2] + "\n" + code[i2];
                Jef functionJef = new Jef(code[i2]);
                try {
                  functionJef.compile();
                } catch (InvalidJefFileSyntax e){
                  e.printStackTrace();
                }
                i2++;
              }
            }, params.length);
            this.jef.functions.registerFunction(name, function);
            Outcome outcome = new Outcome(OutcomeType.RETURN);
            outcome.returns = line++;
            return outcome;
        } else {
            Outcome outcome = new Outcome(OutcomeType.ERROR);
            outcome.exception = new InvalidJefFileSyntax("This function name is already taken");
            return outcome;
        }

    }

    private static Boolean hasEnding(Integer staringLine, String[] lines){
      int i = staringLine;
      while(i < lines.length){
        if(lines[i].contains("(") && lines[i].split(")")[1].contains(")")){
          return false;
        } else if(lines[i].contains("|")){
          return true;
        }
        i++;
      }
      return false;
    }

}