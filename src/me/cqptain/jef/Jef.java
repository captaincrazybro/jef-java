//package me.cqptain.jef;

/*import me.cqptain.jef.compilers.Compiler;
import me.cqptain.jef.compilers.FunctionCompiler;
import me.cqptain.jef.compilers.Variable;
import me.cqptain.jef.datatypes.JefDataTypesManager;
import me.cqptain.jef.functions.Functions;
import me.cqptain.jef.compilers.DefineFunctionCompiler;*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class Jef {

    ArrayList<Compiler> compilers;
    String[] lines = {""};
    public Functions functions;
    public JefDataTypesManager dataTypeManager;

    public Jef(File file){
        JefDataTypesManager.enable();
        this.lines = this.readFile(file).toArray(this.lines);
        this.compilers = this.getCompilers();
        this.functions = new Functions(this);
        functions.registerFunctions();
    }

    public Jef(String code){
        JefDataTypesManager.enable();
        ArrayList<String> lines = new ArrayList<String>();
        Collections.addAll(lines, code.split("\n"));
        lines.toArray(this.lines);
        this.compilers = this.getCompilers();
        this.functions = new Functions(this);
        functions.registerFunctions();
    }

    public void compile() throws InvalidJefFileSyntax {

        final String[] linesBrac = this.lines;

        Integer[] line = {0};

        while(line[0] != this.lines.length){
            int i = 0;
            Integer[] newLine = {0};
            while(i < compilers.size()){
                Compiler compiler = compilers.get(i);
                if(compiler.check(line[0], linesBrac)){
                    Outcome outcome = compiler.run(line[0], linesBrac);
                    if(outcome.getOutcomeType() == OutcomeType.ERROR){
                        throw outcome.exception;
                    }
                    newLine[0] = (Integer) outcome.returns;
                }
                i++;
            }
            if(newLine[0] == 0){
              line[0]++;
            } else {
              line[0] = newLine[0];
            }
        }

    }

    public ArrayList<Compiler> getCompilers(){
        ArrayList<Compiler> compilers = new ArrayList<>();

        compilers.add(new Variable());
        compilers.add(new DefineFunctionCompiler(this));
        compilers.add(new FunctionCompiler(this));

        return compilers;
    }

    public ArrayList<String> readFile(File file){
        if(!file.exists()) return null;
        try {
            Scanner fileScanner = new Scanner(file);
            ArrayList<String> linesList = new ArrayList<String>();
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                linesList.add(nextLine);
            }
            return linesList;
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Jef getJefFromFileName(String fileName){
        File file = new File(Jef.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/jef/src/me/cqptain/jef/" + fileName);

        if(!file.exists()) return null;

        return new Jef(file);

    }

    public Compiler getCompiler(String name){

        Compiler[] compiler = {};

        this.compilers.forEach(c -> {
            if(c.name.equals(name)){
                compiler[0] = c;
            }
        });

        if(compiler.length == 0) return null;

        return compiler[0];

    }

}
