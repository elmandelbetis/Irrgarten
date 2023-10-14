package irrgarten;

public class Monster {
    
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    
    public Monster(String name, float intelligence, float strength)
    {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
    }
    
    public boolean dead(){
        
        boolean isDead = false;
        
        if (health <= 0){
            isDead = true;
        }
        
        return isDead;
    
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
        return "Monster: "+name+" ,H: "+health+" ,S: "+strength+""
                + " ,I: "+intelligence+" ,Pos: "+row+","+col;
    }
    
    public void gotWounded()
    {
        health--;
        System.out.println("Monster "+name+" got wounded, -1 HP");
    }
    
    public boolean defend(float receivedAttack)
    {
        throw new UnsupportedOperationException();
        // próxima práctica
    }
}
