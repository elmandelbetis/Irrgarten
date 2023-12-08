/**
 * @file main/MainGUI.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 * @brief Juego principal incluyendo una interfaz gráfica, cursores y controlador
 */

package main;

import UI.GraphicalUI;
import controller.Controller;
import irrgarten.Game;


public class MainGUI {

    public static void main(String[] args) {
        
        /**
         * @brief Creación del juego, la vista y el controlador
         */
        
        Game game = new Game(0,'U');    // modo User, juego normal
        GraphicalUI vista = new GraphicalUI(); 
        Controller controller = new Controller(game, vista);   
        
        controller.play();
       
        
        
    }
    
}