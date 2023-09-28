
import java.util.Random;

public class Dice {
    
    private static int MAX_USES = 5;
    private static int MAX_ATTACK = 5;
    private static int MAX_SHIELD = 2;
    private static int WEAPONS_REWARD = 2;
    private static int SHIELDS_REWARD = 3;
    private static int HEALTH_REWARD = 5;
    
    private static float MAX_INTELLIGENCE = (float) 10.0;
    private static float MAX_STRENGTH = (float) 10.0;
    private static float RESURRECT_PROB = (float) 0.3;
    
    private Random generator;
    
    
    public Dice(){
        generator = new Random();
    }
    
    public int nextInt(int num){
        return generator.nextInt(num);
    }
    
    public float nextFloat(){
        return generator.nextFloat();
    }
    
    public int randomPos(int max){}
    
    public int whoStarts(int nplayers){}
    
    public float randomIntelligente(){}
    
    public float randomStrength(){}
    
    boolean resurrectPlayer(){}
}
