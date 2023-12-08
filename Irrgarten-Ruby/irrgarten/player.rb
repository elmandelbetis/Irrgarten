#encoding:utf-8

module Irrgarten

  require_relative 'dice'
  require_relative 'shield'
  require_relative 'weapon'
  require_relative 'directions'

  class Player

	 @@MAX_WEAPONS = 2
	 @@MAX_SHIELDS = 3
	 @@INIIAL_HEALTH = 10
	 @@HITS2LOSE = 3

	 attr_reader :row, :col, :number

	 def initialize(number, intelligence, strength)
		@number = number
		@intelligence = intelligence.to_f
		@strength = strength.to_f

		#Inicialización del resto de atributos con valores por defecto
		@row = nil
		@col = nil
		@name = "Player##{@number}"
		@health = @@INITIAL_HEALTH
		@consecutive_hits = 0

		#Arrays de armas y escudos
		@weapons = []
		@shields = []

	 end


	 def resurrect
		@weapons.clear
		@shields.clear
		@health = @@INIIAL_HEALTH.to_f
		@consecutive_hits = 0
	 end

	 def row
		@row
	 end

	 def col
		@col
	 end

	 def number
		@number
	 end

	 def set_pos(row, col)
		@row = row
		@col = col
	 end

	 def dead
		@health == 0
	 end

	 def move(direction, valid_moves)
		####################
	 end

	 def attack
		sum_weapons + @strength
	 end

	 def defend(received_attack)
		manage_hit(received_attack)
	 end

	 def receive_reward
		####################
	 end

	 def to_s
		"#{@name}, S: #{@strength}, I: #{@intelligence}, H: #{@health}, Weapons: #{@weapons}, Shields: #{@shields}"
	 end

	 private
	 def receive_weapon(w)
		####################
	 end

	 def receive_shield(s)
		####################
	 end

	 def new_weapon
		Weapon.new(Dice.weapon_power, Dice.uses_left)
	 end

	 def new_shield
		Shield.new(Dice.shield_power, Dice.uses_left)
	 end

	 def sum_weapons
		sum = 0
		(0..@weapons.length).each { |i|
		  sum += @weapons[i].attack.to_f
		}
	 end

	 def sum_shields
		sum = 0
		(0..@shields.length).each { |i|
		  sum += @shields[i].protect.to_f
		}
	 end

	 def defensive_energy
		@intelligence + sum_shields
	 end

	 def manage_hit(received_attack)
		####################
	 end

	 def reset_hits
		@consecutive_hits = 0
	 end

	 def got_wounded
		@health -= 1
	 end

	 def inc_consecutive_hits
		@consecutive_hits += 1
	 end

  end #class

end	#module

