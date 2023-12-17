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

	 # Constructor
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

	 # Método encargado de esparcir los jugadores por el mapa al comenzar la partida
	 def spread_players(players)
		players.each do |i|
		  pos = random_empty_pos
		  put_player_2D(-1,-1,pos[@@ROW],pos[@@COL],i)
		end
	 end

	 # Método encargado de chivar si hay un ganador
	 def have_a_winner
		@players[@exit_row][@exit_col] != nil
	 end

	 # To_String
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

	 # Método encargado de añadir y setear un monstruo en una posición del laberinto
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

	 # Método encargado de mover a un jugador y devolver un posible monstruo si la posición es M
	 def put_player(direction, player)
		old_row = player.row
		old_col = player.col

		new_pos = dir_2_pos(old_row, old_col, direction)
		put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
	 end

	 # Añade muros bloques al laberinto de diferente longitud y orientación
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

	 # Calcula las direcciones válidas posibles de movimiento para el jugador en el mapa
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

	 # Getter de la fila
	 def rows
		@n_rows
	 end

	 # Getter de la columna
	 def cols
		@n_cols
	 end

	 private
	 # Calcula si la posición del laberinto pasada como parámetro es correcta
	 def pos_ok(row, col)
		(row >= 0) && (row <= @n_rows) && (col >= 0) && (col <= @n_cols)
	 end

	 # Devuelve si la posición pasada como parámetro es vacía
	 def empty_pos(row, col)
		@labyrinth[row][col] == @@EMPTY_CHAR
	 end

	 # Devuelve si la posición pasada como parámetro es de monstruo
	 def monster_pos(row, col)
		@labyrinth[row][col] == @@MONSTER_CHAR
	 end

	 # Devuelve si la posición pasada como parámetro es la de salida
	 def exit_pos(row, col)
		@labyrinth[row][col] == @@EXIT_CHAR
	 end

	 # Devuelve si la posición pasada como parámetro es de combate
	 def combat_pos(row, col)
		@labyrinth[row][col] == @@COMBAT_CHAR
	 end

	 # Devuelve si es posible moverse sobre la posición pasada como parámetro
	 def can_step_on(row, col)
		if @labyrinth[row][col] != @@BLOCK_CHAR
		  pos_ok(row,col) and ((empty_pos(row,col) or monster_pos(row,col) or exit_pos(row, col)))
		end
	 end

	 # Actualiza la posición del laberinto a su estado anterior previo a un combate o un movimiento del jugador sobre ella
	 def update_old_pos(row, col)

		if pos_ok(row,col)
		  if @labyrinth[row][col] == @@COMBAT_CHAR
			 @labyrinth[row][col] = @@MONSTER_CHAR
		  else
			 @labyrinth[row][col] = @@EMPTY_CHAR
		  end
		end
	 end

	 # Calcula la dirección hacia una posición determinada
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

	 # Calcula posiciones aleatorias hasta que sale una vacía
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

	 # Coloca jugadores en posiciones del laberinto y actualiza el estado de la posición en función de donde haya caído este
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
	 # Nº de filas del laberinto
	 def n_rows
		@n_rows
	 end

	 # Nº de columnas del laberinto
	 def n_cols
		@n_cols
	 end

	 # Método creado para setear la posición de un fuzzy_player
	 def place_fuzzy_player(fuzzy_player, row, col)
		if pos_ok(row,col)
		  @labyrinth[row][col] = @@EMPTY_CHAR
		  fuzzy_player.set_pos(row,col)
		end
	 end

  end	# class
end	# module