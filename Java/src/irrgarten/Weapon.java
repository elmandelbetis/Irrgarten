///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Weapon.java    //
///////////////////////////////////////

package irrgarten;

public class Weapon extends CombatElement{
    
    private float power;
    private int uses;
    
    // Constructores
        
    public Weapon(float power, int uses){
        super(power, uses);
    }
    
    // Método "attack"
    // Devuelve el valor de "power" (poder de ataque) siempre y cuando el valor
    // de "uses" sea mayor que 0.
    
    public float attack(){
        return super.produceEffect();          
    }
    
    // Método "to_string" de la clase Weapon, devuelve una cadena con el valor
    // de "power" y "uses" restantes del arma en sí
    
    @Override
    public String toString(){
        return "W["+power+", "+uses;
    }
    
    // Llamada a la clase Dice, que determina en este caso si hay que descartar
    // el arma
    
    public boolean discard(){
        return super.discard();
    }
}
