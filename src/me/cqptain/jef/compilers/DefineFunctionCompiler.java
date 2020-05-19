//package me.cqptain.jef.compilers;

/*import me.cqptain.jef.InvalidJefFileSyntax;
import me.cqptain.jef.Jef;
import me.cqptain.jef.Outcome;
import me.cqptain.jef.OutcomeType;
import me.cqptain.jef.datatypes.JefDataType;
import me.cqptain.jef.datatypes.JefDataTypesManager;
import me.cqptain.jef.functions.Function;
import me.cqptain.jef.functions.Functions;*/

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
        if(lines[line].contains("(") && lines[line].split("\\(")[1].contains(")") && lines[line].split("\\(")[1].split(" ")[1].equals("->")/* && hasEnding(line, lines)*/){
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

        String name = lines[line].split("\\(")[0].replace("function ", "").replace("func ", "");

        int indent = getIndent(lines[line + 1]);

        String[] params = lines[line].split("\\(")[1].split("\\)")[0].replace(" ", "").split(",");

        if(!this.jef.functions.functions.containsKey(name)){
            int i = line + 1;
            String retrievedCode = "";
            while(!lines[i].startsWith(multiplyString(" ", indent)) && i < lines.length){
              retrievedCode += lines[i].replace(multiplyString(" ", indent), "") + "\n";
              i++;
            }
            String[] code = {retrievedCode};
            Integer[] i2 = {0};
            Function function = new Function(name, ps -> {
              while(i2[0] < ps.length){
                if(ps[i2[0]] instanceof String){
                  ps[i2[0]] = "\"" + ps[i2[0]] + "\"";
                }
                code[i2[0]] = params[i2[0]] + " = " + ps[i2[0]] + "\n" + code[i2[0]];
                Jef functionJef = new Jef(code[i2[0]]);
                try {
                  functionJef.compile();
                } catch (InvalidJefFileSyntax e){
                  e.printStackTrace();
                }
                i2[0]++;
              }
            }, params.length);
            this.jef.functions.registerFunction(name, function);
            Outcome outcome = new Outcome(OutcomeType.RETURN);
            outcome.returns = i;
            return outcome;
        } else {
            Outcome outcome = new Outcome(OutcomeType.ERROR);
            outcome.exception = new InvalidJefFileSyntax("This function name is already taken - Line: " + line);
            return outcome;
        }

    }

    /*private static Boolean hasEnding(Integer startingLine, String[] lines){
      int i = startingLine;
      while(i < lines.length){
        System.out.println(lines[i]);
        if(lines[i].contains("(") && lines[i].split("\\(")[1].contains(")")){
          return false;
        } else if(lines[i].contains("|")){
          return true;
        }
        i++;
      }
      return false;
    }*/

    private static Integer getIndention(String string){
      int i = 0;
      while(string.charAt(i) == " "){
        i++;
      }
      return i;
    }

    private static String multiplyString(String string, int amount){
        int i = 0;
        String outcome = "";
        while(i < amount){
          outcome += string;
        }
        return outcome;
    }

}