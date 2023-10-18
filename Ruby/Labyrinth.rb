#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Labyrinth.rb     # 
#######################################

module Irrgarten

    require_relative 'Monster.rb'
    require_relative 'Player.rb'
    require_relative 'Dice.rb'
    require_relative 'Shield.rb'
    require_relative 'Weapon.rb'
    require_relative 'Directions.rb'
    require_relative 'Orientation.rb'

    
    class Labyrinth
        

        attr_accessor :n_rows
        attr_accessor :n_cols
        attr_accessor :exit_row
        attr_accessor :exit_col

        attr_reader :monsters, :players, :labyrinth

        @@BLOCK_CHAR = 'X'
        @@EMPTY_CHAR = '-'
        @@MONSTER_CHAR = 'M'
        @@COMBAT_CHAR = 'C'
        @@EXIT_CHAR = 'E'
        @@ROW = 0
        @@COL = 1

        def initialize (n_rows, n_cols, exit_row, exit_col)

            @n_rows = n_rows
            @n_cols = n_cols
            @exit_row = exit_row
            @exit_col = exit_col

            @monsters = Array.new(n_rows) { Array.new(n_cols) { Irrgarten::Monster } }
            @players = Array.new(n_rows) { Array.new(n_cols) { Irrgarten::Player } }
            @labyrinth = Array.new(n_rows) { Array.new(n_cols) }

        end

        def spread_players(players)
            # próxima práctica
        end

        def have_a_winner

            @labyrinth.each do |row|
                row.each do |labyrinth|
                    return true if labyrinth == @@EXIT_CHAR
                end
            end
        end

        def to_string
            #estado del laberinto en string
        end

        def add_monster(row, col, monster)

            is_pos_ok = pos_ok(row,col)

            if @labyrinth[row][col] == @@EMPTY_CHAR && is_pos_ok
                
                @labyrinth[row][col] = @@MONSTER_CHAR
                @monsters[row][col] = monster
                
                Irrgarten::Monster.set_pos(row,col)
 
            end 
                    
        end

        def put_player(directions, player)
            # próxima práctica
        end

        def add_block(orientation, start_row, start_col, length)
            # próxima práctica
        end

        def valid_moves(row, col)
            # próxima práctica
        end

        def pos_ok(row, col)
            pos_ok = false

            if row >= 0 && @@ROW <= @n_rows && col >= @@COL && col <= @n_cols
                pos_ok = true
            end

            pos_ok
        end
        
        def empty_pos(row, col)

            is_empty = false

            if labyrinth[row][col] == @@EMPTY_CHAR
                is_empty = true
            end

            is_empty

        end

        def monster_pos(row, col)
            
            monster_there = false

            if labyrinth[row][col] == @@MONSTER_CHAR
                monster_there = true
            end

            monster_there

        end

        def exit_pos(row, col)

            exit_pos = false

            if labyrinth[row][col] == @@EXIT_CHAR
                exit_pos = true
            end

            exit_pos

        end

        def combat_pos(row, col)

            combat_pos = false

            if labyrinth[row][col] == @@COMBAT_CHAR
                combat_pos = true
            end

            combat_pos

        end

        def can_step_on(row, col)

            can_step_on = false

            if pos_ok(row,col) == true

                if (empty_pos(row,col) == true || monster_pos(row,col) == true ||
                     exit_pos(row, col) == true)
                    
                    can_step_on = true
                
                end
            
            end

            can_step_on

        end

        def update_old_pos(row, col)

            if pos_ok(row,col) == true
                if combat_pos(row,col) == true
                    labyrinth[row][col] = @@MONSTER_CHAR
                else
                    labyrinth[row][col] == @@EMPTY_CHAR
                end
            end

        end

        def dir_2_pos(row, col, direction)

            new_pos = Array.new(2)

            if direction == Irrgarten::Directions::UP
                new_pos[0] = row - 1
                new_pos[1] = col
            elsif direction == Irrgarten::Directions::DOWN
                new_pos[0] = row + 1
                new_pos[1] = col
            elsif direction == Irrgarten::Directions::LEFT
                new_pos[0] = row
                new_pos[1] = col - 1
            elsif direction == Irrgarten::Directions::RIGHT
                new_pos[0] = row
                new_pos[1] = col + 1
            end    

            new_pos                        

        end

        def random_empty_pos(n_rows, n_cols)    #realmente no tendría que poner parámetros pero no se me ocurre otra cosa

            random_empty_pos = Array.new(2)
            random_row = Irrgarten::Dice.random_pos(n_rows)
            random_col = Irrgarten::Dice.random_pos(n_cols)
            
            while empty_pos(random_row, random_col) == false

                random_row = Irrgarten::Dice.random_pos(n_rows)
                random_col = Irrgarten::Dice.random_pos(n_cols)

                random_empty_pos[0] = random_row
                random_empty_pos[1] = random_col

            end

            random_empty_pos

        end

        def put_player_2D(old_row, old_col, row, col)
            #próxima práctica
        end




        



    end
end