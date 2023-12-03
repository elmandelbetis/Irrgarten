/**
 * @file irrgarten/Shield.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/**
 * Clase Escudo, heredera de la clase abstracta CombatElement.
 * Se encarga de crear y gestionar todo lo relacionado con los escudos
 * de los jugadores en el juego.
 */

public class Shield extends CombatElement{

    //*****************************************************************
    // Constructor 
    //*****************************************************************
    
    /**
     * Constructor de la clae hija, que llama al súper constructor de la clase
     * padre.
     * @param protection Valor de protección (effect) del escudo generado
     * @param uses Número de usos del escudo generado
     */
    
    public Shield(float protection, int uses){
        super(protection, uses);
    }
    
    /**
     * Depende del método produceEffect de la clase padre para generar su "effect",
     * que en este caso es proteger al jugador ante un ataque de un monstruo
     * @return La protección de daño
     */
    
    public float protect(){
        return super.produceEffect();
    }
    
    /**
     * Método toString
     * @return La misma cadena de caracteres de la clase padre con el valor de
     *         protección y usos, pero añadiendo una S de SHIELD antes
     */
    
    @Override
    public String toString(){
        return " S"+ super.toString();
    }
    
    /**
     * Llamada al método discard de la clase padre para determinar el descarte
     * de escudos de forma aleatoria en el juego
     * @return El valor booleano de descarte (true o false)
     */
    
    @Override
    public boolean discard(){
        return super.discard();
    }
}
