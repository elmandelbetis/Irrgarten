///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Player.java    //
///////////////////////////////////////

package irrgarten;

import java.util.ArrayList;

public class Player {
    
    private static final int MAX_WEAPONS = 2, MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10, HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence, strength, health;
    private int row, col;
    private int consecutiveHits = 0;
    
    private ArrayList <Weapon> weapons; 
    private ArrayList <Shield> shields;

    
    // Constructores de la clase
    public Player()
    {
        this('\0', 0,0);
        this.name = null;
        this.health = 0;
    }
    
    public Player(char number, float intelligence, float strength){
        
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.name = "Player#"+number;
        this.health = INITIAL_HEALTH;
        
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        
        Weapon w = new Weapon(1,1);
        Shield s = new Shield(1,1);
        weapons.add(w);
        shields.add(s);
    }
    
    // Método resurrect()
    // Reinicia las listas de armas y escudos a 0, establece el nivel de salud
    // al valor dado como salud inicial, y resetea el contador de hits
    // consecutivos a 0.
    
    public void resurrect()
    {   
        weapons.clear();
        shields.clear();
        this.health = INITIAL_HEALTH;
        resetHits();
    } 
    
    // Método getRow()
    // Devuelve la fila en la que se encuentra el jugador
    
    public int getRow()
    {
        return this.row;
    }
    
    // Método getCol()
    // Devuelve la columna en la que se encuentra el jugador
    
    public int getCol()
    {
        return this.col;
    }
    
    // Método getNumber()
    // Devuelve el num-ID del jugador
    
    public char getNumber()
    {
        return this.number;
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
        
        return health <= 0;
    }
    
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves)
    {   
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if (size > 0 && (!contained)){
            return validMoves.get(0);
        }
        else{
            return direction;
        }
    }
    
    public boolean defend(float receivedAttack)
    {
        return manageHit(receivedAttack);
    }  
        
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i = 0; i < wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        
        for (int i = 0; i < sReward; i++){
            Shield snew = newShield();
            receiveShields(snew);   
        }
        
        int extraHealth = Dice.healthReward();
        health += extraHealth;
        
    }
    
    // Método toString()
    // Devuelve el estado actual detallado del jugador
    
    @Override
    public String toString()
    {
        return ""+name+",H: "+health+",I: "+intelligence+
                ",S: "+strength+",Pos: "+row+","+col;
    }
    
    // Método receiveWeapon()
    //
    
    public void receiveWeapon(Weapon w)
    {    
        for (int i = weapons.size()-1; i <= 0; i--){
            boolean discard = weapons.get(i).discard();
            if (discard)
                weapons.remove(weapons.get(i));
        }
        
        int size = weapons.size();
        if (size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    public void receiveShields(Shield s)
    {
        for(int i = shields.size()-1; i <= 0; i--){
            boolean discard = shields.get(i).discard();
            if(discard)
                shields.remove(shields.get(i));
        }
        
        int size = shields.size();
        if(size < MAX_SHIELDS)
            shields.add(s);
    }
    
    // Método newWeapon()
    // Llama a la clase Weapon, crea una nuueva instancia de arma, y la 
    // añade a la lista de armas que hemos creado anteriormente
    
    public Weapon newWeapon()
    {
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        
        Weapon newWeapon = new Weapon(Dice.weaponPower(),Dice.usesLeft());
        
        do{
            weapons.add(newWeapon);
        }while ((power < 1.0f) && (uses < 1));
        
        return newWeapon; // idea
        
    }
    
    // Método newShield()
    // Igual que el método newWeapon, pero llamando a la clase Shield
    
    public Shield newShield()
    {
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        
        Shield newShield = new Shield(protection, uses);

        do{
            shields.add(newShield);
        }while ((protection < 1.0f) && (uses < 1));
        
        return newShield; // idea
        
    }
    
    // Método sumWeapons()
    // Devuelve la suma total de todas las armas, llamando a sus respectivos
    // métodos attack()
    
    public float sumWeapons()   // idea
    {
        float sum = 0.0f;
        
        for (int i = 0; i < weapons.size(); i++){
            sum += weapons.get(i).attack();
        }
        
        return sum;
                
    }
    
    // Método sumShields()
    // Devuelve la suma total de todos los escudos, llamando a sus métodos
    // protect()
    
    public float sumShields()   // idea
    {
        float sum = 0.0f;
        
        for (int i = 0; i < shields.size(); i++){
            sum += shields.get(i).protect();
        }
        
        return sum;
    }
    
    // Método attack()
    // Calcula y devuelve la suma de la fuerza del jugador y lo aportado por
    // sus armas
    
    public float attack(){
        return this.strength + sumWeapons();
    }
    
    // Método defensiveEnergy()
    // Calcula y devuelve la suma de la inteligencia del jugador y lo
    // aportado por la protección de sus escudos
    
    public float defensiveEnergy()
    {
        return this.intelligence + sumShields();
    }
    
    public boolean manageHit(float receivedAttack)
    {
        float defense = defensiveEnergy();
        if (defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        }
        else {
            resetHits();
        }
        
        if (((consecutiveHits == HITS2LOSE) || (dead() ))) {
            resetHits();
            return true;
        }
        else {
            return false;
        }
    }
    
    // Método resetHits
    // Resetea el contador de hits consecutivos a cero
    
    public void resetHits(){
        this.consecutiveHits = 0;
    }
    
    // Método gotWounded()
    // Al igual que en la clase Monster, reduce en 1 unidad el contador de 
    // salud del jugador
    
    public void gotWounded(){
        this.health--;
    }  
    
    // Método incConsecutiveHits
    // Incrementa de uno en uno el contador de hits consecutivos del jugador
    
    public void incConsecutiveHits(){
        this.consecutiveHits++;
    }
    
    
}
