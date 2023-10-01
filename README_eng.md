# Irrgarten

Project for Directed and Object-Oriented Programming, second year of studies. It's about creating a video game
role in both Java and Ruby. The game is about escaping a maze full of monsters

================================================================== ================================================================== ================
                                               CHANGELOG PRACTICE 1
================================================================== ================================================================== ================

In Practice 1 of the project we are asked to create the following files in Java and Ruby languages:


- Enums.java and Enums.rb:

They contain enumerated data types (Directions, Orientation and GameCharacter). The first one creates
the directions a player or monster can move around the board (left,
right, up, down), the second are vertical or horizontal orientations, and the last
identifies a player or a monster.


- Weapon Class:

This class uses the private instance attributes "power" and "uses" to define weapons as
object of the game. Contains query methods and modifiers for each of the two attributes, an "attack" method,
which returns attack power for each use of a weapon until they are reduced to zero; a method
"to_string" which returns the value of the weapon's attack power and remaining uses, and a boolean method called
discard, which makes a call to the Dice class and a method that allows a weapon to be discarded based on the number of uses
remaining.


- Shield Class:

It contains two private instance attributes called "protection" and "uses". They create shields as
object of the game. Apart from containing a consultant and a modifier for each attribute, we have a method similar to
"attack" of the Weapon class, called "protect", which also defines the protection given by the shield and reduces uses
remaining at 1 each time said item is called to use. It also contains a "to_string" and another "discard" method, which
They function in the same way as their Weapon class counterparts.


- Class Says:

It is the most important class of the practice. It contains 6 private instance attributes of type int, and 3 of type float.
In these methods the following constants are defined: MAX_USES (maximum number of uses that an item can have),
MAX_ATTACK (maximum attack value applicable to a weapon), MAX_SHIELD (maximum protection value applicable to a
shield), WEAPONS_REWARD, SHIELD_REWARD, HEALTH_REWARD (maximum number of weapons, shields or health that is collected by the
player upon winning a combat).

There is also a private instance attribute that creates random numbers to determine decision making by
part of the game in the methods that will be explained below. In the Java part, two methods are created
nextInt() and nextFloat() which creates random integers between 0 and an applied maximum value (to also contain
said maximum value in the interval will be "max+1".

Then, and in both languages, the class contains the randomPos method, which returns a random position value of
row or column on the board; the whoStarts method, which returns the ID of the player who starts the game (the first
player is 0 and the last one is defined by the parameter "nplayers"). We then have two methods randomStrength and
randomIntelligence, which create a random floating point value between 0 and MAX_STRENGTH or MAX_INTELLIGENCE (strength or
intelligence).

resurrectPlayer method: returns a boolean value called "resurrect" in which if a generated probability value
randomly is less than or equal to the constant RESURRECT_PROB, we resurrect a player. If not, he does not resurrect.

Reward methods (weapons, shields and health): return the number of weapons, shield or health that a player gains when winning
a bout randomly between 0 and the maximum constant value of these (int).

usesLeft method: returns the number of uses that are randomly assigned to a weapon or shield, between 0 and
the maximum number of uses allowed for an item.

intensity method: returns the amount of competition applied between 0 and a "competence" (float) parameter.

discardElement Method: Returns "true" or "false" depending on whether it uses 0 or a random value generated between 0 and 1.
less or not than the probability value calculated based on the number of remaining uses. Through this method,
calls to the Dice class from the Shield and Weapon classes.


- GameState class:

Contains the private instance attributes labyrinthv, players, monsters (these 3 of type String), currentPlayer
(int), winner (boolean) and log (String). It contains a constructor that initializes each of the attributes, and a method
consultant for each of them as well.
The class itself is created with the purpose of storing a representation of the complete state of the game: the state
of the labyrinth, the state of the players, the state of the monsters, the index of the player whose turn it is, a
indicator for whether there is already a winner and an additional attribute to save to a string events
interesting things that have happened since the previous turn.
