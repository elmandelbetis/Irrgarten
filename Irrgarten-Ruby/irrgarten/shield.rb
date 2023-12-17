#encoding:utf-8
module Irrgarten

  require_relative 'dice'
  require_relative 'combat_element'

  class Shield < CombatElement

    # Constructor
    def initialize(protection, uses)
      super(protection,uses)
    end

    # Acción de protección de un escudo
    def protect
      produce_effect
    end

    # To_String
    def to_s
      "S"+super
    end

    # Método aleatorio de descarte de un escudo
    def discard
      super
    end

  end # class
end # module
