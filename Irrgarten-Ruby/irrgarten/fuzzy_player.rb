#encoding:utf-8
require_relative 'dice'
require_relative 'player'

module Irrgarten

  class FuzzyPlayer < Player

	 def initialize(other)
		other_player(other)
	 end

	 def move(direction, valid_moves)
		super(Dice.next_step(direction, valid_moves, intelligence), valid_moves)
	 end

	 def attack
		sum_weapons + Dice.intensity(strength)
	 end

	 protected def defensive_energy
		sum_shields + Dice.intensity(intelligence)
	 end

	 def to_s
		"Fuzzy" << super
	 end

  end	#class

end	#module
