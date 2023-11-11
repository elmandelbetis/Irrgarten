
package irrgarten;
import java.util.ArrayList;

public class Game {
    
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
   
    public Player currentPlayer;
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
        this.labyrinth = new Labyrinth(10, 10, 2, 0); // sample dado por el profesor
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.log = "";
        
        for (int i = 0; i <= nplayers; i++){
            players.add(new Player((char)(i+48),Dice.randomIntelligence(),Dice.randomStrength()));
        }
        
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.currentPlayer = players.get(currentPlayerIndex);
        this.configureLabyrinth();
        
    }
    
    public boolean finished()
    {
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection)
    {
        this.log = "";
        boolean dead = currentPlayer.dead();
        
        if (!dead)
        {
            Directions direction = actualDirection(preferredDirection);
            
            if (direction != preferredDirection)
            {
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if (monster == null)
            {
                logNoMonster();
            } else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
            
        } else {
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if (!endGame){
            nextPlayer();
        }
        
        return endGame;
    
    }
    
    public GameState getGameState()
    {
        GameState gameState = new GameState(labyrinth.toString(),
                players.toString(),monsters.toString(),currentPlayerIndex,
                finished(),log);  
        
        return gameState;
    
    }
    
    private void configureLabyrinth()
    {
        int tamTotal = labyrinth.getRows() * labyrinth.getCols();
        
        // TODO Añadir bloques
        
        int nMonstruos = tamTotal / 5;
        
        labyrinth.addBlock(Orientation.HORIZONTAL, 1, 0, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 1, 2);
        
        
        for (int i = 0; i <= nMonstruos; i++){
            Monster monstruo = new Monster("#"+(i),Dice.randomIntelligence(),Dice.randomStrength());
            monsters.add(monstruo);
            labyrinth.addMonster(Dice.randomPos(labyrinth.getRows()), 
                              Dice.randomPos(labyrinth.getCols()), monstruo);
        }
        
        labyrinth.spreadPlayers(players);
    
    }
    
    private void nextPlayer()
    {
        if (this.currentPlayerIndex == players.size()){
            currentPlayerIndex -= currentPlayerIndex;
        }
        else{
            currentPlayerIndex++;
        }
    }
    
    private Directions actualDirection(Directions preferredDirection)
    {
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster)
    {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while ((!lose) && (rounds < MAX_ROUNDS))
        {
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose) 
            {
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
            
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;
        
    }
    
    private void manageReward(GameCharacter winner)
    {
        if (winner == GameCharacter.PLAYER)
        {
            currentPlayer.receiveReward();
            logPlayerWon();
        } else {
            logMonsterWon();
        }
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
        log+="Jugador #"+currentPlayerIndex+", has ganado el combate!!\n"; //también podría ponerse quizás el player.getNumber()?
    }
    
    private void logMonsterWon()
    {
        log+="Jugador #"+currentPlayerIndex+", has perdido contra el monstruo!!\n";
    }
    
    private void logResurrected()
    {
        log+="Jugador #"+currentPlayerIndex+", has sido resucitado!!\n";
    }
    
    private void logPlayerSkipTurn()
    {
        log+="Jugador #"+currentPlayerIndex+", estás muerto. Se pasa tu turno\n";
    }
    
    private void logPlayerNoOrders()
    {
        log+="No es posible seguir la instrucción\n";
    }
    
    private void logNoMonster()
    {
        log+="Jugador "+currentPlayerIndex+", acabas de caer en una celda vacía o no es posible moverse.\n"; // no debería hacerse con un if?
    }
    
    private void logRounds(int rounds, int max)
    {
        log+="Ronda actual: "+rounds+"/"+max+"\n";
    }
    
    
    
    
    
}
