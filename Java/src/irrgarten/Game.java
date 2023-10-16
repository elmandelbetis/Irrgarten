///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Game.java      //
///////////////////////////////////////

package irrgarten;


public class Game {
    
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
        
    public Game(int nplayers) 
    {
        this.currentPlayerIndex = nplayers;
    }
    
    public boolean finished()
    {
        return Labyrinth.haveAWinner;
    }
    
    public boolean nextStep(Directions preferredDirection)
    {
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState()
    {
        throw new UnsupportedOperationException();
    }
    
    public void configureLabyrinth()
    {
        throw new UnsupportedOperationException();
    }
    
    private void actualDirection(Directions preferredDirection)
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
    
    private void Resurrected()
    {
        throw new UnsupportedOperationException();
    }
    
    private void PlayerSkipTurn()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerNoOrders()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logNoMonster()
    {
        throw new UnsupportedOperationException();
    }
    
    private void logRounds()
    {
        throw new UnsupportedOperationException();
    }
    
    
    
    
}
