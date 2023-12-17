#encoding:utf-8

=begin

La clase Player es una subclase de LabyrinthCharacter dentro del módulo Irrgarten.
Representa a un jugador dentro de un laberinto y tiene atributos y métodos para administrar
sus características, movimientos, ataques, defensa y gestión de objetos como armas y escudos.

=end

require_relative 'dice'
require_relative 'shield'
require_relative 'weapon'
require_relative 'directions'
require_relative 'labyrinth_character'

module Irrgarten

  class Player < LabyrinthCharacter

	 @@MAX_WEAPONS = 2
	 @@MAX_SHIELDS = 3
	 @@INITIAL_HEALTH = 10
	 @@HITS2LOSE = 3

	 attr_reader :consecutive_hits, :number, :weapons, :shields

	 # Constructor
	 def initialize(number, intelligence, strength)
		super("Player ##{number}", intelligence.to_f, strength.to_f, @@INITIAL_HEALTH)

		#Inicialización del resto de atributos con valores por defecto
		@number = number	# Número identificador del jugador
		@row = 0	#Número de golpes consecutivos recibidos
		@col = 0
		@consecutive_hits = 0

		#Arrays de armas y escudos
		@weapons = []
		@shields = []

	 end

	 #Constructor de copia
	 def other_player(other)
		other_lab_character(other)
		@number = other.number
		@consecutive_hits = 0

		@shields = other.shields
		@weapons = other.weapons
	 end

	 # Reinicia los atributos y contenedores de un jugador al resucitar
	 def resurrect
		@weapons.clear
		@shields.clear
		@health = @@INITIAL_HEALTH.to_f
		@consecutive_hits = 0
	 end

	 # Fila del jugador
	 def row
		super
	 end

	 # Columna del jugador
	 def col
		super
	 end

	 # Obtiene el número del jugador
	 def get_number
		@number
	 end

	 # Establece la posición del jugador
	 def set_pos(row, col)
		super
	 end

	 # Indica si el jugador está muerto
	 def dead
		super
	 end

	 # Mueve al jugador en una dirección válida
	 def move(direction, valid_moves)
		size = valid_moves.size
		contained = valid_moves.include?(direction)

		if size > 0 && !contained
		  return valid_moves[0]
		else
		  return direction
		end

	 end

	 # Calcula el poder de ataque del jugador
	 def attack
		sum_weapons + @strength
	 end

	 # Calcula el poder defensivo del jugador
	 def defend(received_attack)
		manage_hit(received_attack)
	 end

	 # Otorga recompensas al jugador como armas, escudos y salud extra
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

	 # ToString
	 def to_s
		"Player" << super << "\nArmas: #{@weapons.to_s}\nEscudos: #{@shields.to_s}"
	 end

	 # Nombre del jugador
	 def name
		@name
	 end

	 private

	 # Método encargado de gestionar la recepción de recompensas de tipo Weapon
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

	 # Método encargado de gestionar la recepción de recompensas de tipo Escudo
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

	 # Crear un nuevo arma
	 def new_weapon
		Weapon.new(Dice.weapon_power, Dice.uses_left)
	 end

	 # Crear un nuevo escudo
	 def new_shield
		Shield.new(Dice.shield_power, Dice.uses_left)
	 end

	 # Calcula la suma de ataque de todas las armas de jugador
	 def sum_weapons
		sum = 0.0
		@weapons.each do |x|
		  sum += x.attack
		end

		sum
	 end

	 # Calcula la suma de protección de los escudos del jugador
	 def sum_shields
		sum = 0.0
		@shields.each do |x|
		  sum += x.protect
		end

		sum
	 end

	 # Calcula la energía defensiva del jugador
	 def defensive_energy
		@intelligence + sum_shields
	 end

	 # Gestiona la recepción de golpes por parte de un monstruo
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

	 # Resetea el contador de hits a cero
	 def reset_hits
		@consecutive_hits = 0
	 end

	 # Reduce la salud del jugador en 1 unidad
	 def got_wounded
		super
	 end

	 # Incrementa el contador de hits consecutivos en 1
	 def inc_consecutive_hits
		@consecutive_hits += 1
	 end

  end #class

end	#module

