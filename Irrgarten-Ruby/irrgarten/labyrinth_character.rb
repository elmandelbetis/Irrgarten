#encoding:utf-8

module Irrgarten

  class LabyrinthCharacter

    attr_reader :name, :intelligence, :strength, :health, :row, :col

    private def initialize(name, intelligence, strength, health)
      @name = name
      @intelligence = intelligence
      @strength = strength
      @health = health
      @row = 0
      @col = 0
    end

    def other_lab_character(other)
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      @row = other.row
      @col = other.col
    end

    def dead
      @health == 0
    end

    def row
      @row
    end

    def col
      @col
    end

    protected
    def intelligence
      @intelligence
    end

    def strength
      @strength
    end

    def health
      @health
    end

    def set_health(health)
      @health = health
    end

    public
    def set_pos(row,col)
      @row = row
      @col = col
    end

    def to_s
      "#{@name.to_s}, H: #{@health.to_s}, I: #{@intelligence}, S: #{@strength}, Pos: [#{@row},#{col}]\n"
    end

    protected def got_wounded
      @health -= 1
    end

    def attack
      #abstract method
    end

    def defend(attack)
      #abstract method
    end


  end #class

end #module
