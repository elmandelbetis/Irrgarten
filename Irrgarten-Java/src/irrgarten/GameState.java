/**
 * @file irrgarten/GameState.java
 * @author Álvaro Maldonado Medina, grupo 2D-D3
 */

package irrgarten;

/**
 * Clase encargada de mostrar, en forma de Strings, el estado actual de todo 
 * el juego: laberinto, jugadores, monstruos, el jugador actual en el turno,
 * si hay un ganador, el log de mensajes generados por el propio juego.
 */

public class GameState {
    
    //*****************************************************************
    // Atributos 
    //*****************************************************************

    
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    
    //*****************************************************************
    // Constructor 
    //*****************************************************************
   
    /**
     * Constructor de la clase, genera el estado del juego
     * @param labyrinth Estado del laberinto
     * @param players Jugadores
     * @param monsters Monstruos
     * @param currentPlayer Jugador actual seleccionado para el turno
     * @param winner Si hay un ganador o no
     * @param log Mensajes del juego
     */
    
    public GameState(String labyrinth, String players, String monsters, 
                     int currentPlayer, boolean winner, String log){
        
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    //*****************************************************************
    // Getters & Setters
    //*****************************************************************
    
    public String getLabyrinth(){
        return labyrinth;
    }
    
    public String getPlayers(){
        return players;
    }
    
    public String getMonsters(){
        return monsters;
    }
    
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
}
