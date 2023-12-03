/**
 * @file irrgarten/FuzzyPlayer.java
 * @author Álvaro Maldonado Medina, grupo 2D-D3
 */

package irrgarten;

import java.util.ArrayList;

/**
 * Clase encargada de crear y gestionar los FuzzyPlayer, jugadores "fantasma"
 * que se generan tras morir un jugador normal en partida a causa de un monstruo.
 */

public class FuzzyPlayer extends Player{
    
    //*****************************************************************
    // Constructor
    //*****************************************************************
    
    /**
     * Constructor de la clase, que es a su vez el constructor copia
     * de la clase Player puesto que un FuzzyPlayer sólo puede generarse
     * a partir de un jugador ya existente en partida.
     * @param other El jugador que se ha muerto.
     */
    
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    /**
     * Método que se encarga de mover al FuzzyPlayer por el laberinto.
     * @param direction Dirección de movimiento deseada.
     * @param validMoves Lista de movimientos válidos para el jugador.
     * @return La dirección generada en función del valor generado en Dice.nextStep.
     */
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves)
    {
        super.move(direction, validMoves);
        return Dice.nextStep(direction, validMoves, super.getIntelligence());
    }
    
    /**
     * Método que gestiona los ataques del FuzzyPlayer.
     * @return suma de armas del jugador (clase padre Player) y una intensidad aleatoria.
     */
    
    @Override
    public float attack()
    {
        return (super.sumWeapons() + Dice.intensity(super.getStrength()) );
    }
    
    /**
     * Método que gestiona la energía defensiva del FuzzyPlayer.
     * @return suma de escudos del jugador (clase padre player) y una inteligencia aleatoria.
     */
    
    @Override
    protected float defensiveEnergy()
    {
        return (super.sumShields() + Dice.intensity(super.getIntelligence()) );
    }
    
    /**
     * Método toString
     * @return Cadena de caracteres con el estado actual y estadísticas del jugador
     */
    
    @Override
    public String toString()
    {
        return "Fuzzy"+super.toString();
    }
    
}
