//package me.cqptain.jef.functions;

//import me.cqptain.jef.Jef;

import java.util.function.Consumer;
import java.util.HashMap;

public class Functions {

    public HashMap<String, Function> functions = new HashMap<>();
    public Jef jef;

    public Functions(Jef jef){
        this.jef = jef;
    }

    public void registerFunctions(){

        Function logFunction = new Function("log", params -> {
            System.out.println(params[0]);
        }, 1);

        this.functions.put(logFunction.name, logFunction);

    }

    public void registerFunction(String functionName, Function function){
      this.functions.put(functionName, function);
    }

}
