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
    private static final int ROW = 0; 
    private static final int COL = 1;     
    private int nRows, nCols, exitRow, exitCol;
    
    public Monster[][] monsters; 
    public Player[][] players;  
    public char[][] labyrinth;
    
    
    // Constructor de la clase
       
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
     
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new Monster[nRows+1][nCols+1];
        this.players = new Player[nRows+1][nCols+1];
        this.labyrinth = new char[nRows+1][nCols+1];
        
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                labyrinth[i][j] = EMPTY_CHAR;
            }
        }
        
        labyrinth[exitRow][exitCol] = EXIT_CHAR;
        
    }

    
    // Método haveAWinner
    // Booleano que devuelve "true" en caso de que un jugador, habiendo
    // realizado las respectivas comprobaciones, haya llegado a la casilla 
    // de salida
    
    public boolean haveAWinner()
    {
        return players[exitRow][exitCol] != null;
    }
    
    // Método toString()
    // Devuelve una cadena de caracteres con el estado actual del laberinto
    
    @Override
    public String toString()
    {
        String estado = "";
        
        for (int i=0; i < nRows; i++){
            for (int j=0; j < nCols; j++){
                estado += " ["+labyrinth[i][j]+"]";
            }
            estado += "\n";
        }
        
        estado +="\n";
        estado +="Tamaño del laberinto: "+getRows()+"x"+getCols()+"\n"
                + "Casilla de salida: ["+exitRow+","+exitCol+"]";
        return estado;
        
        
    }
    
    // Método posOK()
    // Devuelve "true" si la posición (row, col) pasada como parámetro
    // es real y se encuentra en el tablero
    
    private boolean posOK(int row, int col){
        return (row >= 0) && (row <= nRows) && (col >= 0) && (col <= nCols);
    }
    
    // Método addMonster()
    // Si cierta casilla está vacía y se encuentra en el tablero,
    // Anotamos la existencia de un monstruo dentro de esta, la almacenamos
    // en el contenedor de monstruos e indicamos al monstruo dónde se encuentra
    
    public void addMonster(int row, int col, Monster monster)
    {           
        while(!emptyPos(row,col)){
            row = Dice.randomPos(getRows());
            col = Dice.randomPos(getCols());
        }
        
        labyrinth[row][col] = MONSTER_CHAR;
        monsters[row][col] = monster;
        monster.setPos(row, col);
    }
    
    public void spreadPlayers(ArrayList<Player> players)
    {
        for (int i = 0; i < players.size(); i++)
        {
            Player p = players.get(i);
            ArrayList<Integer> pos = randomEmptyPos();            
            putPlayer2D(-1,-1, pos.get(ROW), pos.get(COL), p);     
        }
    }
    
    // Método emptyPos()
    // Si la casilla pasada como parámetro está vacía, devuelve "true"
    
    private boolean emptyPos(int row, int col)
    {           
        return labyrinth[row][col] == EMPTY_CHAR;
    }
    
    // Método monsterPos()
    // Pasamos una casilla como parámetro, y si un monstruo se encuentra
    // en ella, devolvemos "true". "False" en caso contrario
    
    private boolean monsterPos(int row, int col)
    {        
        return labyrinth[row][col] == MONSTER_CHAR;
    }
    
    // Método exitPos()
    // Devuelve "true" o "false" en función de si la casilla pasada como
    // argumento es o no la de salida
    
    private boolean exitPos(int row, int col)
    {
        return labyrinth[row][col] == EXIT_CHAR;
    }
    
    // Método exitPos()
    // Devuelve "true" o "false" en función de si la casilla pasada como
    // argumento es o no una de combate (que se encuentren en ella tanto un
    // jugador como un monstruo)
    
    private boolean combatPos(int row, int col)
    {        
        return labyrinth[row][col] == COMBAT_CHAR;
    }
    
    // Método canStepOn()
    // Indica si la posición se encuentra dentro del tablero y se corresponde
    // o con una casilla vacía, monstruo o es la casilla de salida
    
    private boolean canStepOn(int row, int col)
    {           
        if (posOK(row,col))
        {
            if (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col))
            {
                return true;
            }
        
        }
        return false;
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
            if (combatPos(row, col))
                labyrinth[row][col] = MONSTER_CHAR;
            else
                labyrinth[row][col] = EMPTY_CHAR;
        }
    }
    
    // Método dir2Pos
    // Este método se vale de un switch para determinar la nueva posición a la
    // que se va a mover un jugador en función de la dirección de movimiento
    // que tome
    
    private ArrayList<Integer> dir2Pos(int row, int col, Directions direction)
    {
        if (posOK(row,col)){
            switch (direction)
            {
                case UP:
                    row--;
                    break;
                case DOWN:
                    row++;
                    break;
                case LEFT:
                    col--;
                    break;
                case RIGHT:
                    col++;
                    break;
            }
        }
        
        ArrayList<Integer> newPos = new ArrayList<>();
        newPos.add(row);
        newPos.add(col);
        
        return newPos;
    }
    
    // Método randomEmptyPos()
    // Utiliza el dado para generar una posición aleatoria en el laberinto
    // (row, col) asegurándose de que esté vacía. Genera posiciones hasta que 
    // se cumpla la restricción y una vez hecho, la devuelve. De lo contrario
    // se producirá un bucle infinito
    
    private ArrayList<Integer> randomEmptyPos()
    {
        ArrayList<Integer> randomPos= new ArrayList<>();
        
        int randomRow;
        int randomCol;
        
        do{
            randomRow = Dice.randomPos(nRows);
            randomCol = Dice.randomPos(nCols);
        } while(!(emptyPos(randomRow,randomCol)));
        
        randomPos.add(randomRow);
        randomPos.add(randomCol);
        
        return randomPos;
    }
    
    public Monster putPlayer(Directions direction, Player player)
    {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        ArrayList<Integer> newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos.get(ROW), newPos.get(COL), player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol,
            int length)
    {
        int incRow, incCol;
        
        if (orientation == Orientation.VERTICAL)
        {
             incRow = 1;
             incCol = 0;
        } else {
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow, col = startCol;
        
        while ((posOK(row, col)) && (emptyPos(row,col)) && (length > 0))
        {
            labyrinth[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col)
    {
        ArrayList<Directions> output = new ArrayList <>();
        
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
                Player p = players[oldRow][oldCol];
                
                if (p == player)
                {
                    updateOldPos(oldRow,oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            boolean monsterPos = monsterPos(row, col);
            
            if (monsterPos)
            {
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            } else {
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            
            players[row][col] = player;
            player.setPos(row, col);
            
        }
        
        return output;
    }
    
    public int getRows()
    {
        return nRows;
    }
    
    public int getCols()
    {
        return nCols;
    }
      
    
}
