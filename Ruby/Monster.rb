#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Monster.rb       # 
#######################################

module  Irrgarten
    
    class Monster

        @@INITIAL_HEALTH = 5
        attr_accessor :name, :intelligence, :strength, :health
        attr_accessor :row, :col

        def initialize (name, intelligence, strength)
            @name = name
            @intelligence = intelligence
            @strength = strength
            @health = @@INITIAL_HEALTH
        end

        def dead

            is_dead = false

            if @health <= 0
                is_dead = true
            end

            is_dead
        end 

        def attack
            Irrgarten::Dice.intensity(strength)
        end

        def set_pos(row, col)
            @row = row
            @col = col
        end 

        def to_string
            puts "Monster: #{name}, H: #{@health}, S: #{strength},
            I #{intelligence}, Pos: #{row},#{col}"
        end 

        def gotWounded
            health -= 1
            puts "Monster #{name} got wounded, -1 HP"
        end

        def defend(receivedAttack)
            #próximas prácticas
        end 
        

    end
    
end