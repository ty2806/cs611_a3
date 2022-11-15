/*
 *  It manages inventory actions such as use items in inventory.
 */

package main;

import character.Hero;
import character.HeroTeam;
import character.Monster;
import character.MonsterTeam;
import combat.Combat;
import inventory.Item;
import inventory.Potion;
import inventory.Spell;
import util.InputParser;

import java.util.ArrayList;

public class InventorySession {

    public boolean openInventory(InputParser parser, Hero hero, HeroTeam heroes, MonsterTeam monsters, ArrayList<Integer> enemyInRange, Combat combat)
    {
        while (true) {
            int index = chooseItem(parser, hero);
            if (index < 0) {
                return false;
            }

            Item item = hero.getBag().getItem(index);
            boolean successUse;
            if (item instanceof Spell) {
                if (enemyInRange.size() > 0) {
                    successUse = castSpell(parser, hero, index, enemyInRange, monsters, combat);
                }
                else {
                    System.out.println("A spell cannot be used under safe circumstance. Choose another item instead.");
                    successUse = false;
                }
            } else if (item instanceof Potion) {
                successUse = drinkPotion(parser, heroes, hero, index);
            } else {
                successUse = hero.equipFromBag(index);
            }
            if (successUse) {
                return true;
            }
        }
    }

    public int chooseItem(InputParser parser, Hero hero)
    {
        System.out.println(hero.getName() + " is equipped with:");
        System.out.println(hero.getEquipment());
        System.out.println(hero.getName() + " has:");
        hero.getBag().printInventory();
        System.out.println("Choose an item to use or a weapon/armor to equip");
        System.out.println("Enter a number before each item to use/equip or enter q to quit inventory");

        String command = commandSelection(parser, hero.getBag().getInventorySize());
        if (command.equals("q")) {
            return -1;
        }
        else {
            return Integer.parseInt(command);
        }
    }

    public boolean drinkPotion(InputParser parser, HeroTeam heroes, Hero user, int index)
    {
        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            Hero hero = heroes.getMember(i);
            System.out.println(i + ". " + hero);
        }

        Potion potion = (Potion) user.getBag().getItem(index);
        System.out.println("Choose a hero to drink " + potion.getName());
        System.out.println("Enter a number before each hero to choose or enter q to stop drinking potion");

        String command = commandSelection(parser, heroes.getTeamSize());
        if (command.equals("q")) {
            return false;
        }
        else {
            potion.drink(heroes.getMember(Integer.parseInt(command)));
            user.getBag().removeItem(index);
            return true;
        }
    }

    public boolean castSpell(InputParser parser, Hero hero, int index, ArrayList<Integer> enemyInRange, MonsterTeam monsters, Combat combat)
    {
        int monsterIndex = BoardSession.chooseEnemy(parser, enemyInRange, monsters);

        Monster monster = monsters.getMember(monsterIndex);
        Spell spell = (Spell) hero.getBag().getItem(index);
        if (!combat.castSpell(hero, monster, spell)) {
            return false;
        }
        if (spell.getQuantity() == 0) {
            hero.getBag().removeItem(index);
            System.out.println(spell.getName() + " is exhausted.");
        }

        if (monster.getHp() <= 0) {
            BoardSession.removeMonster(monsterIndex, monsters);
            System.out.println(monster.getName() + " is slayed by " + hero.getName());
            combat.distributeReward(hero, monster);
        }

        return true;

    }

    public String commandSelection(InputParser parser, int upperbound)
    {
        String[] allowedWords = new String[upperbound+2];
        for (int i = 0; i < upperbound; i ++) {
            allowedWords[i] = String.valueOf(i);
        }
        allowedWords[upperbound] = "Q";
        allowedWords[upperbound+1] = "q";

        parser.parseInputToString(allowedWords);
        String command = parser.getParsedString().toLowerCase();
        return command;
    }
}
