/**
 * @file main/Main.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package main;

import UI.TextUI;
import UI.UI;
import controller.Controller;
import irrgarten.Game;

/**
 * Programa main para ejecutar el juego en modo Debug
 */

public class Main {

    public static void main(String[] args) {
        
        /**
         * @brief Creación del juego, la vista y el controlador
         */
        
        Game game = new Game(0,'D');    // Creación del juego
        UI view = new TextUI(); // Creación de la vista textual
        Controller controlador = new Controller(game, view);    // Creación del controlador
        
        /**
         * @brief Muestra del juego y el controlador por la terminal
         */
        
        view.showGame(game.getGameState()); // Mostrar juego
        controlador.play(); 
        
        /**
         * @brief Comprobación de ganador y finalización del juego.
         */
        
        if(game.finished()){
            System.out.println("Juego finalizado.");
        }
        
        
    }
    
}
