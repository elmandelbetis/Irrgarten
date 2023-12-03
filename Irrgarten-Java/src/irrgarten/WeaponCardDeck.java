/**
 * @file irrgarten/WeaponCardDeck.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Baraja de cartas de arma de valores aleatorios para el jugador,
 * que le serán entregadas como recompensa al ganar combates.
 * Dependiente de la clase paramétrica abstracta CardDeck
 */

public class WeaponCardDeck extends CardDeck<Weapon> {
    
    //*****************************************************************
    // Constante 
    //*****************************************************************
    
    private static final int MAX_WEAPON_CARDS = 2;  // máximo de cartas de arma permitidas
    
    /**
     * Método de adición de todas las armas a su respectiva baraja, una por una
     * mediante el uso del método addCard() de la clase padre
     */
    
    @Override
    protected void addCards()
    {
        for (int i = 0; i < MAX_WEAPON_CARDS; i++){
            Weapon weaponCard = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            super.addCard(weaponCard);
        }
    }
    
}
