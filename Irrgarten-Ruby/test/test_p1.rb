#encoding:utf-8

module Irrgarten

  require_relative '../irrgarten/dice'
  require_relative '../irrgarten/weapon'
  require_relative '../irrgarten/shield'

end	#module

  def dibuja_lineas
	 puts "=============================================================================================================="
  end

  def salto_linea
	 puts "\n"
  end

	 dibuja_lineas
	 puts "PRUEBA DE LA CLASE WEAPON"

	 arma = Irrgarten::Weapon.new(Irrgarten::Dice.weapon_power.to_f, Irrgarten::Dice.uses_left)

    puts "Método to_s clase Weapon: #{arma.to_s}"

	 puts "Método attack: #{arma.attack}"
	 puts arma.to_s

	 dibuja_lineas
	 puts "PRUEBA DE LA CLASE SHIELD"

	 escudo = Irrgarten::Shield.new(Irrgarten::Dice.shield_power, Irrgarten::Dice.uses_left)

puts "Método to_s clase Shield: #{escudo.to_s}"
	 puts "Método protect: #{escudo.protect}"
	 puts escudo.to_s

	 dibuja_lineas

	 puts "PRUEBA DE LA CLASE DICE"

	 veces_a_probar = 100
	 n_decimales_aprox = 3

	 puts "Método random_pos"

	 for i in 0..veces_a_probar
		puts Irrgarten::Dice.random_pos(7)
	 end

	 salto_linea

	 puts "Método who_starts"

	 for i in 0..veces_a_probar
		puts "Empieza el jugador: #{Irrgarten::Dice.who_starts(5)}"
	 end

	 salto_linea

	 puts "Método random_intelligence"

	 for i in 0..veces_a_probar
		puts "Inteligencia: #{Irrgarten::Dice.random_intelligence.round(n_decimales_aprox)}"
	 end

	 salto_linea

	 puts "Método random_strength"

	 for i in 0..veces_a_probar
		puts "Fuerza: #{Irrgarten::Dice.random_strength.round(n_decimales_aprox)}"
	 end

	 puts "Método resurrect_player"

	 for i in 0..veces_a_probar
		#puts "Resucitado?: #{Irrgarten::Dice.resurrect_player.to_s}"
		if Irrgarten::Dice.resurrect_player
		  puts "Resucitado?: SÍ"
		else
		  puts "Resucitado?: NO"
		end
	 end

	 salto_linea

	 puts "Método weapons_reward"

	 for i in 0..veces_a_probar
		puts "Armas recibidas: #{Irrgarten::Dice.weapons_reward}"
	 end

	 puts "Método shield_reward"

	 for i in 0..veces_a_probar
		puts "Escudos recibidos: #{Irrgarten::Dice.shields_reward}"
	 end

	 salto_linea

	 puts "Método health_reward"

	 for i in 0..veces_a_probar
		puts "Salud recibida: #{Irrgarten::Dice.health_reward}"
	 end

	 salto_linea

	 puts "Método weapon_power"

	 for i in 0..veces_a_probar
		puts "Poder de arma: #{Irrgarten::Dice.weapon_power}"
	 end

	 salto_linea

	 puts "Método shield_power"
	 for i in 0..veces_a_probar
		puts "Poder de escudo: #{Irrgarten::Dice.shield_power}"
	 end

	 salto_linea

	 puts "Método uses_left"
	 for i in 0..veces_a_probar
		puts "Usos restantes: #{Irrgarten::Dice.uses_left}"
	 end

	 salto_linea

	 puts "Método intensity"

	 for i in 0..veces_a_probar
		puts "Intensidad del ataque: #{Irrgarten::Dice.intensity(10)}"
	 end

	 salto_linea

puts "Método discardElement"

usos = 10

for i in 0..15
  print "Descartado?: "
  puts Irrgarten::Dice.discard_element(usos)
  usos-=1
end





