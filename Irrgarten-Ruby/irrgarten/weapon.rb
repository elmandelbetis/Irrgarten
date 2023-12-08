#encoding:utf-8
module Irrgarten

  require_relative 'dice'

  class Weapon

	 def initialize(power,uses)
		@power = power
		@uses = uses
	 end

	 def attack
		if @uses > 0
		  @power.to_f
		else
		  0
		end
	 end

	 def to_s
		"W[#{@power.to_f}, #{@uses}]"
	 end

	 def discard
		Dice.discard_element(@uses)
	 end

  end #class

end #module