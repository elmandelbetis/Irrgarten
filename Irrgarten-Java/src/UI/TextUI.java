/**
 * @file UI/TextUI.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;

/**
 * Interfaz de usuario de texto para el juego 
 */

public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    /**
     * Lee un carácter desde la entrada estándar
     * @return El carácter que ingresa el usuario por teclado
     */
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    
    /**
     * Solicita al usuario el próximo movimiento y devuelve la dirección seleccionada
     * @return La dirección seleccionada por el usuario
     */
    
    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }   
        return direction;
    }
    
    /**
     * Muestra el estado actual del juego en la terminal
     * @param gameState instancia que llama y muestra el GameState en la terminal
     */
    
    public void showGame(GameState gameState) {  
        
        System.out.println(gameState.getLabyrinth());
        System.out.println("Lista de jugadores: "+gameState.getPlayers());
        System.out.println("Lista de monstruos: "+gameState.getMonsters());
        System.out.println("Jugador actual: "+gameState.getCurrentPlayer());
        System.out.println("Ganador?: "+gameState.getWinner());
        System.out.println(gameState.getLog());
  
    }
    
}