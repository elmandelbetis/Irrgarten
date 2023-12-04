/**
 * @file UI/UI.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package UI;

import irrgarten.Directions;
import irrgarten.GameState;

public interface UI {
    
    public Directions nextMove();
    public void showGame(GameState gameState);
    
    
}
