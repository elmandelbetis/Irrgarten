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

        @consecutive_hits = 0
        attr_accessor :name, :number, :intelligence, :strength, :health,
                      :row, :col, :consecutive_hits, :weapons, :shields


        def initialize(number, intelligence, strength)
            
            @number = number
            @intelligence = intelligence
            @strength = strength
            @name = "Player#"+number.to_s
            @health = @@INITIAL_HEALTH
            @consecutive_hits = 0
            @row = -1
            @col = -1


            @weapons = Array.new {Weapon}
            @shields = Array.new {Shield}

            w = Weapon.new(1,1)
            s = Shield.new(1,1)
            @weapons.append(w)
            @weapons.append(s)

        end

        def resurrect
            @weapons.clear
            @shields.clear
            @health = @@INITIAL_HEALTH
            reset_hits
        end

        def row
            @row
        end

        def col
            @col
        end

        def number
            @number
        end

        def set_pos(row, col)
            @row = row
            @col = col
        end

        def dead
            health <= 0
        end

        def move(direction, valid_moves)
            size = valid_moves.size
            contained = valid_moves.include?(direction)

            if size > 0 && !contained
                valid_moves[0]
            else
                direction
            end

        end

        def attack
            @strength + sum_weapons
        end

        def defend(received_attack)
            manage_hit(received_attack)
        end

        def receive_reward
            w_reward = Dice.weapons_reward
            s_reward = Dice.shields_reward

            w_reward.times do
                wnew = new_weapon
                receive_weapon(wnew)
            end

            s_reward.times do
                snew = new_shield
                receive_shields(snew)
            end

            extra_health = Dice.health_reward
            @health += extra_health
        end

        def to_string
            puts "#{name}, H: #{health}, I: #{intelligence}, S: #{strength},
            Pos: #{row},#{col}"
        end

        private
        def receive_weapon(w)
            (weapons.size - 1).downto(0) do |i|
                discard = weapons[i].discard
                weapons.delete_at(i) if discard
            end

            size = weapons.size
            weapons.push(w) if size < @@MAX_WEAPONS
        end

        def receive_shields(s)
            (shields.size - 1).downto(0) do |i|
                discard = shields[i].discard
                shields.delete_at(i) if discard
            end

            size = shields.size
            shields.push(s) if size < @@MAX_WEAPONS
        end

        def new_weapon
            Weapon(Dice.weapon_power, Dice.uses_left)
        end

        def new_shield
            Shield(Dice.shield_power, Dice.uses_left)
        end

        def sum_weapons

            sum = 0
            (0..weapons.length).each { |i|
                sum += weapons[i].attack.to_f
            }

            sum
        end

        def sum_shields

            sum = 0
            (0..shields.length).each { |i|
                sum += shields[i].protect
            }

            sum
        end

        def defensive_energy
            @intelligence + sum_shields
        end

        def manage_hit(received_attack)
            defense = defensive_energy

            if defense < received_attack
                got_wounded
                inc_consecutive_hits
            else
                reset_hits
            end


            if consecutive_hits == @@HITS2LOSE || dead
                reset_hits
                lose = true
            else
                lose = false
            end

            lose
        end

        def reset_hits
            @consecutive_hits = 0
        end

        def got_wounded
            @health -= 1
        end

        def inc_consecutive_hits
            @consecutive_hits += 1
        end
    end


end



