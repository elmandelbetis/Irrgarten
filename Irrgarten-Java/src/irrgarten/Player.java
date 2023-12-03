/**
 * @file irrgarten/Player.java
 * @author Álvaro Maldonado Medina, grupo: 2D-D3
 */

package irrgarten;

import java.util.ArrayList;

/**
 * Clase Player: Representa a un jugador en el juego.
 * Esta clase maneja las características, acciones y objetos asociados 
 * a un jugador.
 * Heredera de la clase LabyrinthCharacter.
 */

public class Player extends LabyrinthCharacter{
    
    //*****************************************************************
    // Constantes 
    //*****************************************************************
    
    private static final int MAX_WEAPONS = 2;   // Máximo de armas permitidas
    private static final int MAX_SHIELDS = 3;   // Máximo de escudos permitidos
    private static final int INITIAL_HEALTH = 10;   // Salud inicial
    private static final int HITS2LOSE = 3; // Número de golpes recibidos para derrota
    
    //*****************************************************************
    // Atributos 
    //*****************************************************************
    
    private char number;
    private int consecutiveHits = 0;
    
    private ArrayList <Weapon> weapons; // Lista de armas del jugador
    private ArrayList <Shield> shields; // Lista de escudos del jugador
    
    private ShieldCardDeck shieldCardDeck;  // Cartas de escudo del jugador
    private WeaponCardDeck weaponCardDeck;  // Cartas de arma del jugador

    
    //*****************************************************************
    // Constructores 
    //*****************************************************************
    
    /**
     * Constructor del Player.
     * @param number Número del jugador.
     * @param intelligence Inteligencia defensiva del jugador.
     * @param strength  Fuerza del jugador.
     */
    
    public Player(char number, float intelligence, float strength){ 
        
        super("Player #"+number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
        
    }
    
    /**
     * Constructor de copia del Player.
     * @param other Jugador a ser copiado.
     */
    
    public Player(Player other){
        
        super(other);    
        this.number = other.number;
        this.weapons = other.weapons; 
        this.shields = other.shields;
        
        this.shieldCardDeck = other.shieldCardDeck;
        this.weaponCardDeck = other.weaponCardDeck;
    }
    
    /**
     * Método para resucitar al jugador.
     * Reinicia las listas de armas y escudos, establece la salud inicial y
     * resetea el contador de hits consecutivos.
     */
    
    public void resurrect()
    {   
        weapons.clear();    // reseteo de armas
        shields.clear();    // reseteo de escudos
        super.setHealth(INITIAL_HEALTH);
        resetHits();
    } 
    
    /**
     * Getter de la fila del jugador.
     * @return La fila en la que se encuentra el jugador.
     */
    
    @Override
    public int getRow()
    {
        return super.getRow();
    }
    
    /**
     * Getter de la columna del jugador.
     * @return La columna donde se encuentra el jugador.
     */
    
    @Override
    public int getCol()
    {
        return super.getCol();
    }
    
    /**
     * Getter del número del jugador.
     * @return El número del jugador actual.
     */
    
    public char getNumber()
    {
        return this.number;
    }
    
    /**
     * Setter de la posición del jugador en el laberinto.
     * @param row
     * @param col 
     */
    
    @Override
    public void setPos(int row, int col)
    {
        super.setPos(row, col);
    }
    
    /**
     * Método que devuelve la dirección de movimiento del jugador.
     * @param direction Dirección de movimiento "deseada"
     * @param validMoves Contenedor de direcciones de movimiento alternativas
     * @return La dirección deseada o la primera en el contenedor de las alternativas
     */
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves)
    {   
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if ((size > 0) && (!contained)){
            return validMoves.get(0);
        }
        else{
            return direction;
        }
    }
    
    /**
     * Método que relega su funcionalidad en el método manageHit() para 
     * defenderse de ataques.
     * @param receivedAttack Valor de ataque recibido en coma flotante.
     * @return manageHit()
     */
    
    @Override
    public boolean defend(float receivedAttack)
    {
        return manageHit(receivedAttack);
    }  
    
