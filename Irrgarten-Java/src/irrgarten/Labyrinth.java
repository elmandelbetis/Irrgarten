/**
 * @file irrgarten/Labyrinth.java
 * @author Álvaro Maldonado Medina, grupo 2D-D3
 */

package irrgarten;

import static irrgarten.Directions.DOWN;
import static irrgarten.Directions.LEFT;
import static irrgarten.Directions.RIGHT;
import static irrgarten.Directions.UP;
import java.util.ArrayList;

/**
 * Clase encargada de generar el esqueleto del juego, el laberinto donde se va
 * a desarrollar toda la acción, y de gestionarlo. Tiene forma de matriz 2D.
 */

public class Labyrinth {
    
    //*****************************************************************
    // Constantes 
    //*****************************************************************
    
    private static final char BLOCK_CHAR = 'X'; // indicador de bloques
    private static final char EMPTY_CHAR = '-'; // indicador de casilla vacía
    private static final char MONSTER_CHAR = 'M';   // indicador de monstruo
    private static final char COMBAT_CHAR = 'C';    // indicador de casilla de combate
    private static final char EXIT_CHAR = 'E';  // indicador de casilla de salida
    private static final int ROW = 0; 
    private static final int COL = 1;     
    private int nRows, nCols, exitRow, exitCol; // nº de filas y columnas, fila 
                                                // y columna de la casilla de salida
    
    public Monster[][] monsters; // monstruos en el laberinto
    public Player[][] players;  // jugadores en el laberinto
    public char[][] labyrinth;  // laberinto
    
    
    /**
     * Constructor del laberinto
     * @param nRows Número de filas
     * @param nCols Número de columnas
     * @param exitRow Número de fila de la casilla de salida
     * @param exitCol Número de columna de la casilla de salida
     */
       
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

    
    /**
     * Método que se encarga de calcular si hay un ganador o no.
     * @return true si hay un jugador en la casilla de salida.
     */
    
    public boolean haveAWinner()
    {
        return players[exitRow][exitCol] != null;
    }
    
    /**
     * Método toString
     * @return Cadena de caracteres con la matriz del laberinto y su estado actual
     */
    
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
    
    /**
     * Calcula si la posición pasada como parámetro es correcta o no
     * @param row Fila
     * @param col Columna
     * @return true si la posición es correcta
     */
    
    private boolean posOK(int row, int col){
        return (row >= 0) && (row <= nRows) && (col >= 0) && (col <= nCols);
    }
    
   /**
    * Añade monstruos al laberinto, y al contenedor de éstos
    * @param row Fila de la casilla donde se añade el monstruo
    * @param col Columna de la casilla donde se añade el monstruo
    * @param monster Monstruo
    */
    
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
    
    /**
     * Método que se encarga de esparcir a los jugadores por las posiciones
     * válidas del laberinto que quedan libres tras la configuración
     * @param players Lista de jugadores
     */
    
    public void spreadPlayers(ArrayList<Player> players)
    {
        for (int i = 0; i < players.size(); i++)
        {
            Player p = players.get(i);
            ArrayList<Integer> pos = randomEmptyPos();            
            putPlayer2D(-1,-1, pos.get(ROW), pos.get(COL), p);     
        }
    }
    
    /**
     * Calcula si la posición pasada como parámetro está o no vacía
     * @param row Fila
     * @param col Columna
     * @return true si la posición está vacía
     */
    
    private boolean emptyPos(int row, int col)
    {           
        return labyrinth[row][col] == EMPTY_CHAR;
    }
    
    /**
     * Método que indica si una posición pasada como parámetro 
     * contiene un monstruo o no
     * @param row Fila de la casilla
     * @param col Columna de la casilla
     * @return True o false dependiendo de si es una monsterPos o no
     */
    
    private boolean monsterPos(int row, int col)
    {        
        return labyrinth[row][col] == MONSTER_CHAR;
    }
    
    /**
     * Calcula si la posición dada como parámetro es la de salida
     * @param row Fila de la casilla
     * @param col Columna de la casilla
     * @return True o false dependiendo de si es o no la exitPos
     */
    
