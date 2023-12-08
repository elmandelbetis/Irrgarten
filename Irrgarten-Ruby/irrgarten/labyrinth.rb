#encoding:utf-8

module Irrgarten

  require_relative 'dice'
  require_relative 'player'
  require_relative 'monster'
  require_relative 'directions'
  require_relative 'orientation'
  require 'matrix'

  class Labyrinth

	 @@BLOCK_CHAR = 'X'
	 @@EMPTY_CHAR = '-'
	 @@MONSTER_CHAR = 'M'
	 @@COMBAT_CHAR = 'C'
	 @@EXIT_CHAR = 'E'
	 @@ROW = 0
	 @@COL = 1

	 attr_reader :n_rows, :n_cols, :exit_row, :exit_col

	 def initialize(n_rows, n_cols, exit_row, exit_col)
		@n_rows = n_rows
		@n_cols = n_cols
		@exit_row = exit_row
		@exit_col = exit_col

		@monsters = Array.new(@n_rows+1) {Array.new(@n_cols+1)}
		@players = Array.new(@n_rows+1) {Array.new(@n_cols+1)}
		@labyrinth = Array.new(@n_rows+1) {Array.new(@n_cols+1, @@EMPTY_CHAR)}

		@labyrinth[@exit_row][@exit_col] = @@EXIT_CHAR
	 end

	 def spread_players(players)
		####################
	 end

	 def have_a_winner
		@players[@exit_row][@exit_row] != nil
	 end

	 def to_s
		@labyrinth.to_a.map {|row| row.join(" ")}.join("\n")
	 end

	 def add_monster(row, col, monster)
		until empty_pos(row, col)
		  row = Dice.random_pos(rows)
		  col = Dice.random_pos(cols)
		end

		@labyrinth[row][col] = @@MONSTER_CHAR
		@monsters[row][col] = monster
		monster.set_pos

	 end

	 def put_player(direction, player)
		####################
	 end

	 def add_block(orientation, start_row, start_col, length)
		####################
	 end

	 def valid_moves(row, col)
		####################
	 end

	 private
	 def pos_ok(row, col)
		(row >= 0) && (col >= 0) && (row <= @n_rows) && (col <= @n_cols)
	 end

	 def empty_pos(row, col)
		@labyrinth[row][col] == @@EMPTY_CHAR
	 end

	 def monster_pos(row, col)
		@labyrinth[row][col] == @@MONSTER_CHAR
	 end

	 def exit_pos(row, col)
		@labyrinth[row][col] == @@EXIT_CHAR
	 end

	 def combat_pos(row, col)
		@labyrinth[row][col] == @@COMBAT_CHAR
	 end

	 def can_step_on(row, col)
		pos_ok(row,col) && (empty_pos(row,col) || monster_pos(row,col) || exit_pos(row,col))
	 end

	 def update_old_pos(row, col)
		if pos_ok(row,col)
		  if @labyrinth[row][col] == @@COMBAT_CHAR
			 @labyrinth[row][col] = @@MONSTER_CHAR
		  else
			 @labyrinth[row][col] = @@EMPTY_CHAR
		  end
		end
	 end

	 def dir_2_pos(row, col, direction)
		if pos_ok(row,col)
		  case (direction)
		  when Directions::UP
			 row -= 1
		  when Directions::DOWN
			 row += 1
		  when Directions::LEFT
			 col -= 1
		  when Directions::RIGHT
			 col += 1
		  else
			 # type code here
		  end
		end

		new_pos = []
		new_pos << row
		new_pos << col

		new_pos

	 end

	 def random_empty_pos
		resultado = []

		loop do
		  resultado[@@ROW] = Dice.random_pos(@n_rows)
		  resultado[@@COL] = Dice.random_pos(@n_cols)

		  if empty_pos(resultado[@@ROW], resultado[@@COL]) && @labyrinth[@@ROW][@@COL] != @@BLOCK_CHAR
			 break
		  end
		end

		resultado

	 end

	 def put_player_2D(old_row, old_col, row, col, player)
		####################
	 end

	 def rows
		@n_rows
	 end

	 def cols
		@n_cols
	 end

  end	#class

end	#module

