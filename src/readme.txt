
## Files
---------------------------------------------------------------------------
board package: It contains all classes related to the big map.
Board: It contains the big map and methods related to move on map.
Tile: It represents a single tile on board. It contains 1 terrain and 1 possible hero team.
Terrain: An abstract terrain class. It has 3 subclasses: CommonTerrain, InaccessibleTerrain and MarketTerrain.
CommonTerrain: It has a method allowing a probability of 30% to encounter monsters.
InaccessibleTerrain: It doesn't allow player to get on.
MarketTerrain: Player is allowed to enter a market on this terrain.

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
Please specify map size. The minimum size is 3 and the maximum size is 30.
8
Please specify the percentage of inaccessible terrain in this map. The allowed range is 0 to 50.
20
Please specify the percentage of market terrain in this map. The allowed range is 0 to 80.
30
Please specify number of heroes. The minimum number is 1 and the maximum number is 3.
3
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
Please choose a hero to join your team. Pick a hero by enter the number before each hero's name.
2
Muamman_Duathall joined your team.
Please choose a hero to join your team. Pick a hero by enter the number before each hero's name.
9
Reverie_Ashels joined your team.
Please choose a hero to join your team. Pick a hero by enter the number before each hero's name.
12
Parzival joined your team.
[[Desghidorrah, 3, 300, 400, 35], [Chrysophylax, 2, 200, 500, 20], [BunsenBurner, 4, 400, 500, 45], [Natsunomeryu, 1, 100, 200, 10], [TheScaleless      , 7, 700, 600, 75], [Kas-Ethelinh, 5, 600, 500, 60], [Alexstraszan     , 10, 1000, 9000, 55], [Phaarthurnax, 6, 600, 700, 60], [D-Maleficent, 9, 900, 950, 85], [TheWeatherbe, 8, 800, 900, 80], [Igneel, 6, 600, 400, 60], [BlueEyesWhite, 9, 900, 600, 75]]
[[Cyrrollalee, 7, 700, 800, 75], [Brandobaris, 3, 350, 450, 30], [BigBad-Wolf, 1, 150, 250, 15], [WickedWitch, 2, 250, 350, 25], [Aasterinian, 4, 400, 500, 45], [Chronepsish, 6, 650, 750, 60], [Kiaransalee, 8, 850, 950, 85], [St-Shargaas, 5, 550, 650, 55], [Merrshaullk, 10, 1000, 900, 55], [St-Yeenoghu, 9, 950, 850, 90], [DocOck, 6, 600, 600, 55], [Exodia, 10, 1000, 1000, 50]]
[[Andrealphus, 2, 600, 500, 40], [Blinky, 1, 450, 350, 35], [Andromalius, 3, 550, 450, 25], [Chiang-shih, 4, 700, 600, 40], [FallenAngel, 5, 800, 700, 50], [Ereshkigall, 6, 950, 450, 35], [Melchiresas, 7, 350, 150, 75], [Jormunngand, 8, 600, 900, 20], [Rakkshasass, 9, 550, 600, 35], [Taltecuhtli, 10, 300, 200, 50], [Casper, 1, 100, 100, 50]]
+---+---+---+---+---+---+---+---+
|   | $ |   |   | X |   | $ | $ |
+---+---+---+---+---+---+---+---+
| $ |   | $ | $ | $ |   | $ |   |
+---+---+---+---+---+---+---+---+
| X |   |   | X |   | $ | X |   |
+---+---+---+---+---+---+---+---+
| X |   |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| $ |   | $ |   | X | P | X |   |
+---+---+---+---+---+---+---+---+
| $ | $ |   | X | X | $ | $ | X |
+---+---+---+---+---+---+---+---+
|   | $ |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
| $ | $ | $ |   |   | X | $ |   |
+---+---+---+---+---+---+---+---+
X = inaccessible, $ = market, P = heroes
You can control your team by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags
s
+---+---+---+---+---+---+---+---+
|   | $ |   |   | X |   | $ | $ |
+---+---+---+---+---+---+---+---+
| $ |   | $ | $ | $ |   | $ |   |
+---+---+---+---+---+---+---+---+
| X |   |   | X |   | $ | X |   |
+---+---+---+---+---+---+---+---+
| X |   |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| $ |   | $ |   | X |   | X |   |
+---+---+---+---+---+---+---+---+
| $ | $ |   | X | X | P | $ | X |
+---+---+---+---+---+---+---+---+
|   | $ |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
| $ | $ | $ |   |   | X | $ |   |
+---+---+---+---+---+---+---+---+
X = inaccessible, $ = market, P = heroes
You are near a Market! You can input M/m to enter this market
You can control your team by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags|M/m:enter market
m
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
0 PotionName:Mermaid_Tears Price:850 Level:5 Effect:100 Attributes:Health/Mana/Strength/Agility
1 PotionName:Ambrosia Price:200 Level:1 Effect:150 Attributes:revive
2 SpellName:Snow_Cannon Price:500 Level:2 Damage:650 Mana cost:250 Type:ice Quantity:8
3 ArmorName:Guardian_Angel Price:1000 Level:10 Defense:1000
4 SpellName:Ice_Blade Price:250 Level:1 Damage:450 Mana cost:100 Type:ice Quantity:10
5 SpellName:Hell_Storm Price:600 Level:3 Damage:950 Mana cost:600 Type:fire Quantity:8
6 SpellName:Arctic_Storm Price:700 Level:6 Damage:800 Mana cost:300 Type:ice Quantity:6
7 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
8 ArmorName:Wizard_Shield Price:1200 Level:10 Defense:1500
9 Weapon Name:TSwords Price:1400 Level:8 Damage:1600 Hand:2
10 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
11 SpellName:Electric_Arrows Price:550 Level:5 Damage:650 Mana cost:200 Type:lightning Quantity:6
12 PotionName:Magic_Potion Price:350 Level:2 Effect:100 Attributes:Mana
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
customer: Muamman_Duathall level:1 gold:2546
S/s:sell items in your inventory to market|B/b:buy items from market|Q/q:leave market|M/m: display map
b
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
0 PotionName:Mermaid_Tears Price:850 Level:5 Effect:100 Attributes:Health/Mana/Strength/Agility
1 PotionName:Ambrosia Price:200 Level:1 Effect:150 Attributes:revive
2 SpellName:Snow_Cannon Price:500 Level:2 Damage:650 Mana cost:250 Type:ice Quantity:8
3 ArmorName:Guardian_Angel Price:1000 Level:10 Defense:1000
4 SpellName:Ice_Blade Price:250 Level:1 Damage:450 Mana cost:100 Type:ice Quantity:10
5 SpellName:Hell_Storm Price:600 Level:3 Damage:950 Mana cost:600 Type:fire Quantity:8
6 SpellName:Arctic_Storm Price:700 Level:6 Damage:800 Mana cost:300 Type:ice Quantity:6
7 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
8 ArmorName:Wizard_Shield Price:1200 Level:10 Defense:1500
9 Weapon Name:TSwords Price:1400 Level:8 Damage:1600 Hand:2
10 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
11 SpellName:Electric_Arrows Price:550 Level:5 Damage:650 Mana cost:200 Type:lightning Quantity:6
12 PotionName:Magic_Potion Price:350 Level:2 Effect:100 Attributes:Mana
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
Enter a number before each item to buy or enter q to quit buying
1
Muamman_Duathall purchased Ambrosia(1)
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
0 PotionName:Mermaid_Tears Price:850 Level:5 Effect:100 Attributes:Health/Mana/Strength/Agility
1 SpellName:Snow_Cannon Price:500 Level:2 Damage:650 Mana cost:250 Type:ice Quantity:8
2 ArmorName:Guardian_Angel Price:1000 Level:10 Defense:1000
3 SpellName:Ice_Blade Price:250 Level:1 Damage:450 Mana cost:100 Type:ice Quantity:10
4 SpellName:Hell_Storm Price:600 Level:3 Damage:950 Mana cost:600 Type:fire Quantity:8
5 SpellName:Arctic_Storm Price:700 Level:6 Damage:800 Mana cost:300 Type:ice Quantity:6
6 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
7 ArmorName:Wizard_Shield Price:1200 Level:10 Defense:1500
8 Weapon Name:TSwords Price:1400 Level:8 Damage:1600 Hand:2
9 SpellName:Frost_Blizzard Price:750 Level:5 Damage:850 Mana cost:350 Type:ice Quantity:6
10 SpellName:Electric_Arrows Price:550 Level:5 Damage:650 Mana cost:200 Type:lightning Quantity:6
11 PotionName:Magic_Potion Price:350 Level:2 Effect:100 Attributes:Mana
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
Enter a number before each item to buy or enter q to quit buying
q
customer: Muamman_Duathall level:1 gold:2346
S/s:sell items in your inventory to market|B/b:buy items from market|Q/q:leave market|M/m: display map
q
customer: Reverie_Ashels level:1 gold:2500
S/s:sell items in your inventory to market|B/b:buy items from market|Q/q:leave market|M/m: display map
q
customer: Parzival level:1 gold:2500
S/s:sell items in your inventory to market|B/b:buy items from market|Q/q:leave market|M/m: display map
q
+---+---+---+---+---+---+---+---+
|   | $ |   |   | X |   | $ | $ |
+---+---+---+---+---+---+---+---+
| $ |   | $ | $ | $ |   | $ |   |
+---+---+---+---+---+---+---+---+
| X |   |   | X |   | $ | X |   |
+---+---+---+---+---+---+---+---+
| X |   |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| $ |   | $ |   | X |   | X |   |
+---+---+---+---+---+---+---+---+
| $ | $ |   | X | X | P | $ | X |
+---+---+---+---+---+---+---+---+
|   | $ |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
| $ | $ | $ |   |   | X | $ |   |
+---+---+---+---+---+---+---+---+
X = inaccessible, $ = market, P = heroes
You are near a Market! You can input M/m to enter this market
You can control your team by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags|M/m:enter market
s
+---+---+---+---+---+---+---+---+
|   | $ |   |   | X |   | $ | $ |
+---+---+---+---+---+---+---+---+
| $ |   | $ | $ | $ |   | $ |   |
+---+---+---+---+---+---+---+---+
| X |   |   | X |   | $ | X |   |
+---+---+---+---+---+---+---+---+
| X |   |   |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| $ |   | $ |   | X |   | X |   |
+---+---+---+---+---+---+---+---+
| $ | $ |   | X | X | $ | $ | X |
+---+---+---+---+---+---+---+---+
|   | $ |   |   |   | P |   |   |
+---+---+---+---+---+---+---+---+
| $ | $ | $ |   |   | X | $ |   |
+---+---+---+---+---+---+---+---+
X = inaccessible, $ = market, P = heroes
You can control your team by following commands
W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags