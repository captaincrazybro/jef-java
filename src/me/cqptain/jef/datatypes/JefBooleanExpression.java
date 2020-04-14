package me.cqptain.jef.datatypes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JefBooleanExpression implements JefDataType {
	static String name = "JefBooleanConverter";
	public Boolean check(String value){
		if (value == null) {
            return false;
        }
		
		value = value.replaceAll("equals", "==");
		value = value.replaceAll("isNot", "!=");
		value = value.replaceAll("isLessThan", "<");
		value = value.replaceAll("isMoreThan", ">");
		value = value.replaceAll("isLessOrEqualTo", "<=");
		value = value.replaceAll("isMoreOrEqualTo", ">=");
		value = value.replaceAll("and", "&&");
		value = value.replaceAll("or", "||");
		
		try {
			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript"); 
		    String expr = value.toLowerCase();
		    boolean result = (Boolean)(engine.eval(expr));
		    return true;
        } catch (ScriptException e) {
            return false;
        }
    }
	
    public Object getValue(String value) {
    	if (value == null) {
            return false;
        }
    	
    	value = value.replaceAll("equals", "==");
		value = value.replaceAll("isNot", "!=");
		value = value.replaceAll("isLessThan", "<");
		value = value.replaceAll("isMoreThan", ">");
		value = value.replaceAll("isLessOrEqualTo", "<=");
		value = value.replaceAll("isMoreOrEqualTo", ">=");
		value = value.replaceAll("and", "&&");
		value = value.replaceAll("or", "||");
    	
    	try {
			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript"); 
		    String expr = value.toLowerCase();
		    boolean result = (Boolean)(engine.eval(expr));
		    return result;
        } catch (ScriptException e) {
            System.out.println("Invalid Expression");
            e.printStackTrace();
            return false;
        }
    }
}
