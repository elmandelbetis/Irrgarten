/**
 * @file irrgarten/Dice.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que se encarga de gestionar todas las decisiones aleatorias
 * internas del juego.
 */

public class Dice {
    
    //*****************************************************************
    // Constantes 
    //*****************************************************************
    
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
    
    /**
     * Método que se encarga de generar posiciones aleatorias en torno a
     * un máximo permitido.
     * @param max máximo valor permitido.
     * @return La posición aleatoria generada.
     */
    
    public static int randomPos(int max){
        
        int pos = generator.nextInt(max); //devuelve un valor entre 0 y max
        return pos;
        
    }
    
    /**
     * Método que se encarga de generar al inicio del juego quién empieza
     * la partida.
     * @param nplayers Número de jugadores en la partida.
     * @return El jugador que empieza la partida.
     */
    
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers+1);
    }

    /**
     * Generador de inteligencia aleatoria para los personajes.
     * @return La inteligencia aleatoria.
     */
    
    public static float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE; 
    }
    
    /**
     * Generador aleatorio de fuerza para los personajes.
     * @return La fuerza aleatoria.
     */
    
    public static float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH; 
    }   
    
    /**
     * Genera un valor, y lo compara con la constante RESURRECT_PROB
     * para determinar si se puede resucitar a un jugador o no.
     * @return true si el valor generado es menor o igual a la constante.
     */
    
    public static boolean resurrectPlayer(){        
        return (generator.nextFloat()*1f) <= RESURRECT_PROB;
    }
    
    /**
     * Genera una cantidad aleatoria entera de armas a recibir como recompensa.
     * @return La recompensa de armas.
     */
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    /**
     * Genera una cantidad aleatoria entera de escudos a recibir como recompensa.
     * @return La recompensa de escudos.
     */
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    /**
     * Genera una cantidad aleatoria de salud a recibir como recompensa.
     * @return La recompensa de salud.
     */
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    /**
     * Genera un valor aleatorio de poder para un arma.
     * @return El valor generado (excluyendo al cero).
     */
    
    public static float weaponPower(){
        int minPower = 1;
        int weaponPower = minPower + generator.nextInt((MAX_ATTACK));
        return weaponPower;
    }
    
    /**
     * Genera un valor aleatorio de protección para un escudo.
     * @return El valor generado (excluyendo al cero).
     */
    
    public static float shieldPower(){
        int minShield = 1;
        int shieldPower = minShield + generator.nextInt((MAX_SHIELD));
        return shieldPower;
    }
    
    /**
     * Genera un valor aleatorio de usos para un item.
     * @return El valor generado (excluyendo al cero).
     */
    
    public static int usesLeft(){
        int minimoUses = 1;
        int uses = minimoUses + generator.nextInt(MAX_USES); 
        return uses;
    }
    
    /**
     * Genera un valor de intensidad de ataque.
     * @param competence Máximo de intensidad permitido.
     * @return El valor generado.
     */
    
    public static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    /**
     * Devuelve true con una probabilidad inversamente proporcional a lo 
     * cercano que esté el parámetro del número máximo de usos que 
     * puede tener un arma o escudo. Como casos extremos, si el número de
     * usos es el máximo devolveráy si es 0 devolverá true. Es decir, las 
     * armas o escudos con más usos posibles es menosprobable que sean descartados.
     * 
     * @param usesLeft Número de usos restantes.
     * @return True o False según lo ya mencionado.
     */
    
    public static boolean discardElement(int usesLeft){
        
        switch (usesLeft) {
            case 0:
                return true;
            case MAX_USES:
                return false;
            default:
                float probDiscard = (float) usesLeft / MAX_USES;
                float randomValue = generator.nextFloat()*(float) 1.01;
                if (randomValue <= probDiscard){
                    return true;
                }   break;
        }
        return true;
    }
    
    /**
     * Genera una dirección de movimiento aleatoria para los FuzzyPlayer en
     * el juego, eliminando el determinismo original de éstos.
     * 
     * @param preference Dirección preferente por el jugador.
     * @param validMoves Lista de movimientos válidos para el jugador.
     * @param intelligence Inteligencia del jugador.
     * @return La dirección de movimiento generada.
     */
    
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
