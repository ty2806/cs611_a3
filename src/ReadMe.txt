## Names
----------------------------------------------------
Tian Ye
Toshniwal Dhruv	U28189255

## Files
---------------------------------------------------------------------------
board package: It contains all classes related to the big map.
Board: It contains the big map and methods related to move on map.
Tile: It represents a single tile on board. It contains 1 terrain and 1 possible hero team.
Terrain: An abstract terrain class. It has 3 subclasses: CommonTerrain, InaccessibleTerrain and MarketTerrain.
CommonTerrain: It has a method allowing a probability of 30% to encounter monsters.
InaccessibleTerrain: It doesn't allow player to get on.
MarketTerrain: Player is allowed to enter a market on this terrain.
InitialLocations: Class stores birth locations of the heroes for recall and rebirth access
LegendsOfValorBoard: It contains the map of the Legends of Valor and all methods related to move, print and initialise board
LOVBushCell: Represents a Bush cell on the map
LOVCaveCell: Represents a Cave cell on the map
LOVHeroNexus: Represents a Hero Nexus cell on the map
LOVInaccessibleCell: Represents all inaccessible cells on the map
LOVKoulouCell: Represents a Koulou cell on the map
LOVMonsterNexus: Represents a Monster Nexus cell on the map
LOVPlainsCell: Represents all walkable tiles with no buff
Move: Class to move the hero and monster and check restriction
Recall: Class to provide specific restriction to special move recall
RegularMove: Class to provide specific restriction of regular move
specificMove: interface for all move types
Teleport: Class to provide specific restriction of teleport type move


character package: It contains all heros and monsters classes.
Hero: An abstract hero class. It contains all methods necessary for all heroes.
Warrior, Sorcerer, and Paladin: 3 subclasses of Hero. they invoke level up methods with different favored attributes.
LevelUpPolicy: It contains all formulae needed to level up all kinds of attributes of a hero.
HeroTeam: It represents a list of heroes.
HeroGenerator: A factory class responsible for generate heroes by reading data files.
Monster: An abstract monster class. It contains all methods necessary for all heroes.
Dragon, Exoskeleton, and Spirit: 3 subclasses of monsters. they invoke level up methods with different favored attributes.
LevelPolicy: It contains all formulae needed to calculate all kinds of attributes of a monster for each level.
MonsterGenerator: A factory class responsible for generate monsters by reading data files.

inventory package: It contains hero's inventory, equipment and all items.
Inventory: It represents a hero's inventory. It contains a list of items.
Equipment: It contains 1 weapon slot and 1 armor slot of a hero.
Item: An abstract item class. It contains all methods necessary for all items.
Weapon, Armor, Potion, and Spell: They are subclasses of Item. They contain their own specific properties and methods for different item types.
ItemGenerator: A factory class responsible for generate items by reading data files.

market package: It contains classes needed for market function.
Market: It represents a market and contains methods needed for heroes to trade.

combat package: It contains classes needed for combat function.
Combat: It represents a combat between heroes and monsters.
CombatBoard: It represents a battlefield to combat on.
CombatPolicy: It contains all formulae to calculate battle actions.

util package: It contains useful utility classes.
DataParser: It contains a static method to read txt files of all kinds of in-game objects and return arraylists of strings.
InputParser: It reads terminal inputs and checks their validity and return a proper java data type.

main package: It contains all classes responsible run the game and interact with terminal commands.
MainMenu: It contains a MainMenu class which controls game starting.
Main: The Main class starts the program
RunGame: It contains a RunGame class which manage the process of a game.
BoardSession: It manages player's behavior on the big map.
InfoSession: It manages info display.
InventorySession: It manages inventory actions such as use items in inventory.
MarketSession: It manages all interactions in a market.
CombatSession: It manages all interactions in a combat.

resources: This folder contains all txt files recording datas of characters and items.



## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "cs611_a3/src" after unzipping the files
2. Run the following instructions:
javac main/*.java
java main.Main

## Input/Output Example
---------------------------------------------------------------------------


Welcome to console game Monsters and Heros!
Printing map

V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X           
H   H   X   H   H   X   H   H   

No.   Name   HP   MP   strength   agility   dexterity   starting money   starting experience
Warriors
0 [Gaerdal_Ironhand, 100, 100, 700, 500, 600, 1354, 7]
1 [Sehanine_Monnbow, 100, 600, 700, 800, 500, 2500, 8]
2 [Muamman_Duathall, 100, 300, 900, 500, 750, 2546, 6]
3 [Flandal_Steelskin, 100, 200, 750, 650, 700, 2500, 7]
4 [Undefeated_Yoj, 100, 400, 800, 400, 700, 2500, 7]
5 [Eunoia_Cyn, 100, 400, 700, 800, 600, 2500, 6]
Sorcerers
6 [Rillifane_Rallathil, 100, 1300, 750, 450, 500, 2500, 9]
7 [Segojan_Earthcaller, 100, 900, 800, 500, 650, 2500, 5]
8 [Reign_Havoc, 100, 800, 800, 800, 800, 2500, 8]
9 [Reverie_Ashels, 100, 900, 800, 700, 400, 2500, 7]
10 [Kalabar, 100, 800, 850, 400, 600, 2500, 6]
11 [Skye_Soar, 100, 1000, 700, 400, 500, 2500, 5]
Paladins
12 [Parzival, 100, 300, 750, 650, 700, 2500, 7]
13 [Sehanine_Moonbow, 100, 300, 750, 700, 700, 2500, 7]
14 [Skoraeus_Stonebones, 100, 250, 650, 600, 350, 2500, 4]
15 [Garl_Glittergold, 100, 100, 600, 500, 400, 2500, 5]
16 [Amaryllis_Astra, 100, 500, 500, 500, 500, 2500, 5]
17 [Caliber_Heist, 100, 400, 400, 400, 400, 2500, 8]
Please choose a hero to join lane 1. Pick a hero by enter the number before each hero's name.
1
Sehanine_Monnbow joined lane1
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X           
HH1 H   X   H   H   X   H   H   

Please choose a hero to join lane 2. Pick a hero by enter the number before each hero's name.
2
Muamman_Duathall joined lane2
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X           
HH1 H   X   HH2 H   X   H   H   

Please choose a hero to join lane 3. Pick a hero by enter the number before each hero's name.
3
Flandal_Steelskin joined lane3
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X           
HH1 H   X   HH2 H   X   HH3 H   

Now it is hero Sehanine_Monnbow's turn.
V  MV   X   V  MV   X   V  MV   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X           
HH1 H   X   HH2 H   X   HH3 H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
w
hero receives a strength bonus on current terrain.
Now it is hero Muamman_Duathall's turn.
V  MV   X   V  MV   X   V  MV   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
KH1     X       B   X           
H   H   X   HH2 H   X   HH3 H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
d
Now it is hero Flandal_Steelskin's turn.
V  MV   X   V  MV   X   V  MV   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
KH1     X       B   X           
H   H   X   H   HH2 X   HH3 H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
a
Illegal move, please try again!
This is not a valid move. The destination is unreachable. Please try another direction.
Now it is hero Flandal_Steelskin's turn.
V  MV   X   V  MV   X   V  MV   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
KH1     X       B   X           
H   H   X   H   HH2 X   HH3 H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
w



monster move from [0, 0] to [0, 1]
monster move from [3, 0] to [3, 1]
monster move from [6, 0] to [6, 1]
Now it is hero Sehanine_Monnbow's turn.
V   V   X   V   V   X   V   V   
B  M    X   C  MC   X   B  MK   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
KH1     X       B   X    H3     
H   H   X   H   HH2 X   H   H   

You can control your hero by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
t
Please enter the row and column you wish to teleport.
6
7
Now it is hero Muamman_Duathall's turn.
V   V   X   V   V   X   V   V   
B  M    X   C  MC   X   B  MK   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       B   X    H3  H1 
H   H   X   H   HH2 X   H   H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
w
hero receives an dexterity bonus on current terrain.
Now it is hero Flandal_Steelskin's turn.
V   V   X   V   V   X   V   V   
B  M    X   C  MC   X   B  MK   
C       X   K   B   X   B   B   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X       C   
K       X       BH2 X    H3  H1 
H   H   X   H   H   X   H   H   

You can control your hero by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
w



monster move from [0, 1] to [0, 2]
monster move from [3, 1] to [3, 2]
monster move from [6, 1] to [6, 2]
Now it is hero Sehanine_Monnbow's turn.
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C  M    X   K  MB   X   B  MB   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X    H3 C   
K       X       BH2 X        H1 
H   H   X   H   H   X   H   H   

You can control your hero by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
r
Now it is hero Muamman_Duathall's turn.
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C  M    X   K  MB   X   B  MB   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X    H3 C   
K       X       BH2 X           
HH1 H   X   H   H   X   H   H   

You can control your hero by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
a
Now it is hero Flandal_Steelskin's turn.
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C  M    X   K  MB   X   B  MB   
        X       B   X   C       
B       X           X   K   K   
    C   X   C   C   X    H3 C   
K       X    H2 B   X           
HH1 H   X   H   H   X   H   H   

You can control your hero by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags
s



monster move from [0, 2] to [0, 3]
monster move from [3, 2] to [3, 3]
monster move from [6, 2] to [6, 3]
Now it is hero Sehanine_Monnbow's turn.
V   V   X   V   V   X   V   V   
B       X   C   C   X   B   K   
C       X   K   B   X   B   B   
   M    X      MB   X   C  M    
B       X           X   K   K   
    C   X   C   C   X       C   
K       X    H2 B   X    H3     
HH1 H   X   H   H   X   H   H   

You are near a Market! You can input M/m to enter this market
You can control your hero by following commands
M/m:enter marketW/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags

