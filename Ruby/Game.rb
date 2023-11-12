#encoding:utf-8

#######################################
# Álvaro Maldonado Medina             #
# 2D- D3                              #
# Fichero: Irrgarten/Game.rb		      #
#######################################

module Irrgarten

		require_relative 'Monster.rb'
		require_relative 'Labyrinth.rb'
    require_relative 'Player.rb'
    require_relative 'Dice.rb'
    require_relative 'Shield.rb'
    require_relative 'Weapon.rb'
    require_relative 'Directions.rb'
    require_relative 'Orientation.rb'
	
	class Game

		attr_reader :current_player_index, :log
    attr_accessor :players, :monsters, :labyrinth
    attr_accessor :current_player

		@@MAX_ROUNDS = 10

		def initialize(nplayers)
			
			@current_player_index = Irrgarten::Dice.who_starts(nplayers)
			@labyrinth = Labyrinth.new(10,10,2,0)
			@players = Array.new { Player }
			@monsters = Array.new { Monster }
			@log = ""

			nplayers.times do  |i|
				@players[i] << Player.new((i+48).chr, Dice.random_intelligence, Dice.random_strength)
			end

			@current_player_index = Dice.who_starts(nplayers)
			@current_player = @players[current_player_index]
			configure_labyrinth

		
		end

		def finished
			@labyrinth.have_a_winner
		end

		def next_step(preferred_direction)
			@log = ""
			dead = @current_player.dead

			unless dead

				direction = actual_direction(preferred_direction)

				log_player_no_orders unless direction == preferred_direction


				monster = labyrinth.put_player(direction, @current_player)

				if monster.nil?
					log_no_monsters
				else
					winner = combat(monster)
					manage_reward(winner)
				end
			else
				manage_resurrection
			end

			end_game = finished?

			next_player unless end_game

			end_game

		end

		def get_game_state
			GameState.new(@labyrinth.to_string, @players.to_string, @monsters.to_string, @current_player_index,
																 finished, @log)
		end

		def configure_labyrinth
			tam_total = @labyrinth.n_rows * @labyrinth.n_cols
			n_monsters = tam_total / 5

			@labyrinth.add_block(Orientation::HORIZONTAL, 1, 0 , 2)
			@labyrinth.add_block(Orientation::VERTICAL, 2, 1, 2)

			(0..n_monsters).each do |i|
				monster = Monster.new("##{i}", Dice.random_intelligence, Dice.random_strength)
				@monsters << monster
				@labyrinth.add_monster(Dice.random_pos(@labyrinth.rows), Dice.random_pos(@labyrinth.cols), monster)
			end

			@labyrinth.spread_players(@players)
		end

		def next_player
			@current_player_index = (@current_player_index == @players.size)? 0 : @current_player_index + 1
		end

		def actual_direction(preferred_direction)
			current_row = @current_player.row
			current_col = @current_player.col

			valid_moves = @labyrinth.valid_moves(current_row, current_col)
			@current_player.move(preferred_direction, valid_moves)
		end

		def combat(monster)
			rounds = 0
			winner = GameCharacter::PLAYER
			player_attack = @current_player.attack
			lose = monster.defend(player_attack)

			while !lose && rounds < @@MAX_ROUNDS
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

			winner
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
			@log += "Jugador ##{@current_player_index}, has perdido contra el monstruo.\n"
		end

		def log_resurrected
			@log += "Jugador ##{@current_player_index}, has sido resucitado!!\n"
		end

		def log_player_skip_turn
			@log += "Jugador ##{@current_player_index}, estás muerto. Se pasa tu turno al siguiente.\n"
		end

		def log_player_no_orders
			@log += "No es posible seguir la instrucción.\n"
		end

		def log_no_monsters
			@log += "Jugador ##{@current_player_index}, acabas de caer en una celda vacía.\n"
		end

		def log_rounds(rounds, max)
			@log += "Ronda actual: #{rounds}/#{max}\n"
		end









	end

end
