
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T> {
    
    private ArrayList<T> cardDeck;
    
    public CardDeck()
    {
        cardDeck = new ArrayList<T>();
    }
    
    protected abstract void addCards();    
    
    protected void addCard(T card)
    {
        this.cardDeck.add(card);
    }
    
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
