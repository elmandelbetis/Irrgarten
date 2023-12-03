/**
 * @file irrgarten/CombatElement
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Clase abstracta que se encarga de gestionar por igual a las clases hijas
 * englobando sus métodos, en este caso las armas Weapon y los escudos Shield
 */

public abstract class CombatElement {

    //*****************************************************************
    // Atributos
    //*****************************************************************
    
    private float effect;  
    private int uses;   
    
    //*****************************************************************
    // Constructor
    //*****************************************************************
    
    /**
     * Constructor del item
     * @param effect Efecto del item
     * @param uses Usos del item
     */
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    /**
     * Reduce el número de usos y devuelve el efecto generado por el item
     * @return El efecto o un cero en función del nº de usos restantes
     */
    
    protected float produceEffect(){
        if (uses > 0){
            uses--;
            return effect;
        } else {
            return 0;
        }
    }
    
    /**
     * Relega su funcionalidad en el método discardElement() de la clase Dice.
     * @return el valor booleano generado en dicho mmétodo de la otra clase
     */
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    /**
     * Método toString()
     * @return El estado actual del item (efecto y usos restantes) en String
     */
    
    @Override
    public String toString(){
        return "["+effect+", "+uses+"]";
    }
}
