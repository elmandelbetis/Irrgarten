#encoding:utf-8

require_relative 'dice'
require_relative 'combat_element'


module Irrgarten

  class Weapon < CombatElement

	 # Constructor
	 def initialize(power,uses)
		super(power,uses)
	 end

	 # Acción de atacar
	 def attack
		produce_effect
	 end

	 # To_String
	 def to_s
		"W"+super
	 end

	 # Método aleatorio encargado de gestionar el descarte de armas
	 def discard
		super
	 end

  end #class
end #module