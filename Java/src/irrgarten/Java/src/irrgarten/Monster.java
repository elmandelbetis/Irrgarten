///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Monster.java   //
///////////////////////////////////////

package irrgarten;

public class Monster {
    
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    
    // Constructores de la clase
    
    public Monster()
    {
        this("",0,0);
    }
    
    public Monster(String name, float intelligence, float strength)
    {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
    }
    
    
    // Método dead()
    // Devuelve un valor booleano que será "true" o "false" en función de si
    // el monstruo está muerto o no
    
    public boolean dead(){
        
        boolean isDead = false;
        
        if (health <= 0){
            isDead = true;
        }
        
        return isDead;
    
    }
    
    // Método attack()
    // Devuelve el resultado delegado del método intensity de la clase Dice,
    // pasándole como parámetro la fuerza del monstruo
    
    public float attack()
    {
        return Dice.intensity(strength);   
    }
    
    // Método setPos()
    // Asigna una posición al monstruo en el tablero
    
    public void setPos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    // Método toString()
    // Genera el estado actual detallado del monstruo
    
    @Override
    public String toString()
    {
        return "Monster: "+name+" ,H: "+health+" ,S: "+strength+""
                + " ,I: "+intelligence+" ,Pos: "+row+","+col;
    }
    
    // Método gotWounded()
    // Reduce la salud del monstruo en 1
    
    public void gotWounded()
    {
        health--;
        System.out.println("Monster "+name+" got wounded, -1 HP");
    }
    
    
    // Método defend()
    //
    
    public boolean defend(float receivedAttack)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
}
