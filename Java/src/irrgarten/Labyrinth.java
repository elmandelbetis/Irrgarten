package irrgarten;
import java.util.ArrayList;

public class Labyrinth {
    
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static int ROW = 0;
    private static int COL = 1;
    
    private final int nRows;
    private final int nCols;
    private final int exitRow;
    private final int exitCol;
    
    private final ArrayList<ArrayList<Monster>> monsters; //realmente no sé muy bien qué hago
    private final ArrayList<ArrayList<Player>> players;  //pero siempre se puede preguntar en clase
    private final ArrayList<ArrayList<Character>> cells;
    
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
     
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        this.monsters = new ArrayList<>(nRows);  // (?)
        this.players = new ArrayList<>(nRows);   // (?)
        this.cells = new ArrayList<>(nRows);
        
    }
    
    public boolean haveAWinner()
    {
        boolean haveAWinner = false;
        
        for (ROW = 0; ROW < nRows; ROW++){
            for (COL = 1; COL < nCols; COL++){
                if (cells.get(ROW).get(COL) == EXIT_CHAR){
                    haveAWinner = true;
                }
            }
        }
        
        return haveAWinner;
    }
    
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException();
    }
    
    
    private boolean posOK(int row, int col){
        
        boolean posOK = false;
        
        if (row >= 0 && row <= nRows && col >= 0 && col <= nCols)
        {
            posOK = true;
        }
        
        return posOK;
    }
    
    public void addMonster(int row, int col, Monster monster)
    {   
        boolean posOK = posOK(row, col);
        
        if (cells.get(row).get(col) == EMPTY_CHAR && posOK)
        {
            monsters.get(row).set(col, monster);
            monster.setPos(row, col);
            cells.get(row).set(col, 'M');
        }
    }
    
    private boolean emptyPos(int row, int col)
    {   
        boolean isEmpty = false;
        
        if (cells.get(row).get(col) == EMPTY_CHAR)
        {
            isEmpty = true;
        }
        
        return isEmpty;
    }
    
    private boolean monsterPos(int row, int col)
    {
        boolean monsterThere = false;
        
        if (cells.get(row).get(col) == MONSTER_CHAR)
        {
            monsterThere = true;
        }
        
        return monsterThere;
    }
    
    private boolean exitPos(int row, int col)
    {
        boolean exitPos = false;
        
        if (cells.get(row).get(col) == EXIT_CHAR){
            exitPos = true;
        }
        
        return exitPos;
    }
    
    private boolean combatPos(int row, int col)
    {
        boolean combatPos = false;
        
        if (cells.get(row).get(col) == COMBAT_CHAR){
            combatPos = true;
        }
        
        return combatPos;
    }
    
    private boolean canStepOn(int row, int col)
    {
        throw new UnsupportedOperationException(); // provisional
    }
    
    
    
    
    
    
    
    
}
