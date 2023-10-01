///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Weapon.java    //
///////////////////////////////////////

package irrgarten;

public class Weapon {
    
    private float power;
    private int uses;
    
    // Constructores
    
    public Weapon(){
        power = 0;
        uses = 0;
    }
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    //Consultores y modificadores (get y set)
    
    public int getUses(){
        return uses;
    }
    
    public float getPower(){
        return power;
    }
    
    public void setPower(float power){
        this.power = power;
    }
    
    public void setUses(int uses){
        this.uses = uses;
    }
    
    // Método "attack"
    // Devuelve el valor de "power" (poder de ataque) siempre y cuando el valor
    // de "uses" sea mayor que 0.
    
    public float attack(){
        
        if (uses > 0){
            uses--;
            return power;
        }
        else{
            return 0;
        }
            
    }
    
    // Método "to_string" de la clase Weapon, devuelve una cadena con el valor
    // de "power" y "uses" restantes del arma en sí
    
    @Override
    public String toString(){
        return "W["+power+", "+uses+"]";
    }
    
    // Llamada a la clase Dice, que determina en este caso si hay que descartar
    // el arma
    
    public boolean discard(){
        return Dice.discardElement(getUses());
    }
}
