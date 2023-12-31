# ***Object Oriented Programming and Design Project***

Project for _Directed and Object-Oriented Programming (PDOO)_, from the first semester of the second year of the Computer Engineering degree at ETSIIT.

It is about creating a turn-based role-playing video game (called **Irrgarten**), which consists of generating a board where there are walls, monsters and players generated and scattered randomly on said board.

There is also a starting square, which turns out to be the final objective of the players since, if they move and fall on it, the game ends with the respective player who has fallen on it winning the game.

The game is programmed independently in two languages: Java and Ruby.


## *Current status of internships*

- P1: [DONE]
- P2: [DONE]
- P3: [DONE]
- P4: [DONE]
- Q5: [DONE] (only done in Java)

[FINISHED PROJECT]


## *Bugs in Java*

- When generating a single player game instance in the main program, it should be of the style: new Game(1). However, it only works by setting new Game(0)
    
- When multiple players play, only one plays. [SOLVED]
  
- When starting P4, the player's number is no longer shown on the board [SOLVED]

- While waiting to create and implement the P4 FuzzyPlayer class, the player who is dead and resurrected is no longer able to move around the board [SOLVED]

- It seems that after starting P4 and establishing the inheritances Player -> LabyrinthCharacter and Monster -> LabyrinthCharacter, the monsters win absolutely all the battles that the players play by landing on their spaces. [SOLVED]


## *Bugs in Ruby*

-When moving a player, the position of the maze in which he was placed prior to his movement is not updated correctly [SOLVED]
  
- When starting a game, players sometimes appear overlapping with others in the same position in the maze [SOLVED]

- Monsters spawn on top of blocks and overlap them [SOLVED]

- Players cannot move through the last row of the maze as it crashes [SOLVED]

- game_state does not show the current player's turn [FIXED]

- Battles caused a crash caused by a Nil-Class error in the sum_weapons and sum_shields methods [SOLVED]

- The blocks are useless, the player passes over them as if they were simple empty squares [SOLVED]

- Players, their attributes, weapons and shields appear as a list of memory addresses instead of a normal String like in the Java part

- FuzzyPlayers are instantiated correctly, but for some strange reason they don't rename "Player #..." to "FuzzyPlayer #..." correctly.

- Monsters are sometimes spawned and added to the maze above the exit space [FIXED]

- Sometimes when starting a game a player appears outside the maze with a null associated position [row][col], which leads to an instant crash when trying to move it [HALF-SOLVED] --> The player who instantiates himself "wrongly" It appears by default in the [0,0] box, but its number does not appear until you decide to move it around the map.


## *Irrgarten: Java Edition*

List and description of files:

- [`Main.java`](Irrgarten-Java/src/main/Main.java): main program where the game is started around an instance of the Game class, a TextUI and a controller (used for debugging).

- [`Irrgarten.java`](Irrgarten-Java/src/main/Irrgarten.java): main program where the game is started around an instance of the Game class, a TextUI and a controller (executable by the user) .

- [`MainGUI.java`](Irrgarten-Java/src/main/MainGUI.java): main game program, but this time with a graphical interface and controller created from the UI interface.

- [`UI.java`](Irrgarten-Java/src/UI/UI.java): game interface.

- [`GraphicalUI.java`](Irrgarten-Java/src/UI/GraphicalUI.java): graphical interface of the game, which implements the methods defined in UI, among others.

- [`TextUI.java`](Irrgarten-Java/src/UI/TextUI.java): textual view (in terminal) of the game state

- [`Controller.java`](Irrgarten-Java/src/controller/Controller.java): controller for players, using WASD keys.

- [`Cursors.java`](Irrgarten-Java/src/controller/Cursors.java): graphical game controller. It opens as a warning window with 4 buttons, programmed so that, when clicking on any of them, one of the movement directions is returned and the player moves around the board through the nextMove() method of the class [`GraphicalUI`](Irrgarten-Java/src/UI/GraphicalUI.java).

- [`TestP1.java`](Irrgarten-Java/test/TestP1): Practice 1 test.

- [`Directions.java`](Irrgarten-Java/src/irrgarten/Directions.java): Enum for the possible directions of movement for players in the game

- [`GameCharacter.java`](Irrgarten-Java/src/irrgarten/GameCharacter.java): Enum for naming players or monsters in the game.

- [`Orientation.java`](Irrgarten-Java/src/irrgarten/Orientation.java): Enum for distinguishing vertical or horizontal orientation in situations such as the configuration of walls within the maze.

- [`CardDeck<T>.java`](Irrgarten-Java/src/irrgarten/CardDeck.java): Parametric abstract class dedicated to being the parent class and managing the player's random combat reward decks for shields and weapons.

- [`WeaponCardDeck.java`](Irrgarten-Java/src/irrgarten/WeaponCardDeck.java): daughter class of CardDeck<Weapon>, and is responsible for creating and managing the shuffling of Weapon type cards for the combat rewards of the player.

- [`ShieldCardDeck.java`](Irrgarten-Java/src/irrgarten/ShieldCardDeck.java): daughter class of CardDeck<Shield>, and is responsible for creating and managing the shuffling of Shield type cards for the combat rewards of the player.

- [`Weapon.java`](Irrgarten-Java/src/irrgarten/Weapon.java): Weapon class. Creates Weapon type items for the player and contains and manages the methods necessary for the player to attack in the game.

