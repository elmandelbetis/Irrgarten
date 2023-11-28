///////////////////////////////////////
// Álvaro Maldonado Medina     	     //
// 2D - D3                           //
// Fichero: irrgarten/TestP1.java    //
///////////////////////////////////////


package irrgarten;

import irrgarten.Directions;
import irrgarten.GameCharacter;
import irrgarten.Orientation;
import java.util.Scanner;


public class TestP1 {
    
    public static void main(String[] args) {
        
        
        Dice dado = new Dice();
        int ITERACIONES = 100;
        String salto_linea = "\n";

        // PRUEBA DE LA CLASE WEAPON

        System.out.println("PRUEBA CLASE WEAPON");
        Weapon arma = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        System.out.println(arma.toString());
        arma.attack();
        System.out.println("Ataque realizado");
        System.out.println(arma.toString());
        arma.discard();
        System.out.println(arma.toString());
        
        // PRUEBA CLASE SHIELD
        
        System.out.println("PRUEBA CLASE SHIELD");
        Shield escudo = new Shield(Dice.shieldPower(),Dice.usesLeft());
        System.out.println(escudo.toString()); 
        System.out.println("Escudo realizado");
        escudo.protect();
        System.out.println(escudo.toString());
        
        //Prueba de los tipos Enumerados
        
        Enum orientacion = Orientation.VERTICAL;
        System.out.println("El arma se encuentra en una orientación " +orientacion);
        
        //PRUEBA CLASE DICE
        
        System.out.println(salto_linea);
        System.out.println("MÉTODO randomPos");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.randomPos(10));
        }
        
        System.out.println(salto_linea);
        System.out.println("MÉTODO whoStarts");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.whoStarts(10));
        }
        
        System.out.println(salto_linea);
        System.out.println("Método randomIntelligence");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.randomIntelligence());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método randomStrength");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.randomStrength());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método resurrectPlayer");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.resurrectPlayer());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método weaponsReward");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.weaponsReward());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método shieldsReward");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.shieldsReward());
        }

        System.out.println(salto_linea);
        System.out.println("Método healthReward");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.healthReward());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método weaponPower");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.weaponPower());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método shieldPower");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.shieldPower());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método usesLeft");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.usesLeft());
        }
        
        System.out.println(salto_linea);
        System.out.println("Método intensity");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.intensity(10));
        }
        
        System.out.println(salto_linea);
        System.out.println("Método discardElement");
        
        for(int i = 0; i < ITERACIONES; i++){
            System.out.println(dado.discardElement(dado.usesLeft()));
        }
        
        System.out.println(salto_linea);


    }

}
