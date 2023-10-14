///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Player.java    //
///////////////////////////////////////

package irrgarten;

import java.util.ArrayList;

public class Player {
    
    private static final int MAX_WEAPONS = 2, MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10, HITS2LOSE = 3; // aún no usados
    
    private final String name;
    private final char number;
    private final float intelligence;
    private final float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    private final ArrayList<Weapon> weapons; //idea para implementar los métodos Sum
    private final ArrayList<Shield> shields; //mediante ArrayLists (duda pa clase)

    
    // Constructor de la clase, incluyendo inicialización de los ArrayList
    // Para las armas y los escudos pasando los límites de éstos en el
    // inventario como parámetro
    
    public Player(char number, float intelligence, float strength){
        
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.name = "Player#"+number;
        this.weapons = new ArrayList<>(MAX_WEAPONS); // idea
        this.shields = new ArrayList<>(MAX_SHIELDS); // idea
        
    }
    
    // Método resurrect()
    // Reinicia las listas de armas y escudos a 0, establece el nivel de salud
    // al valor dado como salud inicial, y resetea el contador de hits
    // consecutivos a 0.
    
    public void resurrect()
    {
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        resetHits();
    } 
    
    // Método getRow()
    // Devuelve la fila en la que se encuentra el jugador
    
    public int getRow()
    {
        return row;
    }
    
    // Método getCol()
    // Devuelve la columna en la que se encuentra el jugador
    
    public int getCol()
    {
        return col;
    }
    
    // Método getNumber()
    // Devuelve el num-ID del jugador
    
    public char getNumber()
    {
        return number;
    }
    
    // Método setPos
    // Modifica y establece la posición (row, col) del jugador
    
    public void setPos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    // Método dead()
    // Booleano que devuelve "true" si el nivel de salud es 0 o menos (el
    // jugador está muerto) o si por el contrario tiene algo de salud y sigue 
    // vivo
    
    public boolean dead(){
        
        boolean isDead = false;
        
        if (health <= 0){
            isDead = true;
        } 
        
        return isDead;
    }
    
    // Método move()
    // 
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves)
    {
        throw new UnsupportedOperationException();
    }
    // próximas prácticas

    
    public boolean defend(float receivedAttack)
    {
        throw new UnsupportedOperationException();
    }   // próxima práctica
        
    public void receiveReward(){}
    //próxima práctica
    
    // Método toString()
    // Devuelve el estado actual detallado del jugador
    
    @Override
    public String toString()
    {
        return ""+name+" ,Health: "+health+" ,Intelligence: "+health+
                " ,Strength: "+strength+intelligence+" ,Position: "+row+","+col;
    }
    
    // Método receiveWeapon()
    //
    
    public void receiveWeapon(Weapon s)
    {    
        // próxima práctica
    }
    
    public void receiveShields(Shield s)
    {
        // próxima práctica
    }
    
    // Método newWeapon()
    // Llama a la clase Weapon, crea una nuueva instancia de arma, y la 
    // añade a la lista de armas que hemos creado anteriormente
    
    public Weapon newWeapon()
    {
        
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft(); 
        Weapon newWeapon = new Weapon(power,uses);
        weapons.add(newWeapon); //idea
        
        System.out.println(newWeapon.toString());
        return newWeapon; // idea
        
    }
    
    // Método newShield()
    // Igual que el método newWeapon, pero llamando a la clase Shield
    
    public Shield newShield()
    {
        
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        Shield newShield = new Shield(protection,uses);
        
        shields.add(newShield); // idea
        
        System.out.println(newShield.toString());
        return newShield; // idea
        
    }
    
    // Método sumWeapons()
    // Devuelve la suma total de todas las armas, llamando a sus respectivos
    // métodos attack()
    
    public float sumWeapons()   // idea
    {
        float sum = (float) 0;
        
        for (Weapon weapon : weapons){
            sum += weapon.attack();
        }
        
        return sum;
                
    }
    
    // Método sumShields()
    // Devuelve la suma total de todos los escudos, llamando a sus métodos
    // protect()
    
    public float sumShields()   // idea
    {
        float sum = (float) 0;
        
        for (Shield shield : shields){
            sum += shield.protect();
        }
        
        return sum;
    }
    
    // Método attack()
    // Calcula y devuelve la suma de la fuerza del jugador y lo aportado por
    // sus armas
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    // Método defensiveEnergy()
    // Calcula y devuelve la suma de la inteligencia del jugador y lo
    // aportado por la protección de sus escudos
    
    public float defensiveEnergy()
    {
        return intelligence + sumShields();
    }
    
    public boolean manageHit(float receivedAttack)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
    
    // Método resetHits
    // Resetea el contador de hits consecutivos a cero
    
    public void resetHits(){
        consecutiveHits = 0;
    }
    
    // Método gotWounded()
    // Al igual que en la clase Monster, reduce en 1 unidad el contador de 
    // salud del jugador
    
    public void gotWounded(){
        health--;
        System.out.println(""+name+" got wounded, -1 HP");
    }  
    
    // Método incConsecutiveHits
    // Incrementa de uno en uno el contador de hits consecutivos del jugador
    
    public void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    
}
