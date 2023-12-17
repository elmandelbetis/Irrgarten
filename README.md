# ***Proyecto de Programación y Diseño Orientado a Objetos***

Proyecto para _Programación Dirigida y Orientada a Objetos (PDOO)_, del primer cuatrimestre del segundo año de carrera en Ingeniería Informática en la ETSIIT.

Se trata de crear un videojuego de rol por turnos (llamado **Irrgarten**), el cual consiste en la generación de un tablero donde se dispone de paredes, monstruos y jugadores generados y esparcidos de manera aleatoria por dicho tablero. 

También se dispone de una casilla de salida, que resulta ser el objetivo final de los jugadores puesto que, de moverse y caer encima de ésta, el juego finaliza con el respectivo jugador que haya caído encima ganando la partida.

El juego es programado de forma independiente en dos lenguajes: Java y Ruby.


## *Status actual de las prácticas*

- P1: [TERMINADA]
- P2: [TERMINADA]
- P3: [TERMINADA]
- P4: [TERMINADA]
- P5: [TERMINADA] (sólo se realiza en Java)

[PROYECTO FINALIZADO]


## *Bugs en Java*

- Al generar una instancia de juego de 1 solo jugador en el programa principal, debería ser del estilo: new Game(1). Sin embargo, sólo funciona poniendo new Game(0)
    
- Cuando juegan varios jugadores, solo juega uno. [SOLUCIONADO]
  
- Al empezar la P4, ya no se muestra el nº del jugador en el tablero [SOLUCIONADO]

- A la espera de crear e implementar la clase FuzzyPlayer de la P4, el jugador que está muerto y resucita ya no es capaz de moverse por el tablero [SOLUCIONADO]

- Parece ser que tras empezar la P4 y establecer las herencias Player -> LabyrinthCharacter y Monster -> LabyrinthCharacter, los monstruos ganan absolutamente todos los combates que juegan los jugadores al caer en sus casillas. [SOLUCIONADO]


## *Bugs en Ruby*

- Al mover un jugador, no se actualiza bien la posición del laberinto en la que estaba colocado previo a su movimiento [SOLUCIONADO]
  
- Al iniciar un juego, a veces aparecen unos jugadores solapados con otros en la misma posición del laberinto [SOLUCIONADO]

- Los monstruos se generan encima de los bloques y los solapan [SOLUCIONADO]

- Los jugadores no pueden moverse por la última fila del laberinto ya que crashea [SOLUCIONADO]

- El game_state no muestra el turno del jugador actual [SOLUCIONADO]

- Los combates provocaban un crasheo provocado por un Nil-Class error de los métodos sum_weapons y sum_shields  [SOLUCIONADO]

- Los bloques no sirven de nada, el jugador pasa por encima de ellos como si fueran simples casillas vacías [SOLUCIONADO]

- Los jugadores, sus atributos, armas y escudos aparecen como una lista de direcciones de memoria en lugar de un String normal como en la parte de Java

- Los FuzzyPlayer se instancian correctamente, pero por algún motivo extraño no se cambia bien el nombre de "Player #..." a "FuzzyPlayer #..."

- Los monstruos se generan y se añaden al laberinto encima de la casilla de salida en ocasiones [SOLUCIONADO]

- Algunas veces al iniciar un juego algún jugador aparece fuera del laberinto con posición asociada [row][col] nulas, lo que lleva a un instant crash al intentar moverlo [SOLUCIONADO A MEDIAS] --> El jugador que se instancie "erróneamente" aparece por defecto en la casilla [0,0], pero no aparece su número hasta que se decida moverlo por el mapa.


## *Irrgarten: Java Edition*

Lista y descripción de los archivos:

- [`Main.java`](Irrgarten-Java/src/main/Main.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (usado para depurar).

- [`Irrgarten.java`](Irrgarten-Java/src/main/Irrgarten.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (ejecutable por el usuario).

- [`MainGUI.java`](Irrgarten-Java/src/main/MainGUI.java): programa principal del juego, pero esta vez con una interfaz y controlador gráficos creados a partir de la interfaz UI.

- [`UI.java`](Irrgarten-Java/src/UI/UI.java): interfaz del juego.

- [`GraphicalUI.java`](Irrgarten-Java/src/UI/GraphicalUI.java): interfaz gráfica del juego, que implementa los métodos definidos en UI, entre otros.

- [`TextUI.java`](Irrgarten-Java/src/UI/TextUI.java): vista textual (en terminal) del estado del juego

- [`Controller.java`](Irrgarten-Java/src/controller/Controller.java): controlador para los jugadores, usando las teclas WASD.

- [`Cursors.java`](Irrgarten-Java/src/controller/Cursors.java): controlador gráfico del juego. Se abre como una ventana de aviso con 4 botones, programados de forma que, al hacer click sobre alguno de ellos, se devuelva alguna de las direcciones de movimiento y el jugador se mueva por el tablero a través del método nextMove() de la clase [`GraphicalUI`](Irrgarten-Java/src/UI/GraphicalUI.java).

- [`TestP1.java`](Irrgarten-Java/test/TestP1): test de la Práctica 1.

- [`Directions.java`](Irrgarten-Java/src/irrgarten/Directions.java): tipo enumerado para las direcciones posibles de movimiento para los jugadores en partida

- [`GameCharacter.java`](Irrgarten-Java/src/irrgarten/GameCharacter.java): tipo enumerado para denominar a jugadores o monstruos en el juego.

- [`Orientation.java`](Irrgarten-Java/src/irrgarten/Orientation.java): tipo enumerado para distinguir la orientación vertical u horizontal en situaciones como, por ejemplo, la configuración de las paredes dentro del laberinto.

- [`CardDeck<T>.java`](Irrgarten-Java/src/irrgarten/CardDeck.java): clase asbtracta paramétrica dedicada a ser la clase padre y gestionar las barajas aleatorias de recompensa de combate para escudos y armas del jugador.

- [`WeaponCardDeck.java`](Irrgarten-Java/src/irrgarten/WeaponCardDeck.java): clase hija de CardDeck<Weapon>, y se encarga de crear y gestionar el barajado de cartas de tipo Weapon para las recompensas de combate del jugador.

- [`ShieldCardDeck.java`](Irrgarten-Java/src/irrgarten/ShieldCardDeck.java): clase hija de CardDeck<Shield>, y se encarga de crear y gestionar el barajado de cartas de tipo Shield para las recompensas de combate del jugador.

- [`Weapon.java`](Irrgarten-Java/src/irrgarten/Weapon.java): clase Arma. Crea objetos tipo Arma para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda atacar en el juego.

- [`Shield.java`](Irrgarten-Java/src/irrgarten/Weapon.java): clase Escudo. Crea objetos tipo Escudo para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda defenderse en el juego.

- [`Dice.java`](Irrgarten-Java/src/irrgarten/Dice.java): clase que contiene métodos relacionados con las decisiones aleatorias dentro del juego.

- [`GameState.java`](Irrgarten-Java/src/irrgarten/GameState.java): clase que contiene un constructor y getters para mostrar el estado en terminal de una serie de instancias.

- [`Monster.java`](Irrgarten-Java/src/irrgarten/Monster.java): clase que crea y gestiona a los monstruos del juego.

- [`Player.java`](Irrgarten-Java/src/irrgarten/Player.java): igual que la clase [`Monster`](Irrgarten-Java/src/irrgarten/Monster.java), pero en este caso con los jugadores.

- [`FuzzyPlayer.java`](Irrgarten-Java/src/irrgarten/FuzzyPlayer.java): jugadores "fantasma" que sustituyen a los originales una vez están muertos y resucitan. Funcionan de forma más aleatoria. Clase heredera de la clase [`Player`](Irrgarten-Java/src/irrgarten/Player.java)
  
- [`Labyrinth.java`](Irrgarten-Java/src/irrgarten/Labyrinth.java): clase que contiene la espina dorsal del juego, el laberinto donde se va a desarrollar la acción. Crea el laberinto, lo rellena, gestiona las casillas turno a turno, etc.

- [`LabyrinthCharacter`](Irrgarten-Java/src/irrgarten/LabyrinthCharacter.java): clase abstracta, padre de Player, Monster, y "abuela" de FuzzyPlayer. Se encarga de definir y gestionar los métodos más básicos de jugadores, fantasmas y monstruos, así como de definir de forma abstracta los métodos de ataque y defensa para los mobs mencionados, y que cada cual pueda implementarlos a su estilo mediante un @Override.

- [`Game.java`](Irrgarten-Java/src/irrgarten/Game.java): clase que contiene y gestiona la lógica del juego en una partida (crearla, comenzarla, terminarla, etc).


## *Irrgarten: Ruby Edition*

La descripción de los archivos viene a ser la misma que en la parte de Java pues el proyecto es igual para ambos lenguajes. Sin embargo, dadas las limitaciones de Ruby, no es posible crear las clases CardDeck (y sus respectivas hijas ShieldCardDeck y WeaponCardDeck), así como definir interfaces gráficas como ocurre en Java en la Práctica 5.

Lista de los archivos:

- [`main.rb`](Irrgarten-Ruby/main/main.rb): programa main para ejecutar el juego.

- [`textUI.rb`](Irrgarten-Ruby/UI/textUI.rb): vista textual (en terminal) del estado del juego.

- [`controller.rb`](Irrgarten-Ruby/controller/controller.rb): controlador WASD para los jugadores en partida.

- [`directions.rb`](Irrgarten-Ruby/irrgarten/directions.rb): módulo encargado de crear y gestionar las variables de tipo Dirección para los jugadores, haciendo las veces de enumerado.

- [`game_character.rb`](Irrgarten-Ruby/irrgarten/game_character.rb): módulo encargado de crear y gestionar las variables de tipo Personaje en el juego, haciendo las veces de enumerado.

- [`orientation.rb`](Irrgarten-Ruby/irrgarten/orientation.rb): módulo que hace las veces de enumerado, encargado de crear y gestionar lo relacionado a las variables de tipo Orientación para, por ejemplo, la creación y colocado de bloques en el mapa.

- [`weapon.rb`](Irrgarten-Ruby/irrgarten/weapon.rb): clase Arma, heredera de [`combat_element`](Irrgarten-Ruby/irrgarten/combat_element.rb). Crea y gestiona, a partir del padre, las armas de cada jugador en los combates a lo largo de la partida.

- [`shield.rb`](Irrgarten-Ruby/irrgarten/shield.rb): clase Escudo, heredera de [`combat_element`](Irrgarten-Ruby/irrgarten/combat_element.rb). Crea y gestiona, a partir del padre, los escudos de cada jugador en los combates a lo largo de la partida.

- [`dice.rb`](Irrgarten-Ruby/irrgarten/dice.rb): clase Dado, encargada de gestionar las decisiones aleatorias durante el juego.

- [`monster.rb`](Irrgarten-Ruby/irrgarten/monster.rb): clase Monstruo, heredera de [`labyrinth_character`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb). Crea y gestiona a los monstruos en la partida.

- [`player.rb`](Irrgarten-Ruby/irrgarten/player.rb): clase Jugador, heredera de [`combat_element`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb). Crea y gestiona a los jugadores en la partida.

- [`game_state.rb`](Irrgarten-Ruby/irrgarten/game_state.rb): clase encarga de crear y mostrar el estado de la partida mediante Strings turno a turno.

- [`game.rb`](Irrgarten-Ruby/irrgarten/game.rb): clase Juego. Crea el juego y gestiona los métodos relacionados a la ejecución de este.

- [`labyrinth.rb`](Irrgarten-Ruby/irrgarten/labyrinth.rb): clase Laberinto. Crea el esqueleto de la partida, el laberinto donde va a desarrolarse la acción.

- [`labyrinth_character.rb`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb): clase encargada de gestionar los métodos más básicos de tanto jugadores como monstruos. En Java es abstracta, pero en Ruby no, pues no existe dicho tipo de clase en este lenguaje.

- [`combat_element.rb`](Irrgarten-Ruby/irrgarten/combat_element.rb): clase encargada de gestionar los métodos más básicos de tanto armas como escudos. En Java es abstracta, pero en Ruby no, pues no existe dicho tipo de clase en este lenguaje.

- [`fuzzy_player.rb`](Irrgarten-Ruby/irrgarten/fuzzy_player.rb): clase encargada de crear a los "jugadores fantasma" cuando muere un jugador durante la partida, y de sustituirlos al resucitar. Heredera de la clase [`player`](Irrgarten-Ruby/irrgarten/player.rb).

- [`testP1`](Irrgarten-Ruby/test/test_p1.rb): test de la Práctica 1.
