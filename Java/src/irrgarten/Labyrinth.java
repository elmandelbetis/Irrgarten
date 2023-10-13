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
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    private Monster[][] monsters; //realmente no sé muy bien qué hago
    private Player[][] players;  //pero siempre se puede preguntar en clase
    private char[][] cells;
    
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
     
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        this.monsters = new Monster[nRows][nCols];  // (?)
        this.players = new Player[nRows][nCols];   // (?)
        this.cells = new char [nRows][nCols];
        
    }
    
    public boolean haveAWinner()
    {
        boolean haveAWinner = false;
        
        for (ROW = 0; ROW < nRows; ROW++){
            for (COL = 1; COL < nCols; COL++){
                if (cells[ROW][COL] == EXIT_CHAR){
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
    
    
    public boolean posOK(int row, int col){
        
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
        
        if (cells[row][col] == EMPTY_CHAR && posOK)
        {
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    
    
    
    
    
}
