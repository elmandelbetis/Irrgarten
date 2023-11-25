#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Dice.rb          #
#######################################


module Irrgarten

    class Dice

        @@MAX_USES = 5
        @@MAX_INTELLIGENCE = 10.0
        @@MAX_STRENGTH = 10.0
        @@RESURRECT_PROB = 0.3
        @@WEAPONS_REWARD = 2
        @@SHIELDS_REWARD = 3
        @@HEALTH_REWARD = 5
        @@MAX_ATTACK = 3
        @@MAX_SHIELD = 2

        @@generator = Random.new

        # Método randomPos
        # Devuelve un valor aleatorio de posición de fila o columna en el 
        # tablero

        def self.MAX_ATTACK
            @@MAX_ATTACK
        end

        def self.MAX_SHIELD
            @@MAX_SHIELD
        end

        def self.MAX_USES
            @@MAX_USES
        end

        def self.random_pos(max)
            if max >= 0
                @@generator.rand(max)
            else
                -1
            end
        end

        # Método whoStarts
        # Devuelve el number ID del jugador que empieza la partida, siendo el
        # primero el 0 y el último "nplayers"

        def self.who_starts(n_players)
            @@generator.rand(n_players + 1)
        end

        # Métodos randomIntelligence y randomStrength
        # Devuelve un valor de inteligencia o fuerza contenido entre 0-9.9

        def self.random_intelligence
            @@generator.rand(@@MAX_INTELLIGENCE)
        end


        def self.random_strength
            @@generator.rand(@@MAX_STRENGTH)
        end

        # Método resurrectPlayer
        # Devuelve un valor booleano llamado "resurrect" que funciona de la 
        # siguiente manera: si el valor de probabilidad aleatoria "prob_res" es 
        # menor o igual a RESURRECT_PROB (0.3), se resucita al jugador. Si no,
        # no resucita el jugador

        def self.resurrect_player
            resurrect = false
            prob_resurrect = @@generator.rand(0.0..1.0)

            if prob_resurrect <= @@RESURRECT_PROB
                resurrect = true
            end
            resurrect

        end 

        # Métodos Reward
        # Cada uno de ellos devuelve el nº de armas, escudo y/o salud que gana un
        # jugador al ganar un combate

        def self.weapons_reward
            @@generator.rand(0..@@WEAPONS_REWARD).to_i
        end

     
        def self.shields_reward
            @@generator.rand(0..@@SHIELDS_REWARD).to_i
        end

     
        def self.health_reward
            @@generator.rand(0..@@HEALTH_REWARD).to_i
        end

        # Métodos Power
        # Devuelven un valor entre 0 y el máximo al que pueda optar del atributo
        # de instancia al que esté llamando cada uno
      
        def self.weapon_power
            @@generator.rand(0.0..@@MAX_ATTACK).to_f
        end

    
        def self.shield_power
            @@generator.rand(0.0..@@MAX_SHIELD).to_f
        end

        # Método usesLeft
        # Devuelve el nº de usos que se le asigna aleatoriamente a un arma o
        # escudo, comprendido entre 0 y el máximo de usos permitidos por item

        def self.uses_left
            @@generator.rand(0..@@MAX_USES).to_i
        end

        # Método intensity
        # Devuelve la cantidad de competencia aplicada entre 0 y "competence"

        def self.intensity(competence)
            @@generator.rand(0..competence).to_f
        end


        # Método discardElement
        # Devuelve "true" o "false" según los usos sean 0 ó un valor aleatorio generado
        # generado entre 0 y 1 sea menor o no al valor de probabilidad calculado
        # en función del nº de usos restantes

        
        def self.discard_element(usesLeft)

            discard = false

            if usesLeft == 0
                discard = true
            else
                prob_discard = 1.0 / (usesLeft + 1)
                random_value = @@generator.rand(0.0..1.1).to_f

                if random_value <= prob_discard
                    discard = true
                end
            end
            return discard
        end

    end
end