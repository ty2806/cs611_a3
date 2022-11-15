/*
 *  It manages player's behavior on the big map.
 */


package main;

import board.Board;
import board.InitialLocations;
import character.*;
import combat.Combat;
import combat.CombatPolicy;
import inventory.ItemGenerator;
import util.InputParser;

import java.util.ArrayList;

public class BoardSession {
    private ItemGenerator itemGenerator;
    private MonsterGenerator monsterGenerator;
    private InputParser parser;
    private Board map;
    private HeroTeam heroes;
    private MonsterTeam monsters;
    private InitialLocations loc;
    private int roundNum;
    private final int respawnRound = 8;

    public BoardSession(ItemGenerator itemGenerator, MonsterGenerator monsterGenerator, InputParser parser, Board map, HeroTeam heroes, MonsterTeam monsters, InitialLocations loc) {
        this.itemGenerator = itemGenerator;
        this.monsterGenerator = monsterGenerator;
        this.parser = parser;
        this.map = map;
        this.heroes = heroes;
        this.monsters = monsters;
        this.loc = loc;
        roundNum = 0;
    }

    public void runBoard()
    {
        InfoSession infoSession = new InfoSession();
        MarketSession marketSession = new MarketSession(itemGenerator);
        InventorySession inventorySession = new InventorySession();
        Combat combat = new Combat();

        while (true) {
            if (roundNum % respawnRound == 0) {
                respawnMonsters();
            }
            respawnHeroes();
            heroesTurn(infoSession, marketSession, inventorySession, combat);


            combat.roundEndRecovery(heroes);
            roundNum ++;
        }
    }

    public void heroesTurn(InfoSession infoSession, MarketSession marketSession, InventorySession inventorySession, Combat combat)
    {
        for (int i = 0; i < 3; i ++) {
            Hero hero = heroes.getMember(i);
            ArrayList<Integer> enemyInRange = searchEnemy(hero.getLocation(), "monster");

            chooseActionLoop:
            while (true) {
                String command = mapControl(hero.getLocation(), enemyInRange.size()>0);
                switch (command) {
                    case "q":
                        exitGame();
                        break;
                    case "i":
                        infoSession.showInfo(parser, heroes);
                        break;
                    case "m":
                        marketSession.runMarket(parser, heroes, map);
                        break;
                    case "b":
                        boolean successUse = inventorySession.openInventory(parser, hero, heroes, monsters, enemyInRange, combat);
                        if (successUse) {
                            break chooseActionLoop;
                        }
                        break;
                    case "k":
                        heroAttack(hero, enemyInRange, combat);
                        break chooseActionLoop;
                    default:
                        boolean validMove = moveHero(command, hero);
                        if (validMove) {

                            break chooseActionLoop;
                        }
                        break;
                }
            }
        }
    }

