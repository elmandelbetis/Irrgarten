package main;

import UI.TextUI;
import controller.Controller;
import irrgarten.Game;

public class Main {

    public static void main(String[] args) {
        
        Game game = new Game(1);
        TextUI view = new TextUI();
        Controller controlador = new Controller(game, view);
        
        view.showGame(game.getGameState());
        controlador.play();
        
        
        
    }
    
}
