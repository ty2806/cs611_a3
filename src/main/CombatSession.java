/*
 *  It manages all interactions in a combat.
 */

package main;

import board.Board;
import character.Hero;
import character.HeroTeam;
import character.Monster;
import character.MonsterGenerator;
import combat.Combat;
import util.InputParser;

public class CombatSession {
    private MonsterGenerator monsterGenerator;
    private boolean isLost;

    public CombatSession(MonsterGenerator monsterGenerator) {
        this.monsterGenerator = monsterGenerator;
        isLost = false;
    }

    public boolean isLost() {
        return isLost;
    }

    public void runCombat(InputParser parser, HeroTeam heroes, InventorySession inventorySession, Board map)
    {
        System.out.println("Monsters appear! Start fight!");
        Combat combat = new Combat(heroes);
        combat.initMonsters(monsterGenerator);
        combat.initBoard();

        while (true) {
            for (int i = 0; i < heroes.getTeamSize(); i ++) {
                Hero hero = heroes.getMember(i);
                if (!hero.isFainted()) {
                    heroTurn(parser, hero, combat, inventorySession, map);
                    if (combat.checkMonsterLost()) {
                        combat.declareVictory();
                        return;
                    }
                }
            }

            combat.monsterTurn();
            if (combat.checkHeroLost()) {
                combat.declareDefeat();
                isLost = true;
                return;
            }
            combat.roundEndRecovery();
        }
    }

    public void heroTurn(InputParser parser, Hero hero, Combat combat, InventorySession inventorySession, Board map)
    {
        while (true) {
            combat.printBoard();
            System.out.println("This is " + hero.getName() + "'s turn.");
            System.out.println("A/a:attack a monster|B/b:open inventory to use an item or change equipment|M/m: display map");

            String[] allowedWords = {"A", "a", "B", "b", "M", "m"};
            parser.parseInputToString(allowedWords);
            String command = parser.getParsedString().toLowerCase();
            if (command.equals("a")) {
                heroAttack(parser, hero, combat);
                break;
            }
            else if (command.equals("b")) {
                boolean successUse = inventorySession.combatOpenInventory(parser, hero, combat);
                if (successUse) {
                    break;
                }
            }
            else if (command.equals("m")) {
                map.printBoard();
            }
        }
    }

    public void heroAttack(InputParser parser, Hero hero, Combat combat)
    {
        Monster[] monsters = combat.getBoard().getMonsterSide();
        for (int i = 0; i < monsters.length; i ++) {
            if (combat.isValidTarget(i)) {
                System.out.println(i + " " + monsters[i]);
            }
        }
        while (true) {
            System.out.println("Choose a monster to attack");
            System.out.println("Enter a number before each monster to choose");

            parser.parseInputToInt(0, monsters.length);
            int index = parser.getParsedInt();
            if (combat.isValidTarget(index)) {
                combat.heroAttack(hero, index);
                break;
            }
            else {
                System.out.println("The target position has no monster. Please choose another one.");
            }
        }
    }
}
