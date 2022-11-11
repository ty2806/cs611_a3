/*
 *  It manages player's behavior on the big map.
 */


package main;

import board.Board;
import character.HeroTeam;
import character.MonsterGenerator;
import inventory.Inventory;
import inventory.ItemGenerator;
import util.InputParser;

public class BoardSession {
    private ItemGenerator itemGenerator;
    private MonsterGenerator monsterGenerator;
    private InputParser parser;
    private Board map;
    private HeroTeam heroes;
    private int heroLocationX;
    private int heroLocationY;

    public BoardSession(ItemGenerator itemGenerator, MonsterGenerator monsterGenerator, InputParser parser, Board map, HeroTeam heroes, int x, int y) {
        this.itemGenerator = itemGenerator;
        this.monsterGenerator = monsterGenerator;
        this.parser = parser;
        this.map = map;
        this.heroes = heroes;
        heroLocationX = x;
        heroLocationY = y;
    }

    public void runBoard()
    {
        InfoSession infoSession = new InfoSession();
        MarketSession marketSession = new MarketSession(itemGenerator);
        InventorySession inventorySession = new InventorySession();
        CombatSession combatSession = new CombatSession(monsterGenerator);

        while (true) {
            String command = mapControl(parser, map, heroLocationX, heroLocationY);
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
                    inventorySession.openInventoryInterface(parser, heroes);
                    break;
                default:
                    boolean validMove = moveHero(command, heroLocationX, heroLocationY);
                    if (validMove) {
                        if (map.isEncounterMonster(heroLocationX, heroLocationY)) {
                            combatSession.runCombat(parser, heroes, inventorySession, map);
                            if (combatSession.isLost()) {
                                System.out.println("Game Over!");
                                exitGame();
                            }
                        }
                    }
            }
        }
    }

    public String mapControl(InputParser parser, Board map, int x, int y)
    {
        map.printBoard();
        if (map.isMarket(x, y)) {
            System.out.println("You are near a Market! You can input M/m to enter this market");
        }

        System.out.println("You can control your team by following commands");
        if (map.isMarket(x, y)) {
            System.out.println("W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags|M/m:enter market");
        }
        else {
            System.out.println("W/w:move up|A/a:move left|S/s:move down|D/d:move right|Q/q:quit game|I/i:show information|B/b: open bags");
        }

        String[] allowedWords = {"Q", "q", "W", "w", "A", "a", "S", "s", "D", "d", "I", "i", "B", "b", "M", "m"};
        parser.parseInputToString(allowedWords);
        return parser.getParsedString().toLowerCase();
    }

    public boolean moveHero(String command, int x, int y)
    {
        int newX = x;
        int newY = y;
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
            map.move(x, y, newX, newY);
            heroLocationX = newX;
            heroLocationY = newY;
            return true;
        }
        else {
            System.out.println("This is not a valid move. The destination is unreachable. Please try another direction.");
            return false;
        }
    }

    public void exitGame()
    {
        System.out.println("Thanks for your playing. Goodbye!");
        System.exit(0);
    }
}
