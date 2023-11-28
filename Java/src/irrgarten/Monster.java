///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/Monster.java   //
///////////////////////////////////////

package irrgarten;

public class Monster extends LabyrinthCharacter{
    
    private static final int INITIAL_HEALTH = 5;
   
    
    // Constructor de la clase
    public Monster(String name, float intelligence, float strength)
    {
        super("Monster "+name, intelligence, strength, INITIAL_HEALTH);
    }    
    
    // Método attack()
    // Devuelve el resultado delegado del método intensity de la clase Dice,
    // pasándole como parámetro la fuerza del monstruo
    
    @Override
    public float attack()
    {
        return Dice.intensity(super.getStrength());   
    }
    
    @Override
     public boolean defend(float receivedAttack)
    {
        boolean isDead = super.dead();
        
        if (!isDead){
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            
            if(defensiveEnergy < receivedAttack){
                super.gotWounded();
                isDead = super.dead();
            }
        }
        
        return isDead;
    }
    
}
