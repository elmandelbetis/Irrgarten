#encoding:utf-8

####################################### 
# Álvaro Maldonado Medina             #      
# 2D- D3                              #
# Fichero: Irrgarten/TestP1.rb        #
#######################################


module Irrgarten

    require_relative 'Dice.rb'
    require_relative 'enums.rb'
    require_relative 'GameState.rb'
    require_relative 'Shield.rb'
    require_relative 'Weapon.rb'

end

def separation_lines
    puts "===================================================================="
end

def salto_linea
    puts "\n"
end


dado = Irrgarten::Dice.new()
n_decimales = 3     # nº máximo de decimales en coma flotante a mostrar
iteraciones = 100   # nº de iteraciones que se nos pide en el guion por 
                    # método de la clase Dice




#PRUEBA DE LA CLASE WEAPON

separation_lines
puts "PROBANDO CLASE WEAPON..."

arma = Irrgarten::Weapon.new(dado.getMaxAttack.to_f, dado.getMaxUses)

#Prueba      

print "Método to_s: "
puts arma.to_s
print "Realizando ataque..."
arma.attack
puts arma.to_s


#PRUEBA DE LA CLASE SHIELD

separation_lines
puts "PROBANDO CLASE SHIELD"


escudo = Irrgarten::Shield.new(dado.getMaxShield.to_f, dado.getMaxUses)

#Prueba    

puts "Método to_s"
puts escudo.to_s
puts "Realizando acción de protección..."
escudo.protect
puts escudo.to_s

#PRUEBA DE LA CLASE DICE (100 iteraciones por cada método)

separation_lines
puts "PROBANDO CLASE DICE"


salto_linea
puts "Método randomPos para 10 filas o columnas" 

for i in 0..(iteraciones)
    puts dado.randomPos(10)
end
salto_linea


puts "Método whoStarts para 16 jugadores (0-15)"

for i in 0..(iteraciones)
    puts dado.whoStarts(15)
end
salto_linea


puts "Método randomIntelligence"

for i in 0..(iteraciones)
    puts dado.randomIntelligence.round(n_decimales)
end
salto_linea


puts "Método randomStrength"

for i in 0..(iteraciones)
    puts dado.randomStrength.round(n_decimales)
end


salto_linea
puts "Método resurrectPlayer"

for i in 0..(iteraciones)
    puts dado.resurrectPlayer
end


salto_linea
puts "Método weaponsReward"

for i in 0..(iteraciones)
    puts dado.weaponsReward
end


salto_linea
puts "Método shieldsReward"

for i in 0..(iteraciones)
    puts dado.shieldsReward
end


salto_linea
puts "Método healthReward"

for i in 0..(iteraciones)
    puts dado.healthReward
end


salto_linea
puts "Método weaponPower"

for i in 0..(iteraciones)
    puts dado.weaponPower.round(n_decimales)
end


salto_linea
puts "Método shieldPower"

for i in 0..(iteraciones)
    puts dado.shieldPower.round(n_decimales)
end


salto_linea
puts "Método usesLeft"

for i in 0..(iteraciones)
    puts dado.usesLeft
end


salto_linea
puts "Método intensity"

for i in 0..(iteraciones)
    puts dado.intensity(10).round(n_decimales)
end


salto_linea
puts "Método discardElement"

for i in 0..(iteraciones)
    puts dado.discardElement(dado.getMaxUses)
end

