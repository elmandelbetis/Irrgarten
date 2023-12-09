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

	 def initialize(nplayers)
		@players = []
		@monsters = []
		@log = ""

		nplayers.times do |i|
		  @players << Player.new(i, Dice.random_intelligence.round(3), Dice.random_strength.round(3))
		end


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
		@log = ""
		dead = @current_player.dead

		if !dead
		  direction = actual_direction(preferred_direction)

		  if direction != preferred_direction
			 log_player_no_orders
		  end

		  monster = @labyrinth.put_player(direction, @current_player)

		  if monster == nil
			 log_no_monster
		  else
			 winner = combat(monster)
			 manage_reward(winner)
		  end

		else
		  manage_resurrection
		end

		end_game = finished

		unless end_game
		  next_player
		end

		end_game

	 end

	 def get_game_state
		GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @current_player_index, finished, log)
	 end

	 private
	 def configure_labyrinth
		tam_total = @labyrinth.rows * @labyrinth.cols
		n_monstruos = tam_total / 5

		# Inicialización y añadido de bloques al laberinto
		@labyrinth.add_block(Orientation::HORIZONTAL, 1,0,2)
		@labyrinth.add_block(Orientation::VERTICAL, 2,1,3)

		# Inicialización y añadido de monstros al laberinto
		(0..n_monstruos).each do |i|
		  monstruo = Monster.new("##{i}", Dice.random_intelligence.round(3), Dice.random_strength.round(3))
		  @monsters << monstruo
		  @labyrinth.add_monster(Dice.random_pos(@labyrinth.rows), Dice.random_pos(@labyrinth.cols), monstruo)
		end




	 end

	 def next_player
		@current_player_index = (@current_player_index + 1) % @players.size
		@current_player = @players[@current_player_index]
	 end

	 def actual_direction(preferred_direction)
		current_row = @current_player.row
		current_col = @current_player.col

		valid_moves = @labyrinth.valid_moves(current_row,current_col)
		output = @current_player.move(preferred_direction, valid_moves)

		output

	 end

	 def combat(monster)
		rounds = 0
		winner = GameCharacter::PLAYER
		player_attack = @current_player.attack
		lose = monster.defend(player_attack)

		while (!lose) && (rounds < @@MAX_ROUNDS)
		  winner = GameCharacter::MONSTER
		  rounds += 1
		  monster_attack = monster.attack
		  lose = @current_player.defend(monster_attack)

		  unless lose
			 player_attack = @current_player.attack
			 winner = GameCharacter::PLAYER
			 lose = monster.defend(player_attack)
		  end

		end

		log_rounds(rounds, @@MAX_ROUNDS)
		return winner

	 end

	 def manage_reward(winner)
		if winner == GameCharacter::PLAYER
		  @current_player.receive_reward
		  log_player_won
		else
		  log_monster_won
		end
	 end

	 def manage_resurrection
		resurrect = Dice.resurrect_player
		if resurrect
		  @current_player.resurrect
		  log_resurrected
		else
		  log_player_skip_turn
		end
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

