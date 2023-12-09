#encoding:utf-8

module Irrgarten

  require_relative 'dice'
  require_relative 'shield'
  require_relative 'weapon'
  require_relative 'directions'

  class Player

	 @@MAX_WEAPONS = 2
	 @@MAX_SHIELDS = 3
	 @@INITIAL_HEALTH = 10
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
		@health = @@INITIAL_HEALTH.to_f
		@consecutive_hits = 0
	 end

	 def row
		@row
	 end

	 def col
		@col
	 end

	 def get_number
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
		size = valid_moves.size
		contained = valid_moves.include?(direction)

		if size > 0 && !contained
		  return valid_moves[0]
		else
		  return direction
		end

	 end

	 def attack
		sum_weapons + @strength
	 end

	 def defend(received_attack)
		manage_hit(received_attack)
	 end

	 def receive_reward
		w_reward = Dice.weapons_reward
		s_reward = Dice.shields_reward

		(0..w_reward).each do |i|
		  wnew = new_weapon
		  receive_weapon(wnew)
		end

		(0..s_reward).each do |i|
		  snew = new_shield
		  receive_shield(snew)
		end

		extra_health = Dice.health_reward
		@health += extra_health

	 end

	 def to_s
		"#{@name}, S: #{@strength}, I: #{@intelligence}, H: #{@health}, Weapons: #{@weapons}, Shields: #{@shields}"
	 end

	 private
	 def receive_weapon(w)
		@weapons.each do |i|
		  discard = i.discard
		  if discard
			 @weapons.delete(i)
		  end
		end

		size = @weapons.size
		if size < @@MAX_WEAPONS
		  @weapons << w
		end

	 end


	 def receive_shield(s)
		@shields.each do |i|
		  discard = i.discard
		  if discard
			 @shields.delete(i)
		  end
		end

		size = @shields.size
		if size < @@MAX_SHIELDS
		  @shields << s
		end

	 end

	 def new_weapon
		Weapon.new(Dice.weapon_power, Dice.uses_left)
	 end

	 def new_shield
		Shield.new(Dice.shield_power, Dice.uses_left)
	 end

	 def sum_weapons
		sum = 0.0
		@weapons.each do |x|
		  sum += x.attack
		end

		sum
	 end

	 def sum_shields
		sum = 0.0
		@shields.each do |x|
		  sum += x.protected
		end

		sum
	 end

	 def defensive_energy
		@intelligence + sum_shields
	 end

	 def manage_hit(received_attack)
		defense = defensive_energy
		if defense < received_attack
		  got_wounded
		  inc_consecutive_hits
		else
		  reset_hits
		end

		if @consecutive_hits == @@HITS2LOSE || dead
		  reset_hits
		  return true
		else
		  return false
		end

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

