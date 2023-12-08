# ***Proyecto de Programación y Diseño Orientado a Objetos***

Proyecto para _Programación Dirigida y Orientada a Objetos (PDOO)_, del primer cuatrimestre del segundo año de carrera en Ingeniería Informática en la ETSIIT.

Se trata de crear un videojuego de rol por turnos (llamado **Irrgarten**), el cual consiste en la generación de un tablero donde se dispone de paredes, monstruos y jugadores generados y esparcidos de manera aleatoria por dicho tablero. 

También se dispone de una casilla de salida, que resulta ser el objetivo final de los jugadores puesto que, de moverse y caer encima de ésta, el juego finaliza con el respectivo jugador que haya caído encima ganando la partida.

El juego es programado de forma independiente en dos lenguajes: Java y Ruby.

## *Status actual de las prácticas*

- P1: [Terminada]
- P2: [Terminada]
- P3: [Terminada]
- P4: [Terminada en Java]
- P5: [TERMINADA] (sólo se realiza en Java)

### **Bugs en Java**

- Al generar una instancia de juego de 1 solo jugador en el programa principal, debería ser del estilo: new Game(1). Sin embargo, sólo funciona poniendo new Game(0)
    
- Cuando juegan varios jugadores, solo juega uno. [SOLUCIONADO]
  
- Al empezar la P4, ya no se muestra el nº del jugador en el tablero [SOLUCIONADO]

- A la espera de crear e implementar la clase FuzzyPlayer de la P4, el jugador que está muerto y resucita ya no es capaz de moverse por el tablero [SOLUCIONADO]

- Parece ser que tras empezar la P4 y establecer las herencias Player -> LabyrinthCharacter y Monster -> LabyrinthCharacter, los monstruos ganan absolutamente todos los combates que juegan los jugadores al caer en sus casillas. [SOLUCIONADO]


### **Bugs en Ruby**

(A la espera de empezar la P4 en Ruby)

- Al mover un jugador, no se actualiza bien la posición del laberinto en la que estaba colocado previo a su movimiento
  
- Al iniciar un juego, a veces aparecen unos jugadores solapados con otros en la misma posición del laberinto 

- El game_state no muestra el turno del jugador actual

- Los jugadores, sus armas y escudos aparecen como una lista de direcciones de memoria en lugar de un String

- Puede ser que los combates no se estén realizando correctamente

### **Irrgarten: Java Edition**

Lista y descripción de los archivos:

