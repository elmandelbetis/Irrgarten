#encoding:utf-8

module Irrgarten

  require_relative 'player'
  require_relative 'game_state'
  require_relative 'game_character'
  require_relative 'directions'
  require_relative 'labyrinth'
  require_relative 'dice'
  require_relative 'monster'

  class Game

	 attr_reader :current_player_index, :log

	 @@MAX_ROUNDS = 10

	 ## TODO ##

	 def initialize(nplayers)

	 end

	 def finished

	 end

	 def next_step(preferred_direction)

	 end

	 def game_state

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

	 def log_resurrected

	 end

	 def log_player_skip_turn

	 end

	 def log_player_no_orders

	 end

	 def log_no_monster
	 end

	 def log_rounds(rounds, max)

	 end

  end
end

