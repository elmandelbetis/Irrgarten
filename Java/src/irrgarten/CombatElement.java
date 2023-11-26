
package irrgarten;

public abstract class CombatElement {

    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        if (uses > 0){
            uses--;
            return effect;
        } else {
            return 0;
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    @Override
    public String toString(){
        return "["+effect+", "+uses+"]";
    }
}
