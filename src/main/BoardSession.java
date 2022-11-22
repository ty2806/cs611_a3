/*
 *  It manages player's behavior on the big map.
 */


package main;

import board.LOVHeroNexus;
import board.LOVMonsterNexus;
import board.LegendsOfValorBoard;
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
    private LegendsOfValorBoard map;
    private HeroTeam heroes;
    private MonsterTeam monsters;
    private InitialLocations loc;
    private int roundNum;
    private final int respawnRound = 8;

    public BoardSession(ItemGenerator itemGenerator, MonsterGenerator monsterGenerator, InputParser parser, LegendsOfValorBoard map, HeroTeam heroes, MonsterTeam monsters, InitialLocations loc) {
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
            monstersTurn(combat);
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
                        boolean successUse = inventorySession.openInventory(parser, hero, heroes, monsters, enemyInRange, combat, map);
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
                            checkHeroWin(hero);
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
        System.out.println(map);
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
            System.out.print("M/m:enter market");
        }
        System.out.println("W/w:move up|A/a:move left|S/s:move down|D/d:move right|T/t:teleport|R/r:recall|Q/q:quit game|I/i:show information|B/b: open bags");

        String[] allowedWords = {"Q", "q", "W", "w", "A", "a", "S", "s", "D", "d", "T", "t", "R", "r", "I", "i", "B", "b", "M", "m", "K", "k"};
        parser.parseInputToString(allowedWords);
        return parser.getParsedString().toLowerCase();
    }

    public void heroAttack(Hero hero, ArrayList<Integer> enemyInRange, Combat combat)
    {
        int index = chooseEnemy(parser, enemyInRange, monsters);

        Monster monster = monsters.getMember(index);
        combat.heroAttack(hero, monster);
        if (monster.getHp() <= 0) {
            removeMonster(index, monsters, map);
            System.out.println(monster.getName() + " is slayed by " + hero.getName());
            combat.distributeReward(hero, monster);
        }
    }

    public boolean moveHero(String command, Hero hero)
    {
        int[] dest = new int[]{hero.getLocation()[0], hero.getLocation()[1]};
        String moveType = "Regular";
        switch (command) {
            case "w":
                dest[1] -= 1;
                break;
            case "s":
                dest[1] += 1;
                break;
            case "a":
                dest[0] -= 1;
                break;
            case "d":
                dest[0] += 1;
                break;
            case "t":
                dest = inputTeleport();
                moveType = "Teleport";
                break;
            case "r":
                dest = getHeroBirthplace(hero);
                moveType = "Recall";
                break;
        }

        boolean moveResult = map.makeMoveHero(hero.getLocation(), dest, moveType);
        if (moveResult) {
            hero.setLocation(dest);
            return true;
        }
        else {
            System.out.println("This is not a valid move. The destination is unreachable. Please try another direction.");
            return false;
        }
    }

    public int[] inputTeleport()
    {
        int[] location = new int[2];
        System.out.println("Please enter the row and column you wish to teleport.");
        parser.parseInputToInt(0, 7);
        location[1] = parser.getParsedInt();

        parser.parseInputToInt(0, 7);
        location[0] = parser.getParsedInt();
        return location;
    }

    public int[] getHeroBirthplace(Hero hero)
    {
        int lane = hero.getLane();
        switch (lane) {
            case 0:
                return loc.getLane1HeroBirthplace();
            case 1:
                return loc.getLane2HeroBirthplace();
            default:
                return loc.getLane3HeroBirthplace();
        }
    }

    public void monstersTurn(Combat combat)
    {
        for (int i = 0; i < monsters.getTeamSize(); i ++) {
            Monster monster = monsters.getMember(i);
            ArrayList<Integer> enemyInRange = searchEnemy(monster.getLocation(), "hero");

            if (enemyInRange.size() > 0) {
                Hero hero = heroes.getMember(enemyInRange.get(0));
                combat.monsterAttack(monster, hero, map);
            }
            else {
                int[] dest = new int[]{monster.getLocation()[0], monster.getLocation()[1]+1};
                map.makeMoveMonster(monster.getLocation(), dest);
                checkMonsterWin(monster);
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
                hero.setLocation(getHeroBirthplace(hero));
                map.getCell(getHeroBirthplace(hero)[1], getHeroBirthplace(hero)[0]).setHero(hero);
            }
        }
    }

    public void respawnMonsters()
    {
        for (int i = 0; i < 3; i ++) {
            int[] birthplace;
            switch (i) {
                case 0:
                    birthplace = loc.getLane1MonsterBirthplace();
                    break;
                case 1:
                    birthplace = loc.getLane2MonsterBirthplace();
                    break;
                default:
                    birthplace = loc.getLane3MonsterBirthplace();
                    break;
            }
            if (map.getCell(birthplace[1], birthplace[0]).getMonster() != null) {
                continue;
            }

            Monster monster = monsterGenerator.randomGenerate();
            monster.setLevel(CombatPolicy.levelMatch(heroes, i, "same"));
            monster.setAttributes();
            monster.setLocation(birthplace);
            monsters.addMember(monster);
            map.getCell(birthplace[1], birthplace[0]).setMonster(monster);
        }
    }

    public void checkHeroWin(Hero hero)
    {
        if (map.getCell(hero.getLocation()[1], hero.getLocation()[0]).getTerrain() instanceof LOVMonsterNexus) {
            System.out.println("Heroes has won!");
            exitGame();
        }
    }

    public void checkMonsterWin(Monster monster)
    {
        if (map.getCell(monster.getLocation()[1], monster.getLocation()[0]).getTerrain() instanceof LOVHeroNexus) {
            System.out.println("Monsters has won!");
            exitGame();
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

    public static void removeMonster(int index, MonsterTeam monsters, LegendsOfValorBoard board)
    {
        removeMonsterFromBoard(monsters.getMember(index).getLocation(), board);
        monsters.removeMember(index);
    }

    public static void removeMonsterFromBoard(int[] location, LegendsOfValorBoard board)
    {
        board.getCell(location[1], location[0]).setMonster(null);
    }

    public static void removeHeroFromBoard(int[] location, LegendsOfValorBoard board)
    {
        board.getCell(location[1], location[0]).setHero(null);
    }
}
