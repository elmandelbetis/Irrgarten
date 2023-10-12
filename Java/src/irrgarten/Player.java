package irrgarten;

import irrgarten.Directions;
import irrgarten.GameCharacter;
import irrgarten.Orientation;

public class Player {
    
    private static final int MAX_WEAPONS = 2, MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10, HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
   
    public Player(char number, float intelligence, float strength){
        
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        
    }
    
    public void resurrect(){}
    
    public int getRow(){}
    
    public int getCol(){}
    
    public char getNumber(){}
    
    public void setPos(int row, int col){}
    
    public boolean dead(){}
    
    public Directions move(Directions direction, Directions[] validMoves){}
    
    public float attack(){}
    
    public boolean defend(float receivedAttack){}
    
    public void receiveReward(){}
    
    public String toString(){}
    
    public void receiveWeapon(){}
    
    public void receiveShields(){}
    
    public Weapon newWeapon(){}
    
    public Shield newShield(){}
    
    public float sumWeapons(){}
    
    public float sumShields(){}
    
    public float defensiveEnergy(){}
    
    public boolean manageHit(float receivedAttack){}
    
    public void resetHits(){}
    
    public void gotWounded(){}  
    
    public void incConsecutiveHits(){}
    
    
}