    /**
     * Método que se encarga de gestionar todo lo relacionado en cuanto a las
     * recompensas recibidas por el jugador cuando gana un combate contra un
     * Monster.
     */
    
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i = 0; i < wReward; i++){
            Weapon wnew = weaponCardDeck.nextCard();     
            this.receiveWeapon(wnew);
        }
        
        for (int i = 0; i < sReward; i++){
            Shield snew = shieldCardDeck.nextCard();
            receiveShields(snew);   
        }
        
        int extraHealth = Dice.healthReward();
        float playerHealth = getHealth(); 
        setHealth(playerHealth + extraHealth);
        
    }
    
    /**
     * Método toString()
     * @return estado actual, estadísticas, armas y escudos del jugador.
     */
    
    @Override
    public String toString()
    {
        System.out.println("=============================================");
        System.out.println("Armas del jugador #"+number+": "+weapons);
        System.out.println("Escudos del jugador #"+number+": "+shields+"\n");
        return super.toString();
        
    }
    
    /**
     * Método que se encarga de gestionar la recepción de armas como recompensa.
     * @param w Arma aleatoria a recibir por el jugador.
     */
    
    private void receiveWeapon(Weapon w)
    {    
        for (int i = weapons.size()-1; i >= 0; i--){
            boolean discard = weapons.get(i).discard();
            if (discard)
                weapons.remove(weapons.get(i));
        }
        
        int size = weapons.size();
        if (size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    /**
     * Método que se encarga de gestionar la recepción de escudos como recompensa.
     * @param s Escudo aleatorio a recibir por el jugador.
     */
    
    private void receiveShields(Shield s)
    {
        for(int i = shields.size()-1; i >= 0; i--){
            boolean discard = shields.get(i).discard();
            if(discard)
                shields.remove(shields.get(i));
        }
        
        int size = shields.size();
        if(size < MAX_SHIELDS)
            shields.add(s);
    }
    
 /*   Métodos no usados pero que figuran en el UML del proyecto
    
    private Weapon newWeapon()
    {
        Weapon newWeapon = new Weapon(Dice.weaponPower(),Dice.usesLeft());
        weapons.add(newWeapon);
        return newWeapon;
    }
    
    // Método newShield()
    // Igual que el método newWeapon, pero llamando a la clase Shield
    
    private Shield newShield()
    {
        Shield newShield = new Shield(Dice.shieldPower(), Dice.usesLeft());
        shields.add(newShield);
        return newShield;
        
    }
*/
    
    /**
     * Método que devuelve la suma de poder de ataque de todas las armas del
     * jugador.
     * @return La suma total de ataque.
     */
    
    protected float sumWeapons()   // idea
    {
        float sum = 0f;
        
        for (int i = 0; i < weapons.size(); i++){
            sum += weapons.get(i).attack();
        }
        
        return sum;
                
    }
    
    /**
     * Método que devuelve la suma de poder de protección de todos los escudos
     * del jugador.
     * @return La suma total de protección.
     */
    
    protected float sumShields() 
    {
        float sum = 0f;
        
        for (int i = 0; i < shields.size(); i++){
            sum += shields.get(i).protect();
        }
        
        return sum;
    }
    
    /**
     * Método que devuelve el poder total de ataque del jugador, suma de su
     * fuerza con la suma de todas sus armas disponibles en la lista.
     * @return Poder total de ataque del jugador.
     */
    
    @Override
    public float attack(){
        return super.getStrength() + sumWeapons();
    }
    
    /**
     * Método que devuelve el poder total de defensa del jugador, suma de su
     * inteligencia defensiva con la suma de todos sus escudos disponibles en
     * la lista.
     * @return Poder total de defensa del jugador.
     */
    
    protected float defensiveEnergy()
    {
        return super.getIntelligence() + sumShields();
    }
    
    /**
     * Método que gestiona la defensa del jugador a los ataques de un monstruo.
     * @param receivedAttack ataque recibido.
     * @return True si ha perdido el combate, false si lo ha ganado.
     */
    
    public boolean manageHit(float receivedAttack)
    {
        float defense = defensiveEnergy();
        if (defense < receivedAttack) {
            super.gotWounded();
            incConsecutiveHits();
        }
        else {
            resetHits();
        }
       
        if ((consecutiveHits == HITS2LOSE) || (super.dead() )) {
            resetHits();
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Método que resetea los hits consecutivos.
     */
    
    public void resetHits(){
        this.consecutiveHits = 0;
    }
    
    /**
     * Método que incrementa los hits consecutivos de uno en uno.
     */
    
    public void incConsecutiveHits(){
        this.consecutiveHits++;
    }
    
    
}
