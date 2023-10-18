#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/Shield.rb        #
#######################################

module Irrgarten

    class Shield

        attr_reader :protection
        attr_reader :uses
        
        #Constructor

        def initialize(protection, uses)
            @protection = protection
            @uses = uses
        end

        # Método "protect"
        # Devuelve el valor de protección de un escudo siempre y cuando el nº de
        # usos restantes sea mayor que 0

        def protect
            if @uses > 0
                @uses -= 1
                protection
            else
                0
            end
        end

        # Método to_string, muestra una cadena con los valores de protección del
        # escudo y los usos restantes

        def to_s
            puts "S[#{@protection},#{@uses}]"
        end

        # Llamada a la clase Dice y su método discardElement, que determina en 
        # este caso si hay que descartar el escudo        

        def discard
            return Dice.discardElement(uses)
        end

    end    

end