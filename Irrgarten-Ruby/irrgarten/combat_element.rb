#encoding:utf-8

require_relative 'dice'

module Irrgarten

  class CombatElement

    attr_reader :effect, :uses

    private def initialize(effect, uses)
      @effect = effect
      @uses = uses
    end

    protected def produce_effect
      out = 0
      if @uses > 0
        @uses -= 1
        out = @effect
      end
      out
    end

    def discard
      Dice.discard_element(@uses)
    end

    def to_s
      "[#{@effect}, #{@uses}]"
    end

  end
end


