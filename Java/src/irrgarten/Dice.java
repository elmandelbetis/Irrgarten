///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Dice.java      //
///////////////////////////////////////

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
    
    private static final Random generator = new Random();
    
    // Método nextInt 
    // Devuelve un valor aleatorio entero entre 0 y el parámetro dado
    
    public static int nextInt(int num){
        return generator.nextInt(num+1); // Ponemos (num+1) para contener a num
                                         // en el intervalo
    }
    
    // Método nextFloat
    // Devuelve un valor en coma flotante entre 0 y el parámetro dado
    
    public static float nextFloat(){
        return generator.nextFloat();
    }
    
    // Método randomPos
    // Devuelve un valor aleatorio de posición de fila o columna en el tablero
    public static int randomPos(int max){
        
        int pos = generator.nextInt(max+1); //devuelve un valor entre 0 y max
        return pos;
        
    }
    
    // Método whoStarts
    // Devuelve el number ID del jugador que empieza la partida, siendo el
    // primero el 0 y el último "nplayers"
    
    public static int whoStarts(int nplayers){
        int player = generator.nextInt(nplayers + 1);
        return player;
    }
    
    
    // Métodos randomIntelligence y randomStrength
    // Devuelve un valor de inteligencia o fuerza contenido entre 0-9.9 (float)
    
    public static float randomIntelligence(){
        float intelligence = generator.nextFloat()*MAX_INTELLIGENCE; 
        return intelligence;
    }
    
    public static float randomStrength(){
        float strength = generator.nextFloat()*MAX_STRENGTH; 
        return strength;
    }
    
    
    // Método resurrectPlayer
    // Devuelve un valor booleano llamado "resurrect" que funciona de la 
    // siguiente manera: si el valor de probabilidad aleatoria "prob_res" es 
    // menor o igual a RESURRECT_PROB (0.3), se resucita al jugador. Si no,
    // no resucita el jugador
    
    
    public static boolean resurrectPlayer(){
        
        boolean resurrect = false;
        float prob_res = generator.nextFloat()*1;
        
        if (prob_res < RESURRECT_PROB){
            resurrect = true;
            return resurrect;
        }
        else{
            return resurrect;
        }
    }
    
    
    // Métodos Reward
    // Cada uno de ellos devuelve el nº de armas, escudo y/o salud que gana un
    // jugador al ganar un combate
    
    public static int weaponsReward(){
        int nweapons = generator.nextInt(WEAPONS_REWARD + 1);
        return nweapons;
    }
    
    public static int shieldsReward(){
        int nshields = generator.nextInt(SHIELDS_REWARD + 1);
        return nshields;
    }
    
    public static int healthReward(){
        int hp_units = generator.nextInt(HEALTH_REWARD + 1);
        return hp_units;
    }
    
    // Métodos Power
    // Devuelven un valor entre 0 y el máximo al que pueda optar del atributo
    // de instancia al que esté llamando cada uno
    
    public static float weaponPower(){
        int attack = generator.nextInt(MAX_ATTACK + 1);
        return attack;
    }
    
    public static float shieldPower(){
        int sh_power = generator.nextInt(MAX_SHIELD + 1);
        return sh_power;
    }
    
    // Método usesLeft
    // Devuelve el nº de usos que se le asigna aleatoriamente a un arma o
    // escudo, comprendido entre 0 y el máximo de usos permitidos por item
    
    public static int usesLeft(){
        int uses = generator.nextInt(MAX_USES + 1);
        return uses;
    }
    
    // Método intensity
    // Devuelve la cantidad de competencia aplicada entre 0 y "competence"
    
    public static float intensity(float competence){
        float intensity = generator.nextFloat()*competence;
        return intensity;
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
            
            float prob = (float) usesLeft / MAX_USES;
            float randomValue = generator.nextFloat()*(float) 1.01;
            
            if (randomValue >= prob){
                discard = true;
            }
        }
        return discard;
    }
    
    
}
