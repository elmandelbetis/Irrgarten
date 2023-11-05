///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Labyrinth.java //
///////////////////////////////////////

package irrgarten;

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
    
    private Monster[][] monsters; //realmente no sé muy bien qué hago
    private Player[][] players;  //pero siempre se puede preguntar en clase
    private char[][] labyrinth;
    
    
    
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
        
        this.players = new Player[nRows][nCols];
        this.monsters = new Monster[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];
        
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
                if (labyrinth[i][j] == EXIT_CHAR)
                {
                    if(players[i][j] != null)
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
        
        if (labyrinth[row][col] == EMPTY_CHAR && posOK)
        {   
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row, col);
            
        }
    }
    
    // próximas prácticas
    
    private void spreadPlayers(Player[] players)
    {
        throw new UnsupportedOperationException();
    }
    
    // Método emptyPos()
    // Si la casilla pasada como parámetro está vacía, devuelve "true"
    
    private boolean emptyPos(int row, int col)
    {   
        boolean isEmpty = false;
        
        if (labyrinth[row][col] == EMPTY_CHAR)
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
        
        if (labyrinth[row][col] == MONSTER_CHAR)
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
        
        if (labyrinth[row][col] == EXIT_CHAR){
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
        
        if (labyrinth[row][col] == COMBAT_CHAR){
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
                labyrinth[row][col] = MONSTER_CHAR; //IDEA
            }
            else
            {
                labyrinth[row][col] = EMPTY_CHAR;
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
    
    public void spreadPlayers()
    {}
    
    public Monster putPlayer(Directions direction, Player player)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol,
            int length)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
    
    public Directions[] validMoves(int row, int col)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, 
            Player player)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
    
    
    
    
    
    
    
    
}