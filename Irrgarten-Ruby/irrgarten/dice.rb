#encoding:utf-8

module Irrgarten
  class Dice

	 @@MAX_USES = 5
	 @@MAX_INTELLIGENCE = 10.to_f
	 @@MAX_STRENGTH = 10.to_f
	 @@RESURRECT_PROB = 0.3.to_f
	 @@WEAPONS_REWARD = 2
	 @@SHIELDS_REWARD = 3
	 @@HEALTH_REWARD = 5
	 @@MAX_ATTACK = 3
	 @@MAX_SHIELD = 2

	 @@generator = Random.new

	 def self.random_pos(max)
		@@generator.rand(max).to_i
	 end

	 def self.who_starts(nplayers)
		@@generator.rand(nplayers).to_i
	 end

	 def self.random_intelligence
		@@generator.rand(@@MAX_INTELLIGENCE)
	 end

	 def self.random_strength
		@@generator.rand(@@MAX_STRENGTH)
	 end

	 def self.resurrect_player
		true if @@generator.rand(0.to_f..1.to_f) <= @@RESURRECT_PROB
	 end

	 def self.weapons_reward
		@@generator.rand(@@WEAPONS_REWARD + 1)
	 end

	 def self.shields_reward
		@@generator.rand(@@SHIELDS_REWARD + 1)
	 end

	 def self.health_reward
		@@generator.rand(@@HEALTH_REWARD + 1)
	 end

	 def self.weapon_power
		@@generator.rand(@@MAX_ATTACK.to_f)
	 end

	 def self.shield_power
		@@generator.rand(@@MAX_SHIELD.to_f)
	 end

	 def self.uses_left
		@@generator.rand(@@MAX_USES + 1)
	 end

	 def self.intensity(competence)
		@@generator.rand(competence.to_f)
	 end

	 def self.discard_element(uses_left)
		return false if uses_left == @@MAX_USES
		return true if uses_left == 0

		prob = 1.0 / (uses_left + 1)
		random = @@generator.rand(uses_left + 1)

		random <= prob
	 end

  end #class
end #module

