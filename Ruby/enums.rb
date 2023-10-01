#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/enums.rb         # 
#######################################

module Irrgarten

  # Módulos que funcionan como enumerados, ya que en Ruby no existe el tipo
  # enumerado como tal
  
  module Directions
    LEFT = :left 
    RIGHT = :right 
    UP = :up
    DOWN = :down
  end 

  module Orientation
    VERTICAL = :vertical
    HORIZONTAL = :horizontal
  end

  module GameCharacter
    PLAYER = :player 
    MONSTER = :monster
  end

end
  