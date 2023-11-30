package irrgarten;

public class ShieldCardDeck extends CardDeck {
    
    @Override
    protected void addCards()
    {
        int maxCards = 3;
        
        for (int i = 0; i < maxCards; i++){
            Shield shieldCard = new Shield(Dice.shieldPower(), Dice.usesLeft());
            super.addCard(shieldCard);
        }
        
    }
    
}
