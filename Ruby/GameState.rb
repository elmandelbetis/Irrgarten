#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/GameState.rb     #
#######################################


module Irrgarten

    class GameState
        
        attr_accessor :labyrinth
        attr_accessor :players
        attr_accessor :monsters
        attr_accessor :currentPlayer
        attr_accessor :winner
        attr_accessor :log


        #Constructor

        def initialize(labyrinth, players, monsters, currentPlayer, winner,
                       log)
            
            @labyrinthv = labyrinthv
            @players = players
            @monsters = monsters
            @currentPlayer = currentPlayer
            @winner = winner
            @log = log


        end

        # Consultores

        def get_labyrinth
            @labyrinth
        end

        def get_players
            @players
        end

        def get_monsters
            @monsters
        end

        def get_currentPlayer
            @currentPlayer
        end

        def get_winner
            @winner
        end

        def get_log
            @log
        end

    end

end