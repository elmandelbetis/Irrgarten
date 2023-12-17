#encoding:utf-8

module Irrgarten

  class LabyrinthCharacter

    attr_reader :name, :intelligence, :strength, :health, :row, :col

    # Constructor privado de la clase "abstracta"
    private def initialize(name, intelligence, strength, health)
      @name = name
      @intelligence = intelligence
      @strength = strength
      @health = health
      @row = 0
      @col = 0
    end

    # Constructor de copia
    def other_lab_character(other)
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      @row = other.row
      @col = other.col
    end

    # Determina si el personaje está muerto
    def dead
      @health == 0
    end

    # Fila del mob
    def row
      @row
    end

    # Columna del mob
    def col
      @col
    end

    protected
    # Getter de inteligencia
    def intelligence
      @intelligence
    end

    # Getter de fuerza
    def strength
      @strength
    end

    # Getter de salud
    def health
      @health
    end

    # Setter de salud
    def set_health(health)
      @health = health
    end

    public

    # Setter de la posición para el mob
    def set_pos(row,col)
      @row = row
      @col = col
    end

    # To_String
    def to_s
      "#{@name}, H: #{@health}, I: #{@intelligence}, S: #{@strength}, Pos: [#{@row},#{@col}]\n"
    end

    # Reduce la salud del mob en 1 unidad
    protected def got_wounded
      @health -= 1
    end

    # Método que se hace override en las clases Player y Monster. Gestiona el ataque
    def attack
      #abstract method
    end

    # Método que se hace override en las clases Player y Monster. Gestiona la defensa
    def defend(attack)
      #abstract method
    end

    # Setter del nombre del mob
    def set_name(name)
      @name = name
    end


  end #class

end #module
