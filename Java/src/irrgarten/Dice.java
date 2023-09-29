package irrgarten;

import java.util.Random;

public class Dice {
    
    private static final int MAX_USES = 5;
    private static final int MAX_ATTACK = 5;
    private static final int MAX_SHIELD = 2;
    private static final int WEAPONS_REWARD = 2;
    private static final int SHIELDS_REWARD = 3;
    private static final int HEALTH_REWARD = 5;
    
    private static final float MAX_INTELLIGENCE = (float) 10.0;
    private static final float MAX_STRENGTH = (float) 10.0;
    private static final float RESURRECT_PROB = (float) 0.3;
    
    private static Random generator = new Random();
    
    
    public Dice(){
        generator = new Random();
    }
    
    public static int nextInt(int num){
        return generator.nextInt(num);
    }
    
    public static float nextFloat(){
        return generator.nextFloat();
    }
    
    public static int randomPos(int max){
        
        int pos = generator.nextInt(max+1); //devuelve un valor entre 0 y max
        return pos;
        
    }
    
    public static int whoStarts(int nplayers){
        int player = generator.nextInt(nplayers + 1);
        return player;
    }
    
    public static float randomIntelligence(){
        float intelligence = generator.nextFloat()*MAX_INTELLIGENCE; //PREGUNTAR!!!
        return intelligence;
    }
    
    public static float randomStrength(){
        float strength = generator.nextFloat()*MAX_STRENGTH;//PREGUNTAR!!!
        return strength;
    }
    
    public static boolean resurrectPlayer(){
        
        boolean resurrect = false;
        float prob_res = generator.nextFloat();
        
        if (prob_res < RESURRECT_PROB){
            resurrect = true;
            return resurrect;
        }
        else{
            return resurrect;
        }
    }
}
