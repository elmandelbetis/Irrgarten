package irrgarten;

import java.util.ArrayList;
import java.util.List;

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
    
    private final List<Weapon> weapons; //idea para implementar los métodos Sum
    private final List<Shield> shields; //mediante ArrayLists (duda pa clase)

    
   
    public Player(char number, float intelligence, float strength){
        
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.name = "Player#"+number;
        this.weapons = new ArrayList<>(MAX_WEAPONS); // idea
        this.shields = new ArrayList<>(MAX_SHIELDS); // idea
        
    }
    
    public void resurrect(){} // ArrayList????????????
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public char getNumber()
    {
        return number;
    }
    
    public void setPos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public boolean dead(){
        
        boolean isDead = false;
        
        if (health <= 0){
            isDead = true;
        } 
        
        return isDead;
    }
    
    public Directions move(Directions direction, Directions[] validMoves)
    {
        throw new UnsupportedOperationException();
    }
    // próximas prácticas

    
    public boolean defend(float receivedAttack)
    {
        throw new UnsupportedOperationException();
    }
        
    public void receiveReward(){}
    
    @Override
    public String toString()
    {
        return ""+name+" ,Health: "+health+" ,Intelligence: "+health+
                " ,Strength: "+strength+intelligence+" ,Position: "+row+","+col;
    }
    
    public void receiveWeapon(Weapon s)
    {    
        // próxima práctica
    }
    
    public void receiveShields(Shield s)
    {
        // próxima práctica
    }
    
    public Weapon newWeapon()
    {
        
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft(); 
        Weapon newWeapon = new Weapon(power,uses);
        weapons.add(newWeapon); //idea
        
        System.out.println(newWeapon.toString());
        return newWeapon; // idea
        
    }
    
    public Shield newShield()
    {
        
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        Shield newShield = new Shield(protection,uses);
        
        shields.add(newShield); // idea
        
        System.out.println(newShield.toString());
        return newShield; // idea
        
    }
     
    public float sumWeapons()   // idea
    {
        float sum = (float) 0;
        
        for (Weapon weapon : weapons){
            sum += weapon.attack();
        }
        
        return sum;
                
    }
    
    public float sumShields()   // idea
    {
        float sum = (float) 0;
        
        for (Shield shield : shields){
            sum += shield.protect();
        }
        
        return sum;
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public float defensiveEnergy()
    {
        return intelligence + sumShields();
    }
    
    public boolean manageHit(float receivedAttack)
    {
        throw new UnsupportedOperationException();
    }
    
    public void resetHits(){
        consecutiveHits = 0;
    }
    
    public void gotWounded(){
        health--;
        System.out.println(""+name+" got wounded, -1 HP");
    }  
    
    public void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    
}
