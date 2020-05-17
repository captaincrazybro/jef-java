package me.cqptain.jef;

import me.cqptain.jef.Jef;

class Main {
    public static void main(String[] args) {
      System.out.print("hi");
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