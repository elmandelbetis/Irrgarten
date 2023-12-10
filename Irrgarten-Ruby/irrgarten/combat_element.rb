#encoding:utf-8

require_relative 'dice'
require_relative 'weapon' #por si acaso
require_relative 'shield'

module Irrgarten

  class CombatElement

    attr_reader :effect, :uses

    private def initialize(effect, uses)
      @effect = effect
      @uses = uses
    end

    protected def produce_effect
      if @uses > 0
        @uses -= 1
        @effect
      else
        0
      end
    end

    def discard
      Dice.discard_element(@uses)
    end

    def to_s
      "[#{@effect}, #{@uses}]"
    end

  end
end


