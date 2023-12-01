package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {
    
    private static final int MAX_SHIELD_CARDS = 3;
    
    @Override
    protected void addCards()
    {        
        for (int i = 0; i < MAX_SHIELD_CARDS; i++){
            Shield shieldCard = new Shield(Dice.shieldPower(), Dice.usesLeft());
            super.addCard(shieldCard);
        }
    }
    
}
