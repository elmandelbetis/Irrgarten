
package irrgarten;

public class TestP2 {

    public static void main(String[] args) {
        
        // Prueba clase Game (métodos de la Práctica 2)
        
        System.out.println("\nPRUEBA CLASE GAME (HASTA PRÁCTICA 2)");
        
        Game juego = new Game(5);
        
        System.out.println(juego.finished());
        
        GameState infoJuego = juego.getGameState();
        
        System.out.println(infoJuego.getLabyrinth());
        System.out.println(infoJuego.getLog());
        System.out.println(infoJuego.getMonsters());
        System.out.println(infoJuego.getPlayers());
        System.out.println(infoJuego.getWinner());
        System.out.println(infoJuego.getCurrentPlayer());
        
        System.out.println("\n");
        
        
        
        
        
    }
    
}