    public ArrayList<Integer> searchEnemy(int[] location, String pattern)
    {
        ArrayList<Integer> indexes = new ArrayList<>();
        if (pattern.equals("monster")) {
            for (int i = 0; i < monsters.getTeamSize(); i ++) {
                if (map.isInAttackRange(location, monsters.getMember(i).getLocation())) {
                    indexes.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < 3; i ++) {
                if (map.isInAttackRange(location, heroes.getMember(i).getLocation())) {
                    indexes.add(i);
                }
            }
        }
        return indexes;
    }

    public String mapControl(int[] location, boolean hasEnemy)
    {
        map.printBoard();
        int x = location[0];
        int y = location[1];

        if (hasEnemy) {
            System.out.println("You are near a monster! You can input K/k to attack it or cast a spell in your bag by input B/b ");
        }

        if (map.isMarket(x, y)) {
            System.out.println("You are near a Market! You can input M/m to enter this market");
        }

        System.out.println("You can control your team by following commands");
        if (hasEnemy) {
            System.out.print("K/k:attack enemy");
        }
        if (map.isMarket(x, y)) {
            System.out.println("W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags|M/m:enter market");
        }
        else {
            System.out.println("W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags");
        }

        String[] allowedWords = {"Q", "q", "W", "w", "A", "a", "S", "s", "D", "d", "I", "i", "B", "b", "M", "m", "K", "k"};
        parser.parseInputToString(allowedWords);
        return parser.getParsedString().toLowerCase();
    }

    public void heroAttack(Hero hero, ArrayList<Integer> enemyInRange, Combat combat)
    {
        int index = chooseEnemy(parser, enemyInRange, monsters);

        Monster monster = monsters.getMember(index);
        combat.heroAttack(hero, monster);
        if (monster.getHp() <= 0) {
            removeMonster(index, monsters);
            System.out.println(monster.getName() + " is slayed by " + hero.getName());
            combat.distributeReward(hero, monster);
        }
    }

    public boolean moveHero(String command, Hero hero)
    {
        int newX = hero.getLocation()[0];
        int newY = hero.getLocation()[1];
        switch (command) {
            case "w":
                newY -= 1;
                break;
            case "s":
                newY += 1;
                break;
            case "a":
                newX -= 1;
                break;
            case "d":
                newX += 1;
                break;
        }
        if (map.isValidPosition(newX, newY)) {
            map.move(hero.getLocation()[0], hero.getLocation()[1], newX, newY);
            hero.setLocation(new int[]{newX, newY});
            return true;
        }
        else {
            System.out.println("This is not a valid move. The destination is unreachable. Please try another direction.");
            return false;
        }
    }

    public void monstersTurn(Combat combat)
    {
        for (int i = 0; i < monsters.getTeamSize(); i ++) {
            Monster monster = monsters.getMember(i);
            ArrayList<Integer> enemyInRange = searchEnemy(monster.getLocation(), "hero");

            if (enemyInRange.size() > 0) {
                Hero hero = heroes.getMember(enemyInRange.get(0));
                combat.monsterAttack(monster, hero);
            }
            else {
                // TODO: 11/14/2022 add monster advance function
            }
        }
    }

    public void respawnHeroes()
    {
        for (int i = 0; i < heroes.getTeamSize(); i ++)
        {
            Hero hero = heroes.getMember(i);
            if (hero.isFainted()) {
                hero.revive();
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
                //TODO: 11/14/2022 add hero to map
            }
        }
    }

    public void respawnMonsters()
    {
        for (int i = 0; i < 3; i ++) {
            // TODO: 11/13/2022 add a condition check if the birthplace is already occupied

            Monster monster = monsterGenerator.randomGenerate();
            monster.setLevel(CombatPolicy.levelMatch(heroes, i, "same"));
            monster.setAttributes();

            switch (i) {
                case 0:
                    monster.setLocation(loc.getLane1MonsterBirthplace());
                    break;
                case 1:
                    monster.setLocation(loc.getLane2MonsterBirthplace());
                    break;
                case 2:
                    monster.setLocation(loc.getLane3MonsterBirthplace());
                    break;
            }

            monsters.addMember(monster);
            // TODO: 11/13/2022 add monster to map
        }
    }

    public void exitGame()
    {
        System.out.println("Thanks for your playing. Goodbye!");
        System.exit(0);
    }

    public static int chooseEnemy(InputParser parser, ArrayList<Integer> enemyInRange, MonsterTeam monsters)
    {
        System.out.println("Choose a monster as your target");
        for (int i = 0; i < enemyInRange.size(); i ++) {
            Monster monster = monsters.getMember(enemyInRange.get(i));
            System.out.println(i + ". " + "Location: row:" + monster.getLocation()[1] + " column:" + monster.getLocation()[0]);
            System.out.println(monster);
        }
        System.out.println("enter the number to choose.");
        parser.parseInputToInt(0, enemyInRange.size());
        return parser.getParsedInt();
    }

    public static void removeMonster(int index, MonsterTeam monsters)
    {
        removeMonsterFromBoard(monsters.getMember(index).getLocation());
        monsters.removeMember(index);
    }

    public static void removeMonsterFromBoard(int[] location)
    {
        // TODO: 11/14/2022 implement the method removing a monster from a tile
    }

    public static void removeHeroFromBoard(int[] location)
    {
        // TODO: 11/14/2022 implement the method removing a hero from a tile
    }
}
