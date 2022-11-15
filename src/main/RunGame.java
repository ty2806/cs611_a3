/*
 *  It contains a RunGame class which manage the process of a game.
 */

package main;

import util.*;
import board.*;
import character.*;
import inventory.*;

import java.util.*;

public class RunGame {
    private final String WARRIOR_FILE = "resources/Warriors.txt";
    private final String SORCERER_FILE = "resources/Sorcerers.txt";
    private final String PALADIN_FILE = "resources/Paladins.txt";

    private final String DRAGON_FILE = "resources/Dragons.txt";
    private final String EXOSKELETONS_FILE = "resources/Exoskeletons.txt";
    private final String SPIRITS_FILE = "resources/Spirits.txt";

    private final String WEAPON_FILE = "resources/Weaponry.txt";
    private final String ARMOR_FILE = "resources/Armory.txt";
    private final String POTION_FILE = "resources/Potions.txt";
    private final String SPELL_FILE = "resources/Spells.txt";

    private Random rand;

    public RunGame()
    {
        rand = new Random();
    }

    public Board initMap(InputParser parser)
    {
        System.out.println("Please specify map size. The minimum size is 3 and the maximum size is 30.");
        parser.parseInputToInt(3, 30);
        int size = parser.getParsedInt();

        Board map = new Board(size);

        System.out.println("Please specify the percentage of inaccessible terrain in this map. The allowed range is 0 to 50.");
        parser.parseInputToInt(0, 50);
        int inaccessibleRatio = parser.getParsedInt();

        System.out.println("Please specify the percentage of market terrain in this map. The allowed range is 0 to " + (100 - inaccessibleRatio) + ".");
        parser.parseInputToInt(0, 100 - inaccessibleRatio);
        int marketRatio = parser.getParsedInt();

        map.initBoard(inaccessibleRatio*0.01, marketRatio*0.01);

        return map;
    }

    public HeroTeam initHeroes(InputParser parser, Board map, InitialLocations loc)
    {
        map.printBoard();

        HeroGenerator generator = new HeroGenerator(WARRIOR_FILE, SORCERER_FILE, PALADIN_FILE);

        generator.printLists();

        HeroTeam team = new HeroTeam();
        for (int i = 0; i < 3; i ++) {
            System.out.println("Please choose a hero to join lane" + (i+1) + ". Pick a hero by enter the number before each hero's name.");
            parser.parseInputToInt(0, generator.getWarriorList().size()+generator.getSorcererList().size()+generator.getPaladinList().size());
            int index = parser.getParsedInt();
            Hero hero;
            if (index >= generator.getWarriorList().size()+generator.getSorcererList().size()) {
                hero = generator.generateHero(index - (generator.getWarriorList().size()+generator.getSorcererList().size()), "paladin");
            }
            else if (index >= generator.getWarriorList().size()) {
                hero = generator.generateHero(index - generator.getWarriorList().size(), "sorcerer");
            }
            else {
                hero = generator.generateHero(index, "warrior");
            }

            System.out.println(hero.getName() + " joined lane" + (i+1));
            team.addMember(hero);

            switch (i) {
                case 0:
                    hero.setLocation(loc.getLane1HeroBirthplace());
                    break;
                case 1:
                    hero.setLocation(loc.getLane2HeroBirthplace());
                    break;
                case 2:
                    hero.setLocation(loc.getLane3HeroBirthplace());
                    break;
            }

            //TODO: 11/13/2022 add hero to map
        }
        return team;
    }

    public void run(Scanner userInput)
    {
        InputParser parser = new InputParser(userInput);

        Board map = initMap(parser);

        InitialLocations loc = new InitialLocations();

        HeroTeam heroes = initHeroes(parser, map, loc);

        ItemGenerator itemGenerator = new ItemGenerator(WEAPON_FILE, ARMOR_FILE, POTION_FILE, SPELL_FILE);
        MonsterGenerator monsterGenerator = new MonsterGenerator(DRAGON_FILE, EXOSKELETONS_FILE, SPIRITS_FILE);
        MonsterTeam monsters = new MonsterTeam();
        BoardSession boardSession = new BoardSession(itemGenerator, monsterGenerator, parser, map, heroes, monsters, loc);
        boardSession.runBoard();
    }
}
