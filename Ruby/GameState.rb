#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/GameState.rb     #
#######################################


module Irrgarten

    require_relative 'Player.rb'
    require_relative 'Labyrinth.rb'
    require_relative 'Monster.rb'
    require_relative 'Game.rb'

    class GameState

        #Constructor

        def initialize(labyrinth, players, monsters, current_player, winner,
                       log)
            
            @labyrinth = labyrinth.to_s
            @players = players.to_s
            @monsters = monsters.to_s
            @current_player = current_player.to_s
            @winner = winner.to_s
            @log = log.to_s


        end

        # Consultores

        def get_labyrinth
            @labyrinth.to_s
        end

        def get_players
            @players
        end

        def get_monsters
            @monsters
        end

        def get_current_player
            @current_player.to_s
        end

        def get_winner
            @winner.to_s
        end

        def get_log
            @log.to_s
        end

    end

end