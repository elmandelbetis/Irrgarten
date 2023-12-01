///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Player.java    //
///////////////////////////////////////

package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    
    private static final int MAX_WEAPONS = 2, MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10, HITS2LOSE = 3;
    
    private char number;
    private int consecutiveHits = 0;
    
    private ArrayList <Weapon> weapons; 
    private ArrayList <Shield> shields;
    
    private ShieldCardDeck shieldCardDeck;
    private WeaponCardDeck weaponCardDeck;

    
    // Constructores de la clase
    
    public Player(char number, float intelligence, float strength){ 
        
        super("Player #"+number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
        
    }
    
    public Player(Player other){    //constructor de copia
        
        super(other);    
        this.number = other.number;
        this.weapons = other.weapons; 
        this.shields = other.shields;
        
        this.shieldCardDeck = other.shieldCardDeck;
        this.weaponCardDeck = other.weaponCardDeck;
    }
    
    // Método resurrect()
    // Reinicia las listas de armas y escudos a 0, establece el nivel de salud
    // al valor dado como salud inicial, y resetea el contador de hits
    // consecutivos a 0.
    
    public void resurrect()
    {   
        weapons.clear();    // reseteo de armas
        shields.clear();    // reseteo de escudos
        super.setHealth(INITIAL_HEALTH);
        resetHits();
    } 
    
    // Método getRow()
    // Devuelve la fila en la que se encuentra el jugador
    
    @Override
    public int getRow()
    {
        return super.getRow();
    }
    
    // Método getCol()
    // Devuelve la columna en la que se encuentra el jugador
    
    @Override
    public int getCol()
    {
        return super.getCol();
    }
    
    // Método getNumber()
    // Devuelve el num-ID del jugador
    
    public char getNumber()
    {
        return this.number;
    }
    
    // Método setPos
    // Modifica y establece la posición (row, col) del jugador
    
    @Override
    public void setPos(int row, int col)
    {
        super.setPos(row, col);
    }
    
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
    
    @Override
    public boolean defend(float receivedAttack)
    {
        return manageHit(receivedAttack);
    }  
        
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i = 0; i < wReward; i++){
            Weapon wnew = weaponCardDeck.nextCard();      //idea que NO funciona
            //Weapon wNew = newWeapon();
            this.receiveWeapon(wnew);
        }
        
        for (int i = 0; i < sReward; i++){
            Shield snew = shieldCardDeck.nextCard();      //idea que NO funciona
            //Shield sNew = newShield();
            receiveShields(snew);   
        }
        
        int extraHealth = Dice.healthReward();
        float playerHealth = getHealth(); 
        setHealth(playerHealth + extraHealth);
        
    }
    
    // Método toString()
    // Devuelve el estado actual detallado del jugador
    
    @Override
    public String toString()
    {
        System.out.println("=============================================");
        System.out.println("Armas del jugador #"+number+": "+weapons);
        System.out.println("Escudos del jugador #"+number+": "+shields+"\n");
        return super.toString();
        
    }
    
    // Método receiveWeapon()
    //
    
    public void receiveWeapon(Weapon w)
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
    
    public void receiveShields(Shield s)
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
    
    // Método newWeapon()
    // Llama a la clase Weapon, crea una nuueva instancia de arma, y la 
    // añade a la lista de armas que hemos creado anteriormente
    
    public Weapon newWeapon()
    {
        Weapon newWeapon = new Weapon(Dice.weaponPower(),Dice.usesLeft());
        weapons.add(newWeapon);
        return newWeapon;
    }
    
    // Método newShield()
    // Igual que el método newWeapon, pero llamando a la clase Shield
    
    public Shield newShield()
    {
        Shield newShield = new Shield(Dice.shieldPower(), Dice.usesLeft());
        shields.add(newShield);
        return newShield;
        
    }
    
    // Método sumWeapons()
    // Devuelve la suma total de todas las armas, llamando a sus respectivos
    // métodos attack()
    
    protected float sumWeapons()   // idea
    {
        float sum = 0f;
        
        for (int i = 0; i < weapons.size(); i++){
            sum += weapons.get(i).attack();
        }
        
        return sum;
                
    }
    
    // Método sumShields()
    // Devuelve la suma total de todos los escudos, llamando a sus métodos
    // protect()
    
    protected float sumShields() 
    {
        float sum = 0f;
        
        for (int i = 0; i < shields.size(); i++){
            sum += shields.get(i).protect();
        }
        
        return sum;
    }
    
    // Método attack()
    // Calcula y devuelve la suma de la fuerza del jugador y lo aportado por
    // sus armas
    
    @Override
    public float attack(){
        return super.getStrength() + sumWeapons();
    }
    
    // Método defensiveEnergy()
    // Calcula y devuelve la suma de la inteligencia del jugador y lo
    // aportado por la protección de sus escudos
    
    protected float defensiveEnergy()
    {
        return super.getIntelligence() + sumShields();
    }
    
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
    
    // Método resetHits
    // Resetea el contador de hits consecutivos a cero
    
    public void resetHits(){
        this.consecutiveHits = 0;
    }
    
    // Método incConsecutiveHits
    // Incrementa de uno en uno el contador de hits consecutivos del jugador
    
    public void incConsecutiveHits(){
        this.consecutiveHits++;
    }
    
    
}
