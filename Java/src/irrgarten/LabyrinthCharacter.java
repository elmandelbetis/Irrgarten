
package irrgarten;

public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    public LabyrinthCharacter(String name, float intelligence, float strength, 
                                float health)
    {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = 0;
        this.col = 0;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other)
    {
        this(other.name, other.intelligence, other.strength, other.health);
        this.col = other.col;
        this.row = other.row;
    }
    
    public boolean dead()
    {
        return this.health == 0;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    protected float getIntelligence(){
        return this.intelligence;
    }
    
    protected float getStrength(){
        return this.strength;
    }
    
    protected float getHealth(){
        return this.health;
    }
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        return name+", H: "+health+", I: "+intelligence+
                ", S: "+strength+", Pos: ["+row+","+col+"] ";
    }
    
    protected void gotWounded(){
        this.health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);

}
