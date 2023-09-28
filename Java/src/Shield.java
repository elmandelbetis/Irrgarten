
public class Shield {
    
    private float protection;
    private int uses;
    
    public Shield(float prot, int us){
        protection = prot;
        uses = us;
    }
    
    public float protect(){
        
        if (uses>0){
            uses--;
            return protection;
        }
        else{
            return 0;
        }
    }
    
    public String toString(){
        return "S["+protection+", "+uses+"]";
    }
}
