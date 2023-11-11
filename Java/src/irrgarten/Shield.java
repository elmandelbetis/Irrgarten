///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Shield.java    //
///////////////////////////////////////

package irrgarten;


public class Shield {
    
    private float protection;
    private int uses;
    
    
    //Constructores 
    
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    
    //Métodos de consulta y modificadores (get y set)
    
    
    public float getProtection(){
        return protection;
    }
    
    public int getUses(){
        return uses;
    }
    
    public void setProtection(float protection){
        this.protection = protection;
    }
    
    public void setUses(int uses){
        this.uses = uses;
    }
    
    
    // Método "protect"
    // Devuelve el valor de protección de un escudo siempre y cuando el nº de
    // usos restantes sea mayor que 0
    
    public float protect(){
        
        if (uses>0){
            uses--;
            return protection;
        }
        else{
            return 0;
        }
    }
    
    // Método to_string, muestra una cadena con los valores de protección del
    // escudo y los usos restantes
    
    @Override
    public String toString(){
        return "S["+protection+", "+uses+"]";
    }
    
    // Llamada a la clase Dice y su método discardElement, que determina en 
    // este caso si hay que descartar el escudo
    
    public boolean discard(){
        return Dice.discardElement(getUses());
    }
}