- [Main.java](Irrgarten-Java/src/main/Main.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (usado para depurar).

- [Irrgarten.java](Irrgarten-Java/src/main/Irrgarten.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (ejecutable por el usuario).

- [MainGUI.java](Irrgarten-Java/src/main/MainGUI.java): programa principal del juego, pero esta vez con una interfaz y controlador gráficos creados a partir de la interfaz UI.

- [UI.java](Irrgarten-Java/src/UI/UI.java): interfaz del juego.

- [GraphicalUI.java](Irrgarten-Java/src/UI/GraphicalUI.java): interfaz gráfica del juego, que implementa los métodos definidos en UI, entre otros.

- [TextUI.java](Irrgarten-Java/src/UI/TextUI.java): vista textual (en terminal) del estado del juego

- [Controller.java](Irrgarten-Java/src/controller/Controller.java): controlador para los jugadores, usando las teclas WASD.

- [Cursors.java](Irrgarten-Java/src/controller/Cursors.java): controlador gráfico del juego. Se abre como una ventana de aviso con 4 botones, programados de forma que, al hacer click sobre alguno de ellos, se devuelva alguna de las direcciones de movimiento y el jugador se mueva por el tablero a través del método nextMove() de la clase [GraphicalUI](Irrgarten-Java/src/UI/GraphicalUI.java).

- [TestP1.java](Irrgarten-Java/test/TestP1): test de la Práctica 1.

- [Directions.java](Irrgarten-Java/src/irrgarten/Directions.java): tipo enumerado para las direcciones posibles de movimiento para los jugadores en partida

- [GameCharacter.java](Irrgarten-Java/src/irrgarten/GameCharacter.java): tipo enumerado para denominar a jugadores o monstruos en el juego.

- [Orientation.java](Irrgarten-Java/src/irrgarten/Orientation.java): tipo enumerado para distinguir la orientación vertical u horizontal en situaciones como, por ejemplo, la configuración de las paredes dentro del laberinto.

-[CardDeck<T>.java](Irrgarten-Java/src/irrgarten/CardDeck.java): clase asbtracta paramétrica dedicada a ser la clase padre y gestionar las barajas aleatorias de recompensa de combate para escudos y armas del jugador.

-[WeaponCardDeck.java](Irrgarten-Java/src/irrgarten/WeaponCardDeck.java): clase hija de CardDeck<Weapon>, y se encarga de crear y gestionar el barajado de cartas de tipo Weapon para las recompensas de combate del jugador.

-[ShieldCardDeck.java](Irrgarten-Java/src/irrgarten/ShieldCardDeck.java): clase hija de CardDeck<Shield>, y se encarga de crear y gestionar el barajado de cartas de tipo Shield para las recompensas de combate del jugador.

- [Weapon.java](Irrgarten-Java/src/irrgarten/Weapon.java): clase Arma. Crea objetos tipo Arma para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda atacar en el juego.

- [Shield.java](Irrgarten-Java/src/irrgarten/Weapon.java): clase Escudo. Crea objetos tipo Escudo para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda defenderse en el juego.

- [Dice.java](Irrgarten-Java/src/irrgarten/Dice.java): clase que contiene métodos relacionados con las decisiones aleatorias dentro del juego.

- [GameState.java](Irrgarten-Java/src/irrgarten/GameState.java): clase que contiene un constructor y getters para mostrar el estado en terminal de una serie de instancias.

- [Monster.java](Irrgarten-Java/src/irrgarten/Monster.java): clase que crea y gestiona a los monstruos del juego.

- [Player.java](Irrgarten-Java/src/irrgarten/Player.java): igual que la clase [Monster](Irrgarten-Java/src/irrgarten/Monster.java), pero en este caso con los jugadores.

- [FuzzyPlayer.java](Irrgarten-Java/src/irrgarten/FuzzyPlayer.java): jugadores "fantasma" que sustituyen a los originales una vez están muertos y resucitan. Funcionan de forma más aleatoria. Clase heredera de la clase [Player](Irrgarten-Java/src/irrgarten/Player.java)
  
- [Labyrinth.java](Irrgarten-Java/src/irrgarten/Labyrinth.java): clase que contiene la espina dorsal del juego, el laberinto donde se va a desarrollar la acción. Crea el laberinto, lo rellena, gestiona las casillas turno a turno, etc.

- [LabyrinthCharacter](Irrgarten-Java/src/irrgarten/LabyrinthCharacter.java): clase abstracta, padre de Player, Monster, y "abuela" de FuzzyPlayer. Se encarga de definir y gestionar los métodos más básicos de jugadores, fantasmas y monstruos, así como de definir de forma abstracta los métodos de ataque y defensa para los mobs mencionados, y que cada cual pueda implementarlos a su estilo mediante un @Override.

- [Game.java](Irrgarten-Java/src/irrgarten/Game.java): clase que contiene y gestiona la lógica del juego en una partida (crearla, comenzarla, terminarla, etc).

### **Irrgarten: Ruby Edition**

(parte del proyecto que me está dando un problema tras otro sin parar, seguimos en proceso)

Lista de los archivos:

- [Main.rb]

- [textUI.rb]

- [controller.rb]

- [Directions.rb]

- [GameCharacter.rb]

- [Orientation.rb]

- [Weapon.rb]

- [Shield.rb]

- [Dice.rb]

- [Monster.rb]

- [Player.rb]

- [GameState.rb]

- [Game.rb]

- [Labyrinth.rb]

- [TestP1]

TODO: remodularizar proyecto en Ruby y establecer los links correctamente para los archivos
