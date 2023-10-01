#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/GameState.rb     #
#######################################


module Irrgarten

    class GameState
        
        attr_accessor :labyrinthv
        attr_accessor :players
        attr_accessor :monsters
        attr_accessor :currentPlayer
        attr_accessor :winner
        attr_accessor :log


        #Constructor

        def initialize(labyrinthv, players, monsters, currentPlayer, winner,
                       log)
            
            @labyrinthv = labyrinthv
            @players = players
            @monsters = monsters
            @currentPlayer = currentPlayer
            @winner = winner
            @log = log


        end

        # Consultores

        def labyrinthv
            @labyrinthv
        end

        def players
            @players
        end

        def monsters
            @monsters
        end

        def currentPlayer
            @currentPlayer
        end

        def winner
            @winner
        end

        def log
            @log
        end

    end

end