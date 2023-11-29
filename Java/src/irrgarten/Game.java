
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
    
    //  Constructor
    public Game(int nplayers, char mode)    //parámetro extra alternar entre Debug y modo Usuario
    {
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.log = "";
        
        for (int i = 0; i <= nplayers; i++){
            players.add(new Player((char)(i+48),Dice.randomIntelligence(),Dice.randomStrength()));
            /**
             * El número mágico 48 se añade dado que al castear i a char, su valor se vuelve el 0,1,2,3...
             * en formato ASCII, lo que añade caracteres o nulos o headers. Para añadir caracteres
             * numéricos, se realiza esta conversión.
             */
        }
        
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.currentPlayer = players.get(currentPlayerIndex);
        
        if (mode == 'D'){
            this.labyrinth = new Labyrinth(5,5,2,0);        
            configureLabyrinthDebug();
            labyrinth.spreadPlayers(players);            
        } else {
            this.labyrinth = new Labyrinth(10,10,10,0);        
            configureLabyrinth();
            labyrinth.spreadPlayers(players);
        }
        
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
        int nMonstruos = tamTotal / 5;
        
        // TODO Añadir Bloques
        
        //Inicializa y añade los monstruos
        for (int i = 0; i < nMonstruos; i++){
            Monster monstruo = new Monster("#"+(i),Dice.randomIntelligence(),Dice.randomStrength());       
            monsters.add(monstruo);
            labyrinth.addMonster(Dice.randomPos(labyrinth.getRows()), 
                              Dice.randomPos(labyrinth.getCols()), monstruo);
        }
            
    }
    
    private void configureLabyrinthDebug()
    {
        int tamTotal = labyrinth.getRows() * labyrinth.getCols();
        int diagonal = 10;
        int fin_muro2 = 11, col_ini = 1, fil_ini = 8;        
        int nMonstruos = tamTotal / 5;
        
        labyrinth.addBlock(Orientation.HORIZONTAL, 1, 0, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 1, 2);
        for (int i = diagonal; i > 0; i-=2){
            labyrinth.addBlock(Orientation.HORIZONTAL, i, i + 1, 1);
        }
        
        for (int i = 0; i < fin_muro2; i++){
            labyrinth.addBlock(Orientation.HORIZONTAL, fil_ini, col_ini, 1);
            col_ini++;
            fil_ini--;
        }
        
        //Inicializa y añade los monstruos
        for (int i = 0; i < nMonstruos; i++){
            //Monster monstruo = new Monster("#"+(i),Dice.randomIntelligence(),Dice.randomStrength());       
            Monster monstruo = new Monster("#"+(i),30,30);            
            monsters.add(monstruo);
            labyrinth.addMonster(Dice.randomPos(labyrinth.getRows()), 
                              Dice.randomPos(labyrinth.getCols()), monstruo);
        }
            
    }
    
    private void nextPlayer()
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
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
            this.logPlayerWon();
        } 
        else
            this.logMonsterWon();
    }
    
    private void manageResurrection()
    {
        boolean resurrect = Dice.resurrectPlayer();
        
        if (resurrect){
            int row = currentPlayer.getRow();
            int col = currentPlayer.getCol();
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(currentPlayer);
            this.currentPlayer = fuzzyPlayer;
            players.set(this.currentPlayerIndex, currentPlayer);
            
            labyrinth.placeFuzzyPlayer(fuzzyPlayer, row, col);
            fuzzyPlayer.resurrect();
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
        log+="Jugador "+currentPlayerIndex+", acabas de caer en una celda vacía"
                + ", o en la casilla de salida\n";
    }
    
    private void logRounds(int rounds, int max)
    {
        log+="Ronda actual: "+rounds+"/"+max+"\n";
    }
    
    
    
    
    
}
