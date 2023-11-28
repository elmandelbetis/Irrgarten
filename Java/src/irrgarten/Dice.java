///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Dice.java      //
///////////////////////////////////////

package irrgarten;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    
    private static final int MAX_USES = 5;
    private static final int MAX_ATTACK = 5;
    private static final int MAX_SHIELD = 2;
    private static final int WEAPONS_REWARD = 2;
    private static final int SHIELDS_REWARD = 3;
    private static final int HEALTH_REWARD = 5;
    
    private static final float MAX_INTELLIGENCE = 10f;
    private static final float MAX_STRENGTH = 10f;
    private static final float RESURRECT_PROB = 0.3f;
    
    private static final Random generator = new Random();
    
    // Método randomPos
    // Devuelve un valor aleatorio de posición de fila o columna en el tablero
    
    public static int randomPos(int max){
        
        int pos = generator.nextInt(max); //devuelve un valor entre 0 y max
        return pos;
        
    }
    
    // Método whoStarts
    // Devuelve el number ID del jugador que empieza la partida, siendo el
    // primero el 0 y el último "nplayers"
    
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers+1);
    }
    
    
    // Métodos randomIntelligence y randomStrength
    // Devuelve un valor de inteligencia o fuerza contenido entre 0-9.9 (float)
    
    public static float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE; 
    }
    
    public static float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH; 
    }   
    
    public static boolean resurrectPlayer(){        
        return (generator.nextFloat()*1f) <= RESURRECT_PROB;
    }
    
    
    // Métodos Reward
    // Cada uno de ellos devuelve el nº de armas, escudo y/o salud que gana un
    // jugador al ganar un combate
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    // Métodos Power
    // Devuelven un valor entre 1 y el máximo al que pueda optar del atributo
    // de instancia al que esté llamando cada uno
    
    public static float weaponPower(){
        int minPower = 1;
        int weaponPower = minPower + generator.nextInt((MAX_ATTACK));
        return weaponPower;
    }
    
    public static float shieldPower(){
        int minShield = 1;
        int shieldPower = minShield + generator.nextInt((MAX_SHIELD));
        return shieldPower;
    }
    
    // Método usesLeft
    // Devuelve el nº de usos que se le asigna aleatoriamente a un arma o
    // escudo, comprendido entre 1 y el máximo de usos permitidos por item
    
    public static int usesLeft(){
        int minimoUses = 1;
        int uses = minimoUses + generator.nextInt(MAX_USES + 1); 
        return uses;
    }
    
    // Método intensity
    // Devuelve la cantidad de competencia aplicada entre 0 y "competence"
    
    public static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    // Método discardElement
    // Devuelve "true" o "false" según los usos sean 0 ó un valor aleatorio 
    // generado entre 0 y 1 sea menor o no al valor de probabilidad calculado
    // en función del nº de usos restantes
    
    public static boolean discardElement(int usesLeft){
        boolean discard = false;
        
        if (usesLeft == 0){
            discard = true;
        }
        
        else{
            
            float probDiscard = (float) usesLeft / MAX_USES;
            float randomValue = generator.nextFloat()*(float) 1.01;
            
            if (randomValue <= probDiscard){
                discard = true;
            }
        }
        return discard;
    }
    
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        float prob = generator.nextFloat()*1f;
        
        if (prob <= intelligence){
                return preference;
        }
        else{
            int anotherDirection = generator.nextInt(validMoves.size());
            return validMoves.get(anotherDirection);
        }
    }
    
    
}
