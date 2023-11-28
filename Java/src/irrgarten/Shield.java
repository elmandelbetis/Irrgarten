///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Shield.java    //
///////////////////////////////////////

package irrgarten;


public class Shield extends CombatElement{
    
    private float protection;
    private int uses;
    
    
    //Constructores 
    
    public Shield(float protection, int uses){
        super(protection, uses);
    }
    
    // Método "protect"
    // Devuelve el valor de protección de un escudo siempre y cuando el nº de
    // usos restantes sea mayor que 0
    
    public float protect(){
        return super.produceEffect();
    }
    
    // Método to_string, muestra una cadena con los valores de protección del
    // escudo y los usos restantes
    
    @Override
    public String toString(){
        return " S"+ super.toString();
    }
    
    // Llamada a la clase Dice y su método discardElement, que determina en 
    // este caso si hay que descartar el escudo
    
    public boolean discard(){
        return super.discard();
    }
}
