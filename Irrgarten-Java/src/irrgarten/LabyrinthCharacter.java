/**
 * @file irrgarten/LabyrinthCharacter.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

/** 
 * Clase abstracta que se encarga de gestionar los métodos más básicos de
 * jugadores y monstruos.
 */

  
public abstract class LabyrinthCharacter {
    
    //*****************************************************************
    // Atributos 
    //*****************************************************************
    
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    //*****************************************************************
    // Constructores 
    //*****************************************************************
     
    /**
     * Constructor principal de un LabyrinthCharacter.
     * @param name Nombre del mob o del jugador/monstruo.
     * @param intelligence Inteligencia del mob.
     * @param strength Fuerza del mob.
     * @param health Salud del mob.
     */
    
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
    
    /**
     * Constructor de copia
     * @param other Mob a copiar
     */
    
    public LabyrinthCharacter(LabyrinthCharacter other)
    {
        this(other.name, other.intelligence, other.strength, other.health);
        this.col = other.col;
        this.row = other.row;
    }
    
    /**
     * Método que devuelve si el mob está muerto o no.
     * @return true si salud=0 y false si no.
     */
    
    public boolean dead()
    {
        return this.health == 0;
    }
    
    /**
     * Getter de la fila donde se encuentra el mob
     * @return La fila
     */
    
    public int getRow(){
        return this.row;
    }
    
    /**
     * Getter de la columna donde se encuentra el mob
     * @return La columna
     */
    
    public int getCol(){
        return this.col;
    }
    
    /**
     * Getter de la intelifencia del mob
     * @return La inteligencia
     */
    
    protected float getIntelligence(){
        return this.intelligence;
    }
    
    /**
     * Getter de la fuerza del mob
     * @return La fuerza
     */
    
    protected float getStrength(){
        return this.strength;
    }
    
    /**
     * Getter de la salud del mob
     * @return La salud
     */
    
    protected float getHealth(){
        return this.health;
    }
    
    /**
     * Setter de la salud del mob
     * @param health valor de salud a establecer en el atributo de la clase
     */
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    /**
     * Setter de la posición del mob en el laberinto bidimensional
     * @param row Fila 
     * @param col Columna
     */
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    /**
     * Método toString()
     * @return Cadena de caracteres con el estado actual y estadísticas del mob
     */
    
    @Override
    public String toString(){
        return name+", H: "+health+", I: "+intelligence+
                ", S: "+strength+", Pos: ["+row+","+col+"] ";
    }
    
    /**
     * Método reductor de salud para los combates
     */
    
    protected void gotWounded(){
        this.health--;
    }
    
    /**
     * Método abstracto que será sobreescrito por monstruos y jugadores, con
     * la finalidad de atacar
     */
    
    public abstract float attack();
    
    /**
     * Método abstracto que será sobreescrito por monstruos y jugadores,
     * con la finalidad de defenderse
     * @param attack Fuerza de ataque recibida
     */
    
    public abstract boolean defend(float attack);

}
