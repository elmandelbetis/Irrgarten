///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Labyrinth.java //
///////////////////////////////////////

package irrgarten;

import java.util.ArrayList;

public class Labyrinth {
    
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static int ROW = 0; // para uso en P3
    private static int COL = 1; // para uso en P3
    
    private final int nRows;
    private final int nCols;
    private final int exitRow;
    private final int exitCol;
    
    public ArrayList<ArrayList<Monster>> monsters; 
    public ArrayList<ArrayList<Player>> players;  
    public ArrayList<ArrayList<Character>> labyrinth;
    
    
    
    // Constructores de la clase
    
    public Labyrinth()
    {
        this(0,0,0,0);
    }
       
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
     
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new ArrayList<ArrayList<Monster>>();
        this.players = new ArrayList<ArrayList<Player>>();
        this.labyrinth = new ArrayList<ArrayList<Character>>();
        
    }

    
    
    // Método haveAWinner
    // Booleano que devuelve "true" en caso de que un jugador, habiendo
    // realizado las respectivas comprobaciones, haya llegado a la casilla 
    // de salida
    
    public boolean haveAWinner()
    {
        boolean haveAWinner = false;
        
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                if (labyrinth.get(i).get(j) == EXIT_CHAR)
                {
                    if(players.get(i).get(j) != null)
                    {
                        haveAWinner = true;
                    }
                }
            }
        }
        
        return haveAWinner;
    }
    
    // Método toString()
    // Devuelve una cadena de caracteres con el estado actual del laberinto
    
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException();
    }
    
    // Método posOK()
    // Devuelve "true" si la posición (row, col) pasada como parámetro
    // es real y se encuentra en el tablero
    
    private boolean posOK(int row, int col){
        
        boolean posOK = false;
        
        if (row >= 0 && row <= nRows && col >= 0 && col <= nCols)
        {
            posOK = true;
        }
        
        return posOK;
    }
    
    // Método addMonster()
    // Si cierta casilla está vacía y se encuentra en el tablero,
    // Anotamos la existencia de un monstruo dentro de esta, la almacenamos
    // en el contenedor de monstruos e indicamos al monstruo dónde se encuentra
    
    public void addMonster(int row, int col, Monster monster)
    {   
        boolean posOK = posOK(row, col);
        
        if (labyrinth.get(row).get(col) == EMPTY_CHAR && posOK)
        {   
            labyrinth.get(row).set(col,MONSTER_CHAR);
            monsters.get(row).set(col,monster);
            monster.setPos(row, col);
            
        }
    }
    
    private void spreadPlayers(ArrayList<Player> players)
    {
        for (int i = 0; i < players.size(); i++)
        {
            Player p = players.get(i);
            int[] pos = randomEmptyPos();            
            putPlayer2D(-1,-1, pos[ROW], pos[COL], p); 
            
        }
    }
    
    // Método emptyPos()
    // Si la casilla pasada como parámetro está vacía, devuelve "true"
    
    private boolean emptyPos(int row, int col)
    {   
        boolean isEmpty = false;
        
        if (labyrinth.get(row).get(col) == EMPTY_CHAR)
        {
            isEmpty = true;
        }
        
        return isEmpty;
    }
    
    // Método monsterPos()
    // Pasamos una casilla como parámetro, y si un monstruo se encuentra
    // en ella, devolvemos "true". "False" en caso contrario
    
    private boolean monsterPos(int row, int col)
    {
        boolean monsterThere = false;
        
        if (labyrinth.get(row).get(col) == MONSTER_CHAR)
        {
            monsterThere = true;
        }
        
        return monsterThere;
    }
    
    // Método exitPos()
    // Devuelve "true" o "false" en función de si la casilla pasada como
    // argumento es o no la de salida
    
    private boolean exitPos(int row, int col)
    {
        boolean exitPos = false;
        
        if (labyrinth.get(row).get(col) == EXIT_CHAR){
            exitPos = true;
        }
        
        return exitPos;
    }
    
    // Método exitPos()
    // Devuelve "true" o "false" en función de si la casilla pasada como
    // argumento es o no una de combate (que se encuentren en ella tanto un
    // jugador como un monstruo)
    
    private boolean combatPos(int row, int col)
    {
        boolean combatPos = false;
        
        if (labyrinth.get(row).get(col) == COMBAT_CHAR){
            combatPos = true;
        }
        
        return combatPos;
    }
    
    // Método canStepOn()
    // Indica si la posición se encuentra dentro del tablero y se corresponde
    // o con una casilla vacía, monstruo o es la casilla de salida
    
    private boolean canStepOn(int row, int col)
    {   
        boolean canStepOn = false;
        
        if (posOK(row,col))
        {
            if (emptyPos(row, col) || monsterPos(row, col) 
                || exitPos(row, col))
            {
                canStepOn = true;
            }
        
        }
        
        return canStepOn;
    }
    
    // Método updateOldPos()
    // Comprobamos si la casilla está dentro del laberinto. Una vez realizada
    // la comprobación, si la casilla estaba marcada como una de combate,
    // entonces pasamos a marcarla como una casilla de monstruo. En otro caso,
    // la marcamos simplemente como una casilla vacía
    
    private void updateOldPos(int row, int col)
    {
        if (posOK(row, col))
        {
            if (combatPos(row, col)){
                labyrinth.get(row).set(col, MONSTER_CHAR);
            }
            else
            {
                labyrinth.get(row).set(col, EMPTY_CHAR);
            }
        }
    }
    
    // Método dir2Pos
    // Este método se vale de un switch para determinar la nueva posición a la
    // que se va a mover un jugador en función de la dirección de movimiento
    // que tome
    
    private int[] dir2Pos(int row, int col, Directions direction)
    {
        
        int newPos[] = new int[2];
        
        switch (direction)
        {
            case UP:
                newPos[0] = row-1;
                newPos[1] = col;
                break;
            case DOWN:
                newPos[0] = row+1;
                newPos[1] = col;
                break;
            case LEFT:
                newPos[0] = row;
                newPos[1] = col-1;
                break;
            case RIGHT:
                newPos[0] = row;
                newPos[1] = col+1;
                break;
        }
        
        return newPos;
    }
    
    // Método randomEmptyPos()
    // Utiliza el dado para generar una posición aleatoria en el laberinto
    // (row, col) asegurándose de que esté vacía. Genera posiciones hasta que 
    // se cumpla la restricción y una vez hecho, la devuelve. De lo contrario
    // se producirá un bucle infinito
    
    private int[] randomEmptyPos()
    {
        int randomPos[]= new int[2];
        
        int randomRow;
        int randomCol;
        
        do{
            
            randomRow = Dice.nextInt(nRows);
            randomCol = Dice.nextInt(nCols);
            
            randomPos[0] = randomRow;
            randomPos[1] = randomCol;
            
        } while(!emptyPos(randomRow,randomCol));
        
        return randomPos;
    }
    
    public Monster putPlayer(Directions direction, Player player)
    {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol,
            int length)
    {
        /*int row = getRow();
        int col = getCol();
        if (orientation == Orientation.VERTICAL){
            
        }
        
        row = startRow;
        col = startCol;*/
    }
    
    public ArrayList<Directions> validMoves(int row, int col)
    {
        ArrayList<Directions> output = new ArrayList <Directions>();
        
        if(canStepOn(row+1,col)){
            output.add(Directions.DOWN);
        }
        if(canStepOn(row-1,col)){
            output.add(Directions.UP);
        }
        if(canStepOn(row,col+1)){
            output.add(Directions.RIGHT);
        }
        if(canStepOn(row,col-1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, 
            Player player)
    {
        
        Monster output = null;
        
        if (canStepOn(row, col))
        {
            if (posOK(oldRow, oldCol))
            {
                Player p = players.get(oldRow).get(oldCol);
                
                if (p == player)
                {
                    updateOldPos(oldRow,oldCol);
                    players.get(oldRow).set(oldCol, null);
                }
            }
            
            boolean monsterPos = monsterPos(row, col);
            
            if (monsterPos)
            {
                labyrinth.get(row).set(col, COMBAT_CHAR);
                output = monsters.get(row).get(col);
            } else {
                char number = player.getNumber();
                labyrinth.get(row).set(col,number);
            }
            
            players.get(row).set(col,player);
            player.setPos(row, col);
            
        }
        
        return output;
    }
    
      
    
}
