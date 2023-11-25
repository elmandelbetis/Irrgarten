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

		@@MAX_ROUNDS = 10

		def initialize(nplayers)
			
			@current_player_index = Dice.who_starts(nplayers)
			@labyrinth = Labyrinth.new(5,5,2,0)
			@players = Array.new
			@monsters = Array.new
			@log = ""

			nplayers.times do  |i|
				@players.append(Player.new(i, Dice.random_intelligence, Dice.random_strength))
			end

			@current_player_index = Dice.who_starts(nplayers)
			@current_player = @players[@current_player_index]
			configure_labyrinth
			@labyrinth.spread_players(@players)
		
		end

		def finished
			@labyrinth.have_a_winner
		end

		def next_step(preferred_direction)
			@log = ""
			dead = @current_player.dead

			if dead
				direction = actual_direction(preferred_direction)

				if direction != preferred_direction
					log_player_no_orders
				end

				monster = labyrinth.put_player(direction, @current_player)

				if monster == nil
					log_no_monsters
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
			estado = GameState.new(@labyrinth.to_s, @players.to_string, @monsters.to_string,
														 @current_player_index, finished, @log)

			estado
		end

		def configure_labyrinth
			tam_total = @labyrinth.n_rows * @labyrinth.n_cols
			n_monsters = tam_total / 5

			@labyrinth.add_block(Orientation::HORIZONTAL, 1, 0 , 2)
			@labyrinth.add_block(Orientation::VERTICAL, 2, 1, 2)

			(0..n_monsters).each do |i|
				@monsters.push(Monster.new("#{i+1}", Dice.random_intelligence, Dice.random_strength))
				@labyrinth.add_monster(Dice.random_pos(@labyrinth.n_rows), Dice.random_pos(@labyrinth.n_cols), @monsters[i])
			end

			@labyrinth.spread_players(@players)
		end

		def next_player
			@current_player_index = ((@current_player_index + 1) % @players.size)
			@current_player = @players[@current_player_index]
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
				monster_attack = monster.attack
				lose = @current_player.defend(monster_attack)
				winner = GameCharacter::MONSTER
				rounds += 1


				unless lose
					winner = GameCharacter::PLAYER
					player_attack = @current_player.attack
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
