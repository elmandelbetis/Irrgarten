#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Labyrinth.rb     # 
#######################################

require 'matrix'

module Irrgarten

    require_relative 'Monster.rb'
    require_relative 'Player.rb'
    require_relative 'Dice.rb'
    require_relative 'Shield.rb'
    require_relative 'Weapon.rb'
    require_relative 'Directions.rb'
    require_relative 'Orientation.rb'

    
    class Labyrinth

        @@BLOCK_CHAR = 'X'
        @@EMPTY_CHAR = '-'
        @@MONSTER_CHAR = 'M'
        @@COMBAT_CHAR = 'C'
        @@EXIT_CHAR = 'E'
        @@ROW = 0
        @@COL = 1

        attr_accessor :n_rows, :n_cols, :exit_row, :exit_col
        attr_reader :monsters, :players, :labyrinth

        def initialize (n_rows, n_cols, exit_row, exit_col)

            @n_rows = n_rows
            @n_cols = n_cols
            @exit_row = exit_row
            @exit_col = exit_col

            @monsters = Array.new(n_rows) { Array.new(n_cols) {nil} }
            @players = Array.new(n_rows) { Array.new(n_cols) {nil} }
            @labyrinth = Array.new(n_rows) { Array.new(n_cols){nil} }

        end

        def spread_players(players)
            players.size.each { |i|
                p = players[i]
                pos = Array.new(random_empty_pos)
                put_player_2D(-1,-1,@@ROW,@@COL,p)
            }
        end

        def have_a_winner

            @labyrinth.each do |row|
                row.each do |labyrinth|
                    return true if labyrinth == @@EXIT_CHAR
                end
            end
        end

        def to_string
            estado = ""
            (0..@n_rows - 1).each { |i|
                (0..n_cols - 1).each { |j|
                    estado << @labyrinth[i][j] #append a string
                }
                estado << "\n"
            }
            estado << "\n"

            estado
        end

        def add_monster(row, col, monster)

            if @labyrinth[row][col] == @@EMPTY_CHAR && pos_ok(row,col)
                
                @labyrinth[row][col] = @@MONSTER_CHAR
                @monsters[row][col] = monster
                
                Monster.set_pos(row,col)
 
            end 
                    
        end

        def put_player(direction, player)
            old_row = player[row]
            old_col = player[col]

            new_pos = dir_2_pos(old_row, old_col, direction)
            put_player_2D(old_row, old_col, new_pos[0], new_pos[1], player)
        end

        def add_block(orientation, start_row, start_col, length)

            if orientation == Orientation.VERTICAL
                inc_row = 1
                inc_col = 0
            else
                inc_row = 0
                inc_col = 1
            end

            row = start_row
            col = start_col

            while pos_ok(row,col) && empty_pos(row,col) && length > 0
                labyrinth[row][col] = @@BLOCK_CHAR
                length -= 1
                row += inc_row
                col += inc_col
            end

        end

        def valid_moves(row, col)
            output = Array.new

            output << Directions::DOWN if can_step_on(row + 1, col)
            output << Directions::UP if can_step_on(row - 1, col)
            output << Directions::RIGHT if can_step_on(row, col + 1)
            output << Directions::LEFT if can_step_on(row, col - 1)

            output
        end

        private
        def pos_ok(row, col)
            true if row >= 0 && row <= @n_rows && @col >= 0 && col <= @n_cols
        end
        
        def empty_pos(row, col)
            true if labyrinth[row][col] == @@EMPTY_CHAR
        end

        def monster_pos(row, col)
            true if labyrinth[row][col] == @@MONSTER_CHAR
        end

        def exit_pos(row, col)
            true if labyrinth[row][col] == @@EXIT_CHAR
        end

        def combat_pos(row, col)
            true if labyrinth[row][col] == @@COMBAT_CHAR
        end

        def can_step_on(row, col)

            can_step_on = false

            if pos_ok(row, col)

                if empty_pos(row, col) || monster_pos(row, col) ||
                  exit_pos(row, col)
                    
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

        def random_empty_pos   #realmente no tendría que poner parámetros pero no se me ocurre otra cosa

            random_empty_pos = Array.new(2)
            random_row = Dice.random_pos(n_rows)
            random_col = Dice.random_pos(n_cols)
            
            while self.empty_pos(random_row, random_col) == false

                random_row = Dice.random_pos(n_rows)
                random_col = Dice.random_pos(n_cols)

                random_empty_pos[0] = random_row
                random_empty_pos[1] = random_col

            end

            random_empty_pos

        end

        def put_player_2D(old_row, old_col, row, col, player)

            output = nil

            if can_step_on(row, col)
                if pos_ok(old_row, old_col)
                    p = players[old_row][old_col]

                    if p == player
                        update_old_pos(old_row, old_col)
                        @players[old_row][old_col] = nil
                    end

                end

                monster_pos = monster_pos(row, col)
                if monster_pos
                    @labyrinth[row][col] = @@COMBAT_CHAR
                    output = monsters[row][col]
                else
                    number = player.get_number
                    @labyrinth[row][col] = number
                end

                @players[row][col] = player
                player.set_pos(row, col)

            end

            output

        end

        public
        def n_rows
            @n_rows
        end

        def n_cols
            @n_cols
        end




        



    end
end