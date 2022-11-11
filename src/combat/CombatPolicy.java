/*
 *  It contains all formulae to calculate battle actions.
 */

package combat;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import character.*;

public class CombatPolicy {

    // generate a level number for a monster to match heroes' level
    // lowest : the lowest level of current hero team
    // highest: the highest level of current hero team
    // rule: the rule to generate level number
    //      high : generate a number same as the highest
    //      low : generate a number same as the lowest
    //      mix: generate a random number ranging between lowest and highest
    public static int levelMatch(int lowest, int highest, String rule)
    {
        switch (rule)
        {
            case "high":
                return highest;
            case "low":
                return lowest;
            case "mix":
                return ThreadLocalRandom.current().nextInt(lowest, highest + 1);
            default:
                System.out.println("rule doesn't exist. Pick a rule from high|low|mix");
                return -1;
        }
    }

    public static int pickTarget(Hero[] heroes, int position, String rule)
    {
        int target = 0;
        for (int i = 0; i < heroes.length; i ++) {
            if (!heroes[i].isFainted()) {
                target = i;
            }
        }

        switch (rule) {
            case "direct":
                if (!heroes[position].isFainted()) {
                    return position;
                }
                else {
                    return target;
                }
            case "lowestHp":
                int hp = heroes[target].getHp();
                for (int i = 0; i < heroes.length; i ++) {
                    if (!heroes[i].isFainted()) {
                        if (heroes[i].getHp() < hp) {
                            hp = heroes[i].getHp();
                            target = i;
                        }
                    }
                }
                return target;
            default:
                return target;
        }
    }

    public static double heroDamage(int strength, int weaponDamage)
    {
        return (strength + weaponDamage) * 0.5;
    }

    public static double spellDamage(int damage, int dexterity)
    {
        return damage * (1 + (double) (dexterity) / 10000);
    }

    public static double heroDodge(int agility)
    {
        return agility*0.0002;
    }

    public static double monsterDodge(int dodge)
    {
        return dodge*0.0001;
    }

    public static double defense(int defense)
    {
        return defense;
    }

    public static double attack(double damage, double defense, double dodge)
    {
        if (ThreadLocalRandom.current().nextDouble(1) > dodge) {
            double damageInflicted = damage - defense;
            return Math.max(0, damageInflicted);
        }
        else {
            return -1;
        }
    }

    public static double getSpellDebuffRate()
    {
        return 0.1;
    }

    public static int roundEndRecover(int value)
    {
        return (int) Math.round(value * 1.1);
    }

    public static int goldReward(ArrayList<Monster> monsters)
    {
        int gold = 0;
        for (Monster monster : monsters)
        {
            gold += monster.getLevel() * 100;
        }
        return gold;
    }

    public static int expReward(ArrayList<Monster> monsters)
    {
        int exp = 0;
        for (Monster monster : monsters)
        {
            exp += monster.getLevel() * 2;
        }
        return exp;
    }
}
