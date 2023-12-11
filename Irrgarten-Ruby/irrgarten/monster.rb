#encoding:utf-8

require_relative 'labyrinth_character'

module Irrgarten

  class Monster < LabyrinthCharacter

	 @@INITIAL_HEALTH = 5

	 def initialize(name, intelligence, strength)
		super("Monster"+name, intelligence.to_f, strength.to_f, @@INITIAL_HEALTH)
	 end

	 def attack
		Dice.intensity(strength)
	 end

	 def defend(received_attack)

		is_dead = dead

		unless is_dead

		  defensive_energy = Dice.intensity(@intelligence)

		  if defensive_energy < received_attack
			 got_wounded
			 is_dead = dead
		  end

		end

		is_dead

		end

  end	#class
end	#module

