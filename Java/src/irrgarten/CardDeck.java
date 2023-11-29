
package irrgarten;

import java.util.ArrayList;

public abstract class CardDeck<T> {
    
    private ArrayList<T> cardDeck;
    
    public CardDeck()
    {
        cardDeck = new ArrayList<>();
    }
    
    protected abstract void addCards();    
    
    protected void addCard(T card)
    {
        cardDeck.add(card);
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
