
public class Weapon {
    
    private float power;
    private int uses;
    
    public Weapon(float p, int u){
        power = p;
        uses = u;
    }
    
    public float attack(){
        
        if (uses > 0){
            uses--;
            return power;
        }
        else{
            return 0;
        }
            
    }
    
    @Override
    public String toString(){
        return "W["+power+", "+uses+"]";
    }
}