    private boolean exitPos(int row, int col)
    {
        return labyrinth[row][col] == EXIT_CHAR;
    }
    
    /**
     * Calcula si la posicón dada como parámetro es de combate o no
     * @param row Fila
     * @param col Columna
     * @return True o false dependiendo de si es combatPos
     */
    
    private boolean combatPos(int row, int col)
    {        
        return labyrinth[row][col] == COMBAT_CHAR;
    }
    
    /**
     * Indica si en una posición el jugador puede moverse sobre ella o no
     * @param row Fila
     * @param col Columna
     * @return True o false si puede caminar o no sobre la posición
     */
    
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
    
    /**
     * Actualiza una posición a su estado anterior a que el jugador pasara
     * sobre ella: si era de combate, pasa a ser de monstruo, o de lo contrario
     * pasa a ser casilla vacía
     * @param row Fila
     * @param col Columna
     */
    
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
    
    /**
     * Determina la posición a la que se va a mover un jugador en función de 
     * la dirección que tome
     * @param row Fila
     * @param col Columna
     * @param direction Dirección de movimiento
     * @return La nueva posición a la que se moverá el jugador
     */
    
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
    
    /**
     * Genera posiciones aleatorias usando el método randomPos de la clase Dice
     * hasta dar con una que esté vacía. Mientras tanto, se mantiene en un bucle
     * infinito
     * @return Una posición válida generada aleatoriamente
     */
    
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
    
    /**
     * Mueve un jugador a una posición determinada y devuelve un posible monstruo
     * si el movimiento resulta en la presencia de uno en la nueva posición del
     * jugador
     * @param direction Dirección de movimiento del jugador
     * @param player Jugador
     * @return El posible monstruo.
     */
    
    public Monster putPlayer(Directions direction, Player player)
    {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        ArrayList<Integer> newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos.get(ROW), newPos.get(COL), player);
        
        return monster;
    }
    
    /**
     * Método que gestiona la creación y adición de un bloque o una fila
     * de éstos en hileras verticales u horizontales en el laberinto
     * @param orientation Orientación de la hilera de bloques
     * @param startRow Fila de inicio de la hilera
     * @param startCol Columna de inicio de la hilera
     * @param length Longitud de la hilera
     */
    
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
    
    /**
     * Método que crea una lista con todos los posibles movimientos válidos que
     * puede tomar un jugador antes de que éste introduzca una dirección de
     * movimiento por teclado
     * @param row Fila
     * @param col Columna
     * @return La lista de movimientos válidos mencionada
     */
    
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
    
    /**
     * Método que coloca jugadores en posiciones bidimensionales en el laberinto
     * y actualiza el estado de la posición en función de donde éste haya caído.
     * @param oldRow Fila de la casilla anterior del jugador
     * @param oldCol Estado anterior de la columna de la casilla
     * @param row Fila de la nueva casilla
     * @param col Columna de la nueva casilla
     * @param player Jugador
     * @return un output en forma de monstruo, nulo si no lo había, monstruo si lo había
     */
    
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
    
    //*****************************************************************
    // Getters 
    //*****************************************************************
    
    /**
     * Row getter
     * @return Número de filas del laberinto
     */
    
    public int getRows()
    {
        return nRows;
    }
    
    /**
     * Column getter
     * @return Número de columnas del laberinto
     */
    
    public int getCols()
    {
        return nCols;
    }
    
    /**
     * Coloca un FuzzyPlayer en el mismo sitio donde muere un Player, al resucitar
     * NOTA: al resucitar, el FuzzyPlayer no aparece, pero en el momento en que se mueva
     * a una nueva casilla, éste aparecerá sin problema. He tratado de arreglarlo
     * de varias maneras y no funciona
     * 
     * @param fuzzyPlayer El FuzzyPlayer
     * @param row Fila de la casilla donde se coloca la instancia
     * @param col Columna de la casilla donde se coloca la instancia
     */
    
    public void placeFuzzyPlayer(FuzzyPlayer fuzzyPlayer, int row, int col){
        if (posOK(row,col)){
            labyrinth[row][col] = EMPTY_CHAR;
            fuzzyPlayer.setPos(row, col);
            
        }
    }
      
    
}
