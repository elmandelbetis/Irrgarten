package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player{
    
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    // TODO Añadir resto de métodos del diagrama de clases
    public Directions move(Directions direction, ArrayList<Directions> validMoves)
    {
        super.move(direction, validMoves);
        return Dice.nextStep(direction, validMoves, super.getIntelligence());
    }
    
    @Override
    public float attack()
    {
        return (super.sumWeapons() + Dice.intensity(super.getStrength()) );
    }
    
    @Override
    protected float defensiveEnergy()
    {
        return (super.sumShields() + Dice.intensity(super.getIntelligence()) );
    }
    
    @Override
    public String toString()
    {
        return "Fuzzy"+super.toString();
    }
    
}
