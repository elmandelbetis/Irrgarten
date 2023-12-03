/**
 * @file irrgarten/CardDeck.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase abstracta y paramétrica que se encarga de generar y gestionar barajas 
 * de cartas de arma o de escudo en función del parámetro T
 * 
 * @param <T> Parámetro designado para distinguir cardDecks de un tipo u otro.
 */

public abstract class CardDeck<T> {
    
    //******************************************************************
    // Atributo
    //******************************************************************
    
    private ArrayList<T> cardDeck;  // Baraja de cartas en forma de ArrayList
    
    //*******************************************************************
    // Constructor
    //*******************************************************************
    
    /**
     * Crea una baraja de cartas.
     */
    
    public CardDeck()
    {
        cardDeck = new ArrayList<T>();
    }
    
    /**
     * Método abstracto que se sobreescribe en las clases hijas weaponCardDeck.
     * y shieldCardDeck y se encarga de añadir cartas a una baraja.
     */
    
    protected abstract void addCards();    
    
    /**
     * Método que se encarga de añadir UNA carta del tipo T a la baraja.
     * @param card 
     */
    
    protected void addCard(T card)
    {
        this.cardDeck.add(card);
    }
    
    /**
     * Método que se encarga de devolver una carta de la baraja. Si la
     * baraja está vacía de antemano, añade cartas y las baraja.
     * @return la carta seleccionada
     */
    
    public T nextCard()
    {
        if (cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        
        T selectedCard = cardDeck.get(0);
        cardDeck.remove(0);
        
        return selectedCard;
               
    }
}
