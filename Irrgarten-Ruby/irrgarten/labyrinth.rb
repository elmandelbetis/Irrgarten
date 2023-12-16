#encoding:utf-8

#######################################
# Álvaro Maldonado Medina             #
# 2D- D3                              #
# Fichero: Irrgarten/Labyrinth.rb     #
#######################################

require 'matrix'
require_relative 'monster'
require_relative 'player'
require_relative 'dice'
require_relative 'shield'
require_relative 'weapon'
require_relative 'directions'
require_relative 'orientation'
require_relative 'fuzzy_player'

module Irrgarten

  class Labyrinth

	 attr_reader :n_rows, :n_cols

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

		@monsters = Array.new(n_rows+1) { Array.new(n_cols+1) }
		@players = Array.new(n_rows+1) { Array.new(n_cols+1) }
		@labyrinth = Array.new(n_rows+1) { Array.new(n_cols+1, @@EMPTY_CHAR) }
		@labyrinth[exit_row][exit_col] = @@EXIT_CHAR
	 end


	 def spread_players(players)
		players.each do |i|
		  pos = random_empty_pos
		  put_player_2D(-1,-1,pos[@@ROW],pos[@@COL],i)
		end
	 end

	 def have_a_winner
		@players[@exit_row][@exit_col] != nil
	 end

	 def to_s
		estado = ""

		(0...@n_rows).each do |i|
		  (0...@n_cols).each do |j|
			 estado += " [#{@labyrinth[i][j]}]"
		  end
		  estado += "\n"
		end

		estado += "===============================================================\n"
		estado += "Tamaño del laberinto: #{@n_rows}x#{@n_cols}\n"
		estado += "Casilla de salida: [#{@exit_row},#{@exit_col}]\n"
		estado += "===============================================================\n"

		estado
	 end


	 def add_monster(row, col, monster)

		if pos_ok(row,col)
		  if empty_pos(row,col)

			 @labyrinth[row][col] = @@MONSTER_CHAR
			 @monsters[row][col] = monster

			 if (@labyrinth[row][col] != @@BLOCK_CHAR) && (@labyrinth[row][col] != @@EXIT_CHAR)
				monster.set_pos(row,col)
			 end

		  end
		end

	 end

	 def put_player(direction, player)
		old_row = player.row
		old_col = player.col

		new_pos = dir_2_pos(old_row, old_col, direction)
		put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
	 end

	 def add_block(orientation, start_row, start_col, length)

		if orientation == Orientation::VERTICAL
		  inc_row = 1
		  inc_col = 0
		else
		  inc_row = 0
		  inc_col = 1
		end

		row = start_row
		col = start_col

		while pos_ok(row,col) && empty_pos(row,col) && length > 0
		  @labyrinth[row][col] = @@BLOCK_CHAR
		  length -= 1
		  row += inc_row
		  col += inc_col
		end

	 end

	 def valid_moves(row, col)
		output = Array.new

		if can_step_on(row+1,col)
		  output.push(Directions::DOWN)
		end
		if can_step_on(row-1,col)
		  output.push(Directions::UP)
		end
		if can_step_on(row,col+1)
		  output.push(Directions::RIGHT)
		end
		if can_step_on(row,col-1)
		  output.push(Directions::LEFT)
		end

		output
	 end

	 def rows
		@n_rows
	 end

	 def cols
		@n_cols
	 end

	 private
	 def pos_ok(row, col)
		(row >= 0) && (row <= @n_rows) && (col >= 0) && (col <= @n_cols)
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
		if @labyrinth[row][col] != @@BLOCK_CHAR
		  pos_ok(row,col) and ((empty_pos(row,col) or monster_pos(row,col) or exit_pos(row, col)))
		end
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

		new_row=row
		new_col=col

		case direction
		when Directions::UP
		  new_row=new_row-1
		when Directions::DOWN
		  new_row=new_row+1
		when Directions::LEFT
		  new_col=new_col-1
		when Directions::RIGHT
		  new_col=new_col+1
		end
		[new_row, new_col]

	 end

	 def random_empty_pos
		resultado = Array.new

		row = Dice.random_pos(@n_rows)
		col = Dice.random_pos(@n_cols)

		loop do
		  if pos_ok(row,col)
			 resultado[@@ROW] = row
			 resultado[@@COL] = col
		  end

		  if empty_pos(resultado[@@ROW], resultado[@@COL]) && @labyrinth[@@ROW, @@COL] != @@BLOCK_CHAR
			 break
		  end
		end

		resultado
	 end

	 def put_player_2D(old_row, old_col, row, col, player)

		output = nil

		if can_step_on(row, col)
		  if pos_ok(row, col)
			 p = @players[old_row][old_col]

			 if p == player
				update_old_pos(old_row, old_col)
				@players[old_row][old_col] = nil
			 end

		  end

		  monster_pos = monster_pos(row, col)
		  if monster_pos
			 @labyrinth[row][col] = @@COMBAT_CHAR
			 output = @monsters[row][col]
		  else
			 number = player.number
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

	 def place_fuzzy_player(fuzzy_player, row, col)
		if pos_ok(row,col)
		  @labyrinth[row][col] = @@EMPTY_CHAR
		  fuzzy_player.set_pos(row,col)
		end
	 end

  end
end