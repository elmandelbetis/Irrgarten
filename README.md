# Irrgarten

Proyecto para Programación Dirigida y Orientada a Objetos, de segundo año de carrera. Se trata de crear un videojuego 
de rol tanto en Java como Ruby. El juego trata sobre escapar de un laberinto lleno de monstruos

====================================================================================================================
                                              CHANGELOG PRÁCTICA 1
====================================================================================================================

En la Práctica 1 del proyecto se nos pide crear los siguientes ficheros en lenguajes Java y Ruby:


- Enums.java y Enums.rb:

Contienen tipos de dato enumerado (Directions, Orientation y GameCharacter). El primero crea
las direcciones a las que se puede mover un jugador o monstruo por el tablero (izquierda,
derecha,arriba, abajo), el segundo son orientaciones verticales u horizontales, y el último
identifica a un jugador o bien a un monstruo.


- Clase Weapon:

Esta clase se vale de los atributos de instancia privados "power" y "uses" para definir las armas como
objeto del juego. Contiene métodos de consulta y modificadores para cada uno de los dos atributos, un método "attack",
que devuelve la potencia de ataque por cada uso que se le da a un arma hastas que se reducen a cero; un método
"to_string" que devuelve el valor del poder de ataque y los usos restantes del arma, y un método booleano llamado
discard, que hace una llamada a la clase Dice y a un método que permite descartar un arma en función del número de usos
restantes.


- Clase Shield:

Contiene dos atributos de instancia privados llamados "protection" y "uses". Crean a los escudos como
objeto del juego. Aparte de contener un consultor y un modificador para cada atributo, tenemos un método similar al
"attack" de la clase Weapon, llamado "protect", que también define la protección dada por el escudo y reduce los usos
restantes en 1 cada vez que es llamado al uso dicho item. Contiene también un método "to_string" y otro "discard", que
funcionan de la misma manera que sus homólogos de la clase Weapon.


- Clase Dice:

Es la clase más importante de la práctica. Contiene 6 atributos de instancia privados de tipo int, y 3 de tipo float. 
En dichos métodos se definen las siguientes constantes: MAX_USES (número máximo de usos que puede tener un item), 
MAX_ATTACK (valor máximo de ataque aplicable a un arma), MAX_SHIELD (valor máximo aplicable de protección de un 
escudo), WEAPONS_REWARD, SHIELD_REWARD, HEALTH_REWARD (número máximo de armas, escudos o salud que se recolecta por el 
jugador al ganar un combate).

También existe un atributo de instancia privado que crea números aleatorios para determinar la toma de decisiones por 
parte del juego en los métodos que van a ser explicados a continuación. En la parte de Java, se crean dos métodos 
nextInt() y nextFloat() que crea números aleatorios enteros entre 0 y un valor máximo aplicado (para contener también a 
dicho valor máximo en el intervalo, será "max+1". 

Luego, y ya en ambos lennguajes, la clase contiene el método randomPos, que devuelve un valor aleatorio de posición de 
fila o columna en el tablero; el método whoStarts, que devuelve el ID del jugador que comienza la partida (el primer 
jugador es el 0 y el último se define por el parámetro "nplayers"). Tenemos luego dos métodos randomStrength y 
randomIntelligence, que crean un valor aleatorio en coma flotante entre 0 y MAX_STRENGTH o MAX_INTELLIGENCE (fuerza o 
inteligencia).

Método resurrectPlayer: devuelve un valor booleano llamado "resurrect" en el cual si un valor de probabilidad generado 
aleatoriamente es menor o igual a la constante RESURRECT_PROB, resucitamos a un jugador. Si no, no resucita.

Métodos Reward (weapons, shields y health): devuelven el número de armas, escudo o salud que gana un jugador al ganar 
un combate aleatoriamente entre 0 y el valor constante máximo de estos (int).

Método usesLeft: devuelve el nº de usos que se le asigna de forma aleatoria a un arma o escudo, comprendido entre 0 y 
el máximo de usos permitidos para un item.

Método intensity: devuelve la cantidad de competencia aplicada entre 0 y un parámetro "competence" (float).

Método discardElement: Devuelve "true" o "false" según los usos sean 0 ó un valor aleatorio generado entre 0 y 1 sea 
menor o no al valor de probabilidad calculado en función del nº de usos restantes. A través de este método se realizan 
llamadas a la clase Dice desde las clases Shield y Weapon.


- Clase GameState:

Contiene los atributos de instancia privados labyrinthv, players, monsters (estos 3 de tipo String), currentPlayer 
(int), winner (boolean) y log (String). Contiene un constructor que inicializa a cada una de los atributos, y un método 
consultor para cada uno de ellos también. 
La clase en sí está creada con la finalidad de almacenar una representación  del estado completo del juego: el estado 
del laberinto, el estado de los jugadores, el estado de los monstruos, el índice del jugador que tiene el turno, un 
indicador sobre si ya hay un ganador y un atributo adicional para guardar en una cadena de caracteres eventos
interesantes que hayan ocurrido desde el turno anterior.
