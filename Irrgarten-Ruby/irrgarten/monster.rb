#encoding:utf-8
module Irrgarten
  class Monster

	 @@INITIAL_HEALTH = 5

	 def initialize(name, intelligence, strength)
		@name = name
		@intelligence = intelligence.to_f
		@strength = strength.to_f
		@health = @@INITIAL_HEALTH
	 end

	 def dead
		@health == 0
	 end

	 def attack
		Dice.intensity(@strength)
	 end

	 def defend(received_attack)
		####################
	 end

	 def set_pos(row, col)
		@row = row
		@col = col
	 end

	 def to_s
		"#{@name}: [S: #{@strength}, H: #{@health}"
	 end

	 def got_wounded
		@health-=1
	 end

  end	#class
end	#module

