///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/GameState.java //
///////////////////////////////////////

package irrgarten;


public class GameState {
    
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    
    // Constructor
    
    public GameState()
    {
        this("","","",0,false,"");
    }
    
    public GameState(String labyrinthv, String players, String monsters, 
                     int currentPlayer, boolean winner, String log){
        
        this.labyrinthv = labyrinthv;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    // Métodos get y set de la clase GameState
    
    public String getLabyrinthv(){
        return labyrinthv;
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
