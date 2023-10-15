#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Labyrinth.rb     # 
#######################################

module Irrgarten

    class Labyrinth
        

        attr_accessor :n_rows
        attr_accessor :n_cols
        attr_accessor :exit_row
        attr_accessor :exit_col

        attr_reader :monsters, :players, :cells

        @@BLOCK_CHAR = 'X'
        @@EMPTY_CHAR = '-'
        @@MONSTER_CHAR = 'M'
        @@COMBAT_CHAT = 'C'
        @@EXIT_CHAR = 'E'
        @@ROW = 0
        @@COL = 1


        def initialize (n_rows, n_cols, exit_row, exit_col)

            @n_rows = n_rows
            @n_cols = n_cols
            @exit_row = exit_row
            @exit_col = exit_col

            @monsters = Array.new(rows) { Array.new(cols) { Irrgarten::Monster.new } }
            @players = Array.new {Array.new}
            @cells = Array.new {Array.new}

        end

        def have_a_winner

            @cells.each do |row|
                row.each do |cell|
                    return true if cell == @@EXIT_CHAR
                end
            end
        end

        def to_string
        
        end

        def pos_ok(row, col)
            pos_ok = false

            if row >= 0 && @@ROW <= @n_rows && col >= @@COL && col <= @n_cols
                pos_ok = true
            end

            pos_ok
        end

        def add_monster(row, col, monster)

            is_pos_ok = pos_ok(row,col)

            if ()
        end

        



    end
end