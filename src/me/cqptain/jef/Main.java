//package me.cqptain.jef;

class Main {
    public static void main(String[] args) {
        try{
            Jef jef = new Jef("log\\(\"hi\"\\)");
            jef.compile();
        } catch (InvalidJefFileSyntax e){
            e.printStackTrace();
        }
        try {
          Jef jef = Jef.getJefFromFileName("Main.jef");
          jef.compile();
        } catch (InvalidJefFileSyntax e){
          e.printStackTrace();
        }
    }
}