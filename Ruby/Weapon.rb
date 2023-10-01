#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Weapon.rb        #
#######################################

module Irrgarten

     #clase Weapon 
     
     class Weapon

          attr_accessor :power
          attr_accessor :uses

          #Constructor

          def initialize(power, uses)
               @power = power
               @uses = uses
          end

          # Método "attack"
          # Devuelve el valor de "power" (poder de ataque) siempre y cuando el valor
          # de "uses" sea mayor que 0.

          def attack
               if @uses > 0
               @uses -= 1
               power
          else
               0
          end

          # Método "to_string" de la clase Weapon, devuelve una cadena con el valor
          # de "power" y "uses" restantes del arma en sí

          def to_s
               puts "W[#{@power}, #{@uses}]"
          end

          # Llamada a la clase Dice, que determina en este caso si hay que descartar
          # el arma

          def discard
            return Dice.discardElement(uses)
          end
          
     end

end

