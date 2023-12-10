#encoding:utf-8
module Irrgarten

  require_relative 'dice'

  class Shield

    def initialize(protection, uses)
      @protection = protection
      @uses = uses
    end

    def protect
      if @uses > 0
        @protection
      else
        0
      end
    end

    def to_s
      "S[#{@protection}, #{@uses}]"
    end

    def discard
      Dice.discard_element(@uses)
    end

  end

end
