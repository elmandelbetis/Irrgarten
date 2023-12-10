#encoding:utf-8
module Irrgarten

  require_relative 'dice'
  require_relative 'combat_element'

  class Shield < CombatElement

    def initialize(protection, uses)
      super(protection,uses)
    end

    def protect
      produce_effect
    end

    def to_s
      "S"+super
    end

    def discard
      super
    end

  end

end
