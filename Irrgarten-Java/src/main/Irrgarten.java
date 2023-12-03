/**
 * @file main/Irrgarten.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package main;

/**
 * Programa main para ejecutar el juego en modo Usuario
 */

import UI.TextUI;
import controller.Controller;
import irrgarten.Game;

public class Irrgarten {
    
    public static void main(String[] args) {
        
        /**
         * @brief Creación del juego, la vista y el controlador
         */
        
        Game game = new Game(0,'G');
        TextUI view = new TextUI();
        Controller controlador = new Controller(game, view);
        
        /**
         * @brief Muestra del juego y el controlador por la terminal
         */
        
        view.showGame(game.getGameState());
        controlador.play();
        
        /**
         * @brief Comprobación de ganador y finalización del juego.
         */
        
        if(game.finished()){
            System.out.println("Juego finalizado.");
        }
        
        
    }
    
}
