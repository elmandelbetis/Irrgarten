#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Player.rb        # 
#######################################

module Irrgarten

    class Player

        require_relative 'Weapon.rb'
        require_relative 'Shield.rb'
        require_relative 'Directions.rb'
        require_relative 'Dice.rb'

        @@MAX_WEAPONS = 2
        @@MAX_SHIELDS = 3
        @@INITIAL_HEALTH = 10
        @@HITS2LOSE = 3

        attr_accessor :name, :number, :intelligence, :strength, :health, :row, :col
        attr_accessor :consecutive_hits
        attr_accessor :weapons
        attr_accessor :shields

        def initialize(number, intelligence, strength)
            
            @number = number
            @intelligence = intelligence
            @strength = strength
            @name = "Player##{number}"
            @health = @@INITIAL_HEALTH

            @weapons = Array.new(@@MAX_WEAPONS) #capacidad inicial, pero NO FIJA
            @shields = Array.new(@@MAX_SHIELDS)

            @row = nil
            @col = nil 

        end

        def resurrect
            @weapons.clear
            @shields.clear
            @health = @@INITIAL_HEALTH
            self.reset_hits
        end

        def get_row
            @row
        end

        def get_col
            @col
        end

        def get_number
            @number
        end

        def set_pos(row, col)
            @row = row
            @col = col
        end

        def dead

            is_dead = false

            if @health <= 0
                is_dead = true
            end

            is_dead

        end

        def move(direction, valid_moves)
            #próximas prácticas
        end

        def attack
            attack = strength + sum_weapons
            attack
        end

        def defend(receivedAttack)
            #próximas prácticas
        end

        def receive_reward
            #próximas prácticas
        end

        def to_string
            puts "#{name}, H: #{health}, I: #{intelligence}, S: #{strength},
            Pos: #{row},#{col}"
        end

        private
        def receive_weapon(w)
            #próximas prácticas
        end

        def receive_shields(s)
            #próximas prácticas
        end

        def new_weapon
            Weapon(Dice.weapon_power, Dice.uses_left)
        end

        def new_shield
            Shield(Dice.shield_power, Dice.uses_left)
        end

        def sum_weapons

            for i in 0..weapons.length
                sum += weapons[i].attack.to_f
            end

            sum
        end

        def sum_shields
            for i in 0..shields.length
                sum+= shields[i].protect
            end

            sum
        end

        def defensive_energy
            #próximas prácticas
        end

        def reset_hits
            @consecutive_hits = 0
        end

        def got_wounded
            @health -= 1
            puts "#{name} got wounded, -1 HP"
        end

        def inc_consecutive_hits
            @consecutive_hits += 1
        end


    end


end



