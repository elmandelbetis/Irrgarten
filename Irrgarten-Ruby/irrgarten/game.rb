#encoding:utf-8

module Irrgarten

  require_relative 'player'
  require_relative 'game_state'
  require_relative 'game_character'
  require_relative 'directions'
  require_relative 'orientation'
  require_relative 'labyrinth'
  require_relative 'dice'
  require_relative 'monster'

  class Game

	 attr_reader :current_player_index, :log, :current_player, :labyrinth

	 @@MAX_ROUNDS = 10

	 ## TODO ##

	 def initialize(nplayers)
		@players = []
		@monsters = []
		@log = ""

		(0..nplayers).each { |i|
			@players << Player.new(i.chr, Dice.random_intelligence.round(3), Dice.random_strength.round(3))
		}

		@current_player_index = Dice.who_starts(nplayers)
		@current_player = @players[@current_player_index]

		@labyrinth = Labyrinth.new(5,5,2,0)
		configure_labyrinth
		@labyrinth.spread_players(@players)

	 end

	 def finished
		@labyrinth.have_a_winner
	 end

	 def next_step(preferred_direction)
		####################
	 end

	 def get_game_state
		game_state = GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @current_player_index, finished, log)
	 end

	 private
	 def configure_labyrinth
		tam_total = @labyrinth.rows * @labyrinth.cols
		n_monstruos = tam_total / 5

		# Inicialización y añadido de monstros al laberinto
		(0..n_monstruos).each{ |i|
		  monstruo = Monster.new("##{i}", Dice.random_intelligence.round(3), Dice.random_strength.round(3))
		  @labyrinth.add_monster(Dice.random_pos(@labyrinth.rows), Dice.random_pos(@labyrinth.cols), monstruo)
		}

	 end

	 def next_player
		@current_player_index = (@current_player_index + 1) % @players.size
		@current_player = @players[@current_player_index]
	 end

	 def actual_direction(preferred_direction)
		####################
	 end

	 def combat(monster)
		####################
	 end

	 def manage_reward(winner)
		####################
	 end

	 def manage_resurrection
		####################
	 end

	 def log_player_won
		@log += "Jugador ##{@current_player_index}, has ganado el combate!!\n"
	 end

	 def log_monster_won
		@log += "Jugador ##{@current_player_index}, has perdido contra el monstruo!!\n"
	 end

	 def log_resurrected
		@log += "Jugador ##{@current_player_index}, has sido resucitado!!\n"
	 end

	 def log_player_skip_turn
		@log += "Jugador ##{@current_player_index}, estás muerto. Se pasa tu turno.\n"
	 end

	 def log_player_no_orders
		@log += "No es posible seguir la instrucción\n"
	 end

	 def log_no_monster
		@log += "Jugador ##{@current_player_index}, estás en una casilla vacía o la de salida.\n"
	 end

	 def log_rounds(rounds, max)
		@log += "Se han producido ##{rounds} de ##{max} rondas de combate.\n"
	 end

  end	#class
end	#module

