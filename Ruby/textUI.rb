
require 'io/console'
require_relative 'Directions'

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
        c = gets.chomp
        case c
          when "\e[w"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "\e[s"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "\e[d"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "\e[a"
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
      puts "====================================="
      puts game_state.get_labyrinth
      puts "Jugador actual: " << game_state.get_current_player.to_s
      puts "Hay ganador?: " << game_state.get_winner.to_s
      puts game_state.get_log.to_s

    end

  end # class   

end # module   


