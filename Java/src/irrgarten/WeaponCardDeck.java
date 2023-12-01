package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {
    
    private static final int MAX_WEAPON_CARDS = 2;
    
    @Override
    protected void addCards()
    {
        for (int i = 0; i < MAX_WEAPON_CARDS; i++){
            Weapon weaponCard = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            super.addCard(weaponCard);
        }
    }
    
}
