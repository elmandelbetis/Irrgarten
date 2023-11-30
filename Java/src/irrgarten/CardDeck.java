
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T> {
    
    private ArrayList<T> cardDeck;
    
    public CardDeck()
    {
        cardDeck = new ArrayList<>();
        addCards();
    }
    
    protected abstract void addCards();    
    
    protected void addCard(T card)
    {
        cardDeck.add(card);
        Collections.shuffle(cardDeck);  // barajado de las cartas
    }
    
    public T nextCard()
    {
        if (cardDeck.isEmpty()){
            addCards();
        }
        
        T selectedCard = cardDeck.get(0);
        cardDeck.remove(selectedCard);
        
        return selectedCard;
               
    }
}
