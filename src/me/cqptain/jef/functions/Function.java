package me.cqptain.jef.functions;

import me.cqptain.jef.InvalidJefFileSyntax;

import java.util.function.Consumer;

public class Function {

    public String name = "";
    public Consumer<Object[]> action;
    public Integer params = 0;

    public Function(String name, Consumer<Object[]> action){
        this.name = name;
        this.action = action;
    }

    public Function(String name, Consumer<Object[]> action, Integer params){
        this.name = name;
        this.action = action;
        this.params = params;
    }

    public void run(Object[] params) throws InvalidJefFileSyntax {
        if(params.length != this.params){
            throw new InvalidJefFileSyntax("Invalid number of parameters given for function " + this.name);
        }
        this.action.accept(params);
    }

}
