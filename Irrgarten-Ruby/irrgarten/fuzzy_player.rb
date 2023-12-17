#encoding:utf-8
require_relative 'dice'
require_relative 'player'

module Irrgarten

  class FuzzyPlayer < Player

	 # Constructor que crea el fuzzy_player a partir de un jugador existente (copia)
	 def initialize(other)
		other_player(other)
	 end

	 # Mueve al fuzzy_player a partir de un conjunto de direcciones de movimiento válidas y un dado aleatorio
	 def move(direction, valid_moves)
		super(Dice.next_step(direction, valid_moves, intelligence), valid_moves)
	 end

	 # Gestiona el ataque del fuzzy_player a partir del dado y un método de la clase padre
	 def attack
		sum_weapons + Dice.intensity(strength)
	 end

	 #Gestiona la defensa del fuzzy_player a partir del dado y un método de la clase padre
	 protected def defensive_energy
		sum_shields + Dice.intensity(intelligence)
	 end

	 # To_String
	 def to_s
		"Fuzzy" << super
	 end

  end	#class

end	#module
