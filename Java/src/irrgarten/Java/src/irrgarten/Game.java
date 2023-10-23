
package irrgarten;
import java.util.ArrayList;

public class Game {
    
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
   
    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    

    //  Constructor vacío
    public Game()
    {
        this(0);
    }
    
    //  Constructor
    public Game(int nplayers)
    {
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.labyrinth = new Labyrinth(5, 7, 2, 0); // sample dado por el profesor
        this.players = null;
        this.monsters = null;
        this.log = null;
        
        for (int i = 0; i <= nplayers; i++){
            players.add(new Player((char) i,Dice.randomIntelligence(),Dice.randomStrength()));
        }
        
        this.currentPlayer = players.get(currentPlayerIndex);
        
        
    }
    
    public boolean finished()
    {
        throw new UnsupportedOperationException();
    }
    
    public boolean nextStep(Directions preferredDirection)
    {
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState()
    {
        throw new UnsupportedOperationException();
    }
    
    private void configureLabyrinth()
    {
        throw new UnsupportedOperationException();
    }
    
    private void nextPlayer()
    {
        throw new UnsupportedOperationException();
    }
    
    private Directions actuaDirection(Directions preferredDirection)
    {
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster)
    {
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner)
    {
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logMonsterWon()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logResurrected()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerSkipTurn()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerNoOrders()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logNoMonsters()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logRounds(int rounds, int max)
    {
        throw new UnsupportedOperationException();
    }
    
    
    
    
    
    
    
    
    
    
}
