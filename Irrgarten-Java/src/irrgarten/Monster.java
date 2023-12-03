/**
 * @file irrgarten/LabyrinthCharacter.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Clase Monstruo. Se encarga de crear y gestionar todo lo relacionado con los 
 * monstruos en el juego.
 * Heredera de la clase abstracta LabyrinthCharacter.
 */

public class Monster extends LabyrinthCharacter{
    
    //******************************************************************
    // Constante
    //******************************************************************
    
    private static final int INITIAL_HEALTH = 5;
    
    //******************************************************************
    // Constructor
    //******************************************************************
    
    /**
     * Constructor de Monster
     * @param name Nombre del monstruo
     * @param intelligence Inteligencia del monstruo
     * @param strength Fuerza del monstruo
     */
    
    public Monster(String name, float intelligence, float strength)
    {
        super("Monster "+name, intelligence, strength, INITIAL_HEALTH);
    }    
    
    /**
     * Devuelve el valor de ataque de un monstruo en un combate delegando su
     * funcionalidad en el dado
     * @return Valor aleatorio de ataque generado en el dado
     */
    
    @Override
    public float attack()
    {
        return Dice.intensity(super.getStrength());   
    }
    
    /**
     * Método encargado de gestionar la defensa de un monstruo al ataque
     * propiciado por un jugador al entrar en combate.
     * 
     * @param receivedAttack Ataque recibido por el monstruo
     * @return Si el monstruo está o no muerto
     */
    
    @Override
     public boolean defend(float receivedAttack)
    {
        boolean isDead = super.dead();
        
        if (!isDead){
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            
            if(defensiveEnergy < receivedAttack){
                super.gotWounded();
                isDead = super.dead();
            }
        }
        
        return isDead;
    }
    
}
