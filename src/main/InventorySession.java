/*
 *  It manages inventory actions such as use items in inventory.
 */

package main;

import character.Hero;
import character.HeroTeam;
import character.Monster;
import combat.Combat;
import inventory.Item;
import inventory.Potion;
import inventory.Spell;
import util.InputParser;

public class InventorySession {

    public void openInventoryInterface(InputParser parser, HeroTeam heroes)
    {
        while (true) {
            for (int i = 0; i < heroes.getTeamSize(); i ++) {
                Hero hero = heroes.getMember(i);
                System.out.println(i + ". " + hero);
            }
            System.out.println("Choose a hero's bag to open");
            System.out.println("Enter a number before each hero to open his bag or enter q to quit inventory interface");

            String command = commandSelection(parser, heroes.getTeamSize());
            if (command.equals("q")) {
                break;
            }
            else {
                Hero hero = heroes.getMember(Integer.parseInt(command));
                safeOpenInventory(parser, hero, heroes);
            }
        }
    }

    public void safeOpenInventory(InputParser parser, Hero hero, HeroTeam heroes)
    {
        while (true){
            int index = chooseItem(parser, hero);
            if (index < 0) {
                break;
            }

            Item item = hero.getBag().getItem(index);
            if (item instanceof Spell) {
                System.out.println("A spell cannot be used under safe circumstance. Choose another item instead.");
            }
            else if (item instanceof Potion) {
                drinkPotion(parser, heroes, hero, index);
            }
            else {
                hero.equipFromBag(index);
            }
        }
    }

    public boolean combatOpenInventory(InputParser parser, Hero hero, Combat combat)
    {
        HeroTeam heroes = combat.getHeroes();
        Monster[] monsters = combat.getBoard().getMonsterSide();
        while (true) {
            int index = chooseItem(parser, hero);
            if (index < 0) {
                return false;
            }

            Item item = hero.getBag().getItem(index);
            boolean successUse;
            if (item instanceof Spell) {
                successUse = castSpell(parser, hero, index, monsters, combat);
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

    public boolean castSpell(InputParser parser, Hero hero, int index, Monster[] monsters, Combat combat)
    {
        for (int i = 0; i < monsters.length; i ++) {
            if (combat.isValidTarget(i)) {
                System.out.println(i + " " + monsters[i]);
            }
        }

        Spell spell = (Spell) hero.getBag().getItem(index);
        System.out.println("Choose a monster to use spell " + spell.getName());
        System.out.println("Enter a number before each monster to choose or enter q to stop casting spell");

        String command = commandSelection(parser, monsters.length);
        if (command.equals("q")) {
            return false;
        }
        else {
            int monsterIndex = Integer.parseInt(command);
            if (!combat.isValidTarget(monsterIndex)) {
                System.out.println("This position has no monster. This use is not valid");
                return false;
            }
            if (!combat.castSpell(hero, monsterIndex, spell)) {
                return false;
            }
            if (spell.getQuantity() == 0) {
                hero.getBag().removeItem(index);
                System.out.println(spell.getName() + " is exhausted.");
            }
            return true;
        }
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
