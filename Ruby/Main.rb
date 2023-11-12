module Main
	
	require_relative "Game.rb"
	require_relative "controller.rb"
	require_relative "textUI.rb"

  game = Irrgarten::Game.new(0)
  view = UI::TextUI.new
  controller = Control::Controller.new(game,view)

  view.show_game(game.get_game_state)
  controller.play
	


end