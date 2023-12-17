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

	 # Generador aleatorio
	 @@generator = Random.new

	 # Calcula una posición aleatoria a partir de un valor máximo
	 def self.random_pos(max)
		@@generator.rand(max).to_i
	 end

	 # Determina quién va a empezar la partida
	 def self.who_starts(nplayers)
		@@generator.rand(nplayers).to_i
	 end

	 # Calcula un valor aleatorio de inteligencia para los mobs
	 def self.random_intelligence
		@@generator.rand(@@MAX_INTELLIGENCE)
	 end

	 # Calcula un valor aleatorio de fuerza para los mobs
	 def self.random_strength
		@@generator.rand(@@MAX_STRENGTH)
	 end

	 # Calcula de forma aleatoria cuándo resucitar un jugador
	 def self.resurrect_player
		@@generator.rand(0.to_f..1.to_f) <= @@RESURRECT_PROB
	 end

	 # Calcula un nº aleatorio de armas a recibir en función de un máximo dado
	 def self.weapons_reward
		@@generator.rand(@@WEAPONS_REWARD + 1)
	 end

	 # Calcula un nº aleatorio de escudos a recibir en función de un máximo dado
	 def self.shields_reward
		@@generator.rand(@@SHIELDS_REWARD + 1)
	 end

	 # Calcula un nº aleatorio de salud a recibir en función de un máximo dado
	 def self.health_reward
		@@generator.rand(@@HEALTH_REWARD + 1)
	 end

	 # Calcula un valor aleatorio de poder para las armas
	 def self.weapon_power
		@@generator.rand(@@MAX_ATTACK.to_f)
	 end

	 # Calcula un valor aleatorio de protección para los escudos
	 def self.shield_power
		@@generator.rand(@@MAX_SHIELD.to_f)
	 end

	 # Calcula un valor aleatorio de usos restantes (1..max) para los items
	 def self.uses_left
		@@generator.rand(@@MAX_USES + 1)
	 end

	 # Calcula una intensidad aleatoria de ataque
	 def self.intensity(competence)
		@@generator.rand(competence.to_f)
	 end

	 # Método aleatorio que se encarga de determinar el descarte de items
	 def self.discard_element(uses_left)
		false if uses_left == @@MAX_USES
		true if uses_left == 0

		prob = 1.0 / (uses_left + 1)
		random = @@generator.rand(uses_left + 1)

		random <= prob
	 end

	 # Método de movimiento aleatorio para los fuzzy_player en partida
	 def self.next_step(preference, valid_moves, intelligence)
		prob = @@generator.rand(1.0)

		if prob <= intelligence
		  return preference
		else
		  another_direction = @@generator.rand(valid_moves.size)
		  return valid_moves[another_direction]
		end

	 end

  end #class
end #module

