# ***Proyecto de Programación y Diseño Orientado a Objetos***

Proyecto para _Programación Dirigida y Orientada a Objetos (PDOO)_, del primer cuatrimestre del segundo año de carrera en Ingeniería Informática en la ETSIIT.

Se trata de crear un videojuego de rol por turnos (llamado **Irrgarten**), el cual consiste en la generación de un tablero donde se dispone de paredes, monstruos y jugadores generados y esparcidos de manera aleatoria por dicho tablero. 

También se dispone de una casilla de salida, que resulta ser el objetivo final de los jugadores puesto que, de moverse y caer encima de ésta, el juego finaliza con el respectivo jugador que haya caído encima ganando la partida.

El juego es programado de forma independiente en dos lenguajes: Java y Ruby.

## *Status actual de las prácticas*

- P1: [Terminada]
- P2: [Terminada]
- P3: Terminada exitosamente en Java, con severos bugs aún en Ruby
- P4: Empezada en Java
- P5: ...

### **Bugs en Java**

- Al generar una instancia de juego de 1 solo jugador en el programa principal, debería ser del estilo: new Game(1). Sin embargo, sólo funciona poniendo new Game(0) [NO SOLUCIONADO]
  
- Cuando juegan varios jugadores, solo juega uno. [SOLUCIONADO]
  
- Al empezar la P4, ya no se muestra el nº del jugador en el tablero [SOLUCIONADO]

- A la espera de crear e implementar la clase FuzzyPlayer de la P4, el jugador que está muerto y resucita ya no es capaz de moverse por el tablero [SOLUCIONADO]

- Parece ser que tras empezar la P4 y establecer las herencias Player -> LabyrinthCharacter y Monster -> LabyrinthCharacter, los monstruos ganan absolutamente todos los combates que juegan los jugadores al caer en sus casillas. [SOLUCIONADO]


### **Bugs en Ruby**

(A la espera de empezar la P4 en Ruby)

- [Véase la issue abierta]

### **Irrgarten: Java Edition**

Lista y descripción de los archivos:

- [Main.java](Java/src/main/Main.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (usado para depurar).

- [Irrgarten.java](Java/src/main/Irrgarten.java): programa principal donde se inicia el juego en torno a una instancia de la clase Game, un TextUI y un controlador (ejecutable por el usuario).

- [TextUI.java](Java/src/UI/TextUI.java): vista textual del estado del juego

- [Controller.java](Java/src/controller/Controller.java): controlador para los jugadores, usando las teclas WASD.

- [TestP1.java](Java/test/TestP1.java): test de la Práctica 1.

- [Directions.java](Java/src/irrgarten/Directions.java): tipo enumerado para las direcciones posibles de movimiento para los jugadores en partida

- [GameCharacter.java](Java/src/irrgarten/GameCharacter.java): tipo enumerado para denominar a jugadores o monstruos en el juego.

- [Orientation.java](Java/src/irrgarten/Orientation.java): tipo enumerado para distinguir la orientación vertical u horizontal en situaciones como, por ejemplo, la configuración de las paredes dentro del laberinto.

- [Weapon.java](Java/src/irrgarten/Weapon.java): clase Arma. Crea objetos tipo Arma para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda atacar en el juego.

- [Shield.java](Java/src/irrgarten/Shield.java): clase Escudo. Crea objetos tipo Escudo para el jugador y contiene y gestiona los métodos necesarios para que el jugador pueda defenderse en el juego.

- [Dice.java](Java/src/irrgarten/Dice.java): clase que contiene métodos relacionados con las decisiones aleatorias dentro del juego.

- [GameState.java](Java/src/irrgarten/GameState.java): clase que contiene un constructor y getters para mostrar el estado en terminal de una serie de instancias.

- [Monster.java](Java/src/irrgarten/Monster.java): clase que crea y gestiona a los monstruos del juego.

- [Player.java](Java/src/irrgarten/Player.java): igual que la clase [Monster](Java/src/irrgarten/Monster.java), pero en este caso con los jugadores.

- [Labyrinth.java](Java/src/irrgarten/Labyrinth.java): clase que contiene la espina dorsal del juego, el laberinto donde se va a desarrollar la acción. Crea el laberinto, lo rellena, gestiona las casillas turno a turno, etc.

- [Game.java](Java/src/irrgarten/Game.java): clase que contiene y gestiona la lógica del juego en una partida (crearla, comenzarla, terminarla, etc).

### **Irrgarten: Ruby Edition**

[La descripción de los archivos viene a ser la misma que en Java, después de todo]

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
