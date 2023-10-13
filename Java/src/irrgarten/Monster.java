package irrgarten;
import irrgarten.Dice;

public class Monster {
    
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    Dice dado = new Dice();
    
    public Monster(String name, float intelligence, float strength)
    {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
    }
    
    public boolean dead(){
        
        boolean dead = false;
        
        if (health <= 0){
            dead = true;
        }
        
        return dead;
    
    }
    
    public float attack()
    {
        return Dice.intensity(strength);   
    }
    
    public void setPos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString()
    {
        return "Name: "+name+" ,Health: "+health+" ,Strength: "+strength+""
                + " ,Intelligence: "+intelligence+" ,Position: "+row+","+col;
    }
    
    public void gotWounded()
    {
        health--;
        System.out.println("Monster "+name+" got wounded, -1 HP");
    }
    
    public boolean defend(float receivedAttack){
        return false; 
        // PROVISIONAL
        
    }
}
