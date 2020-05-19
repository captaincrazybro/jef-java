//package me.cqptain.jef;

public class Outcome {

    public InvalidJefFileSyntax exception;
    public Boolean success;
    public String message;
    public Object returns;
    private OutcomeType outcomeType;

    public Outcome(OutcomeType outcomeType){
        this.outcomeType = outcomeType;
    }

    public OutcomeType getOutcomeType(){ return this.outcomeType; }

}
