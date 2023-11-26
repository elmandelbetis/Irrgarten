
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
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other)
    {
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
    }
    
    public boolean dead()
    {
        return health <= 0;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
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
        return name+",H: "+health+",I: "+intelligence+
                ",S: "+strength+",Pos: ["+row+","+col+"] ";
    }
    
    protected void gotWounded(){
        this.health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);

}
