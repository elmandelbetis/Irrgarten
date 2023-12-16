#encoding:utf-8

require_relative 'dice'
require_relative 'combat_element'


module Irrgarten

  class Weapon < CombatElement

	 def initialize(power,uses)
		super(power,uses)
	 end

	 def attack
		produce_effect
	 end

	 def to_s
		"W"+super
	 end

	 def discard
		super
	 end

  end #class

end #module