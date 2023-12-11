
require 'io/console'
require_relative '../irrgarten/directions'
require_relative '../irrgarten/game_state'

module UI

  class TextUI

    #https://gist.github.com/acook/4190379
    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!
    
      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "w"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "s"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "d"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "a"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "W"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "S"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "D"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "A"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end

    def show_game(game_state)
      puts game_state.labyrinth.to_s
      puts "Lista de jugadores: #{game_state.players}"
      puts "Lista de monstruos: #{game_state.monsters}"
      puts "Jugador actual: #{game_state.current_player}"
      if game_state.winner
        puts "Hay algún ganador?: Sí, el jugador #{game_state.current_player} ha ganado la partida"
      else
        puts "Hay algún ganador?: No"
      end
      puts game_state.log.to_s
      puts "==============================================================="

    end

  end # class   

end # module   


