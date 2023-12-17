#encoding:utf-8

require_relative 'dice'

module Irrgarten

  class CombatElement

    attr_reader :effect, :uses

    # Constructor privado de la clase "abstracta"
    private def initialize(effect, uses)
      @effect = effect
      @uses = uses
    end

    # Gestiona la producción de efectos por un item en función del nº de usos restantes
    protected def produce_effect
      out = 0
      if @uses > 0
        @uses -= 1
        out = @effect
      end
      out
    end

    # Calcula a partir del dado aleatorio si descartar un arma
    def discard
      Dice.discard_element(@uses)
    end

    # To_String
    def to_s
      "[#{@effect}, #{@uses}]"
    end

  end # class
end # module


