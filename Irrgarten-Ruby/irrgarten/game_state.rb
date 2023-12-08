#encoding:utf-8

module Irrgarten

  class GameState

	 attr_accessor :labyrinth, :players, :monsters, :current_player, :winner, :log

	 def initialize(labyrinth, players, monsters, current_player, winner, log)
		@labyrinth = labyrinth.to_s
		@players = players.to_s
		@monsters = monsters.to_s
		@current_player = current_player.to_i
		@winner = !!winner	#dejarle claro al intérprete que se trata de un booleano
		@log = log.to_s
	 end

	 def labyrinth
		@labyrinth
	 end

	 def players
		@players
	 end

	 def monsters
		@monsters
	 end

	 def current_player
		@current_player
	 end

	 def winner
		@winner
	 end

	 def log
		@log
	 end

  end	#class

end	#module
