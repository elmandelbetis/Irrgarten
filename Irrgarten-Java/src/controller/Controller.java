/**
 * @file controller/Controller.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package controller;

import UI.GUI;
import irrgarten.Directions;
import irrgarten.Game;
import UI.UI;

/**
 * El controlador maneja la lógica del juego, coordinando las interacciones y
 * la interfaz de usuario de texto (TextUI)
 */

public class Controller {
    
    //*****************************************************************
    // Atributos 
    //*****************************************************************
    
    private Game game;  // Instancia del juego
    private GUI view;    // Instancia de TextUI
    
    
    /**
     * Constructor de controller que recibe una instancia de Game y la TextUI
     * @param game  Instancia del juego
     * @param view  Interfaz de usuario de texto
     */
    
    public Controller(Game game, GUI view) {
        this.game = game;
        this.view = view;
    }
    
    /**
     * Inicia el juego y gestiona el bucle principal del juego.
     * Muestra el estado del juego en la interfaz de usuario de texto,
     * solicita movimientos al usuario y actualiza el estado del juego
     * hasta que se alcanza el final del juego.
     */
    
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            Directions direction = view.nextMove(); 
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
}