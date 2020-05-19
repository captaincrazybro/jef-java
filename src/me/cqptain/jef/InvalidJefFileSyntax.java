//package me.cqptain.jef;

public class InvalidJefFileSyntax extends Exception {

    public String message = "";

    public InvalidJefFileSyntax(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
