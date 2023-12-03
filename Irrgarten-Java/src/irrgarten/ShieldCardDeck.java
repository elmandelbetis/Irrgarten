/**
 * @file irrgarten/ShieldCardDeck.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Baraja de cartas de escudo de valores aleatorios para el jugador,
 * que le serán entregadas como recompensa al ganar combates.
 * Dependiente de la clase paramétrica abstracta CardDeck
 */

public class ShieldCardDeck extends CardDeck<Shield> {
    
    //*****************************************************************
    // Constante 
    //*****************************************************************
    
    private static final int MAX_SHIELD_CARDS = 3;  // nº máximo de escudos que pueden generarse
    
    /**
     * Método de adición de todos los escudos a su respectiva baraja, uno por uno
     * mediante el uso del método addCard() de la clase padre
     */
    
    @Override
    protected void addCards()
    {        
        for (int i = 0; i < MAX_SHIELD_CARDS; i++){
            Shield shieldCard = new Shield(Dice.shieldPower(), Dice.usesLeft());
            super.addCard(shieldCard);
        }
    }
    
}
