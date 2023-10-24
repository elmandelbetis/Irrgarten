#encoding:utf-8


module Irrgarten
	
	class Game

		attr_reader :current_player_index :log 
        attr_accessor :players :monsters, :players, :labyrinth
        attr_accessor :current_player

		@@MAX_ROUNDS = 10

		def Game(nplayers)
			
			@current_player_index = Irrgarten::Dice.who_starts(nplayers)
			@labyrinth = Irrgarten::Labyrinth.new(5,7,2,0)
			@players = Array.new() { Irrgarten::Player  }
			@monsters = Array.new() { Irrgarten::Monster }
			@log = nil

			for i in (0..nplayers)
				@players << Player.new(i.chr, Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)
			end

			@current_player = @players[current_player_index]

		
		end

		def finished
		end

		def nextStep(preferred_direction)
		end

		def get_game_state
		end

		def configure_labyrinth
		end

		def next_player
		end

		def actual_direction(preferred_direction)
		end

		def combat(monster)
		end

		def manage_reward(winner)
		end

		def manage_resurrection
		end

		def log_player_won
		end

		def lof_monster_won
		end

		def log_resurrected
		end

		def log_player_skip_turn
		end

		def log_player_no_orders
		end

		def log_no_monsters
		end

		def log_rounds(rounds, max)
		end









	end

end
