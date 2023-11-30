package irrgarten;

public class WeaponCardDeck extends CardDeck {
    
    @Override
    protected void addCards()
    {
        int maxCards = 2;
        
        for (int i = 0; i < maxCards; i++){
            Weapon weaponCard = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            super.addCard(weaponCard);
        }
    
    }
    
}
