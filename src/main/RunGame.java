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

    public RunGame() {
        rand = new Random();
    }

    public LegendsOfValorBoard initMap(InputParser parser) {

        LegendsOfValorBoard map = new LegendsOfValorBoard(8);

        //map.initBoard(inaccessibleRatio * 0.01, marketRatio * 0.01);
        map.initBoard(0, 0);
        System.out.println("Printing map\n\n" + map.toString());
        return map;
    }

    public HeroTeam initHeroes(InputParser parser, LegendsOfValorBoard map, InitialLocations loc) {

        HeroGenerator generator = new HeroGenerator(WARRIOR_FILE, SORCERER_FILE, PALADIN_FILE);

        generator.printLists();

        HeroTeam team = new HeroTeam();
        for (int i = 0; i < 3; i++) {
            System.out.println("Please choose a hero to join lane" + (i + 1) + ". Pick a hero by enter the number before each hero's name.");
            parser.parseInputToInt(0, generator.getWarriorList().size() + generator.getSorcererList().size() + generator.getPaladinList().size());
            int index = parser.getParsedInt();
            Hero hero;
            if (index >= generator.getWarriorList().size() + generator.getSorcererList().size()) {
                hero = generator.generateHero(index - (generator.getWarriorList().size() + generator.getSorcererList().size()), "paladin", i + 1);
            } else if (index >= generator.getWarriorList().size()) {
                hero = generator.generateHero(index - generator.getWarriorList().size(), "sorcerer", i + 1);
            } else {
                hero = generator.generateHero(index, "warrior", i + 1);
            }

            System.out.println(hero.getName() + " joined lane" + (i + 1));
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

            map.addHero(hero);

            System.out.println(map);
        }
        return team;
    }

    public void run(Scanner userInput) {
        InputParser parser = new InputParser(userInput);

        LegendsOfValorBoard map = initMap(parser);

        InitialLocations loc = new InitialLocations();

        HeroTeam heroes = initHeroes(parser, map, loc);

        ItemGenerator itemGenerator = new ItemGenerator(WEAPON_FILE, ARMOR_FILE, POTION_FILE, SPELL_FILE);
        MonsterGenerator monsterGenerator = new MonsterGenerator(DRAGON_FILE, EXOSKELETONS_FILE, SPIRITS_FILE);
        MonsterTeam monsters = new MonsterTeam();
        System.exit(0);
        //BoardSession boardSession = new BoardSession(itemGenerator, monsterGenerator, parser, map, heroes, monsters, loc);
        //boardSession.runBoard();
    }
}