- [`Shield.java`](Irrgarten-Java/src/irrgarten/Weapon.java): Shield class. Creates Shield-type objects for the player and contains and manages the methods necessary for the player to defend themselves in the game.

- [`Dice.java`](Irrgarten-Java/src/irrgarten/Dice.java): class that contains methods related to random decisions within the game.

- [`GameState.java`](Irrgarten-Java/src/irrgarten/GameState.java): class that contains a constructor and getters to display the terminal state of a series of instances.

- [`Monster.java`](Irrgarten-Java/src/irrgarten/Monster.java): class that creates and manages the game's monsters.

- [`Player.java`](Irrgarten-Java/src/irrgarten/Player.java): same as the class [`Monster`](Irrgarten-Java/src/irrgarten/Monster.java), but in this case with Players.

- [`FuzzyPlayer.java`](Irrgarten-Java/src/irrgarten/FuzzyPlayer.java): "ghost" players that replace the originals once they are dead and are resurrected. They work more randomly. Class inheriting from the class [`Player`](Irrgarten-Java/src/irrgarten/Player.java)
  
- [`Labyrinth.java`](Irrgarten-Java/src/irrgarten/Labyrinth.java): class that contains the backbone of the game, the labyrinth where the action will take place. Create the maze, fill it, manage the squares turn by turn, etc.

- [`LabyrinthCharacter`](Irrgarten-Java/src/irrgarten/LabyrinthCharacter.java): abstract class, parent of Player, Monster, and "grandmother" of FuzzyPlayer. It is responsible for defining and managing the most basic methods of players, ghosts and monsters, as well as abstractly defining the attack and defense methods for the aforementioned mobs, and that everyone can implement them in their own style using an @Override.

- [`Game.java`](Irrgarten-Java/src/irrgarten/Game.java): class that contains and manages the game logic in a game (create it, start it, end it, etc.).


## *Irrgarten: Ruby Edition*

The description of the files is the same as in the Java part since the project is the same for both languages. However, given the limitations of Ruby, it is not possible to create the CardDeck classes (and their respective daughters ShieldCardDeck and WeaponCardDeck), as well as define graphical interfaces as occurs in Java in Practice 5.

File list:

- [`main.rb`](Irrgarten-Ruby/main/main.rb): main program to run the game.

- [`textUI.rb`](Irrgarten-Ruby/UI/textUI.rb): textual view (in terminal) of the game state.

- [`controller.rb`](Irrgarten-Ruby/controller/controller.rb): WASD controller for players in the game.

- [`directions.rb`](Irrgarten-Ruby/irrgarten/directions.rb): module in charge of creating and managing the Direction type variables for the players, acting as enumerators.

- [`game_character.rb`](Irrgarten-Ruby/irrgarten/game_character.rb): module in charge of creating and managing the Character type variables in the game, acting as enumerators.

- [`orientation.rb`](Irrgarten-Ruby/irrgarten/orientation.rb): module that acts as an enum, responsible for creating and managing what is related to Orientation type variables for, for example, the creation and placement of blocks on the map.

- [`weapon.rb`](Irrgarten-Ruby/irrgarten/weapon.rb): Weapon class, heir to [`combat_element`](Irrgarten-Ruby/irrgarten/combat_element.rb). Create and manage, based on the parent, the weapons of each player in combat throughout the game.

- [`shield.rb`](Irrgarten-Ruby/irrgarten/shield.rb): Shield class, heir to [`combat_element`](Irrgarten-Ruby/irrgarten/combat_element.rb). Create and manage, based on the parent, the shields of each player in combat throughout the game.

- [`dice.rb`](Irrgarten-Ruby/irrgarten/dice.rb): Dice class, in charge of management of random decisions during the game.

- [`monster.rb`](Irrgarten-Ruby/irrgarten/monster.rb): Monster class, heir to [`labyrinth_character`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb). Create and manage monsters in the game.

- [`player.rb`](Irrgarten-Ruby/irrgarten/player.rb): Player class, heir to [`combat_element`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb). Create and manage players in the game.

- [`game_state.rb`](Irrgarten-Ruby/irrgarten/game_state.rb): class responsible for creating and displaying the state of the game using Strings turn by turn.

- [`game.rb`](Irrgarten-Ruby/irrgarten/game.rb): Game class. Create the game and manage the methods related to its execution.

- [`labyrinth.rb`](Irrgarten-Ruby/irrgarten/labyrinth.rb): Labyrinth class. Create the skeleton of the game, the labyrinth where the action will take place.

- [`labyrinth_character.rb`](Irrgarten-Ruby/irrgarten/labyrinth_character.rb): class in charge of managing the most basic methods of both players and monsters. In Java it is abstract, but not in Ruby, since there is no such type of class in this language.

- [`combat_element.rb`](Irrgarten-Ruby/irrgarten/combat_element.rb): class in charge of managing the most basic methods of both weapons and shields. In Java it is abstract, but not in Ruby, since there is no such type of class in this language.

- [`fuzzy_player.rb`](Irrgarten-Ruby/irrgarten/fuzzy_player.rb): class in charge of creating "ghost players" when a player dies during the game, and replacing them when they are resurrected. Heir to the class [`player`](Irrgarten-Ruby/irrgarten/player.rb).

- [`testP1`](Irrgarten-Ruby/test/test_p1.rb): Practice 1 test.
