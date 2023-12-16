require_relative "../irrgarten/game"
require_relative "../controller/controller"
require_relative "../UI/textUI"

module Main

  game = Irrgarten::Game.new(1)

  view = UI::TextUI.new
  controller = Control::Controller.new(game,view)


  view.show_game(game.get_game_state)
  controller.play



end