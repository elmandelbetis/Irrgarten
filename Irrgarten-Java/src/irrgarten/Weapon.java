/**
 * @file irrgarten/Weapon.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Clase Arma, heredera de la clase CombatElement.
 * Se encarga de crear y gestionar todo lo relacionado con las armas de los
 * jugadores en el juego.
 */

public class Weapon extends CombatElement{
    
    //*****************************************************************
    // Constructor 
    //*****************************************************************
    
    /**
     * Constructor que toma las funciones del superconstructor de la clase padre
     * para crear el item designado.
     * @param power Poder de ataque del arma
     * @param uses Usos del arma
     */
    
    public Weapon(float power, int uses){
        super(power, uses);
    }
    
    /**
     * Relega su funcionalidad en el método produceEffect() de la clase padre
     * @return Fuerza de ataque del arma
     */
    
    public float attack(){
        return super.produceEffect();          
    }
    
    /**
     * Método toString()
     * @return Estadísticas del item (toString() de la clase padre) + una W de WEAPON
     */
    
    @Override
    public String toString(){
        return " W"+super.toString();
    }
    
    /**
     * Método que calcula si se debe descartar o no un arma (clase padre) de 
     * forma aleatoria.
     * @return Valor booleano de descare (true o false)
     */
    
    @Override
    public boolean discard(){
        return super.discard();
    }
}
