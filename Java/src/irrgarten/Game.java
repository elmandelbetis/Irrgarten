
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
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection)
    {
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState()
    {
        GameState gameState = new GameState(labyrinth.toString(),
                players.toString(),monsters.toString(),currentPlayerIndex,
                labyrinth.haveAWinner(),log);  
        
        return gameState;
    
    }
    
    private void configureLabyrinth()
    {
        throw new UnsupportedOperationException(); //TODO completar método
    }
    
    private void nextPlayer()
    {
        if (this.currentPlayerIndex == players.size()){
            currentPlayerIndex -= currentPlayerIndex;   // también vale poner el 0, pero sería nº mágico
        }
        else{
            currentPlayerIndex++;
        }
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
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect){
            this.currentPlayer.resurrect();
            this.logResurrected();
        }
        else{
            this.logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon()
    {
        log+="Jugador "+currentPlayerIndex+", has ganado el combate!!\n"; //también podría ponerse quizás el player.getNumber()?
    }
    
    private void logMonsterWon()
    {
        log+="Jugador "+currentPlayerIndex+", has perdido contra el monstruo!!\n";
    }
    
    private void logResurrected()
    {
        log+="Jugador "+currentPlayerIndex+", has sido resucitado!!\n";
    }
    
    private void logPlayerSkipTurn()
    {
        log+="Jugador "+currentPlayerIndex+", estás muerto. Se pasa el turno al jugador "+(currentPlayerIndex++)+"\n";
    }
    
    private void logPlayerNoOrders()
    {
        log+="No es posible seguir la instrucción\n";
    }
    
    private void logNoMonsters()
    {
        log+="Jugador "+currentPlayerIndex+", acabas de caer en una celda vacía o no es posible moverse.\n"; // no debería hacerse con un if?
    }
    
    private void logRounds(int rounds, int max)
    {
        log+="Ronda actual: "+rounds+"/"+max+"\n";
    }
    
    
    
    
    
}
