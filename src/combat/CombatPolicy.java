/*
 *  It contains all formulae to calculate battle actions.
 */

package combat;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import character.*;

public class CombatPolicy {

    // generate a level number for a monster to match heroes' level
    // heroes : a collection of all heroes on board
    // lane: the lane current invoker located
    // rule: the rule to generate level number
    //      same : generate a number same as current lane's hero level
    //      average : generate a number taking average of all heroes' level
    public static int levelMatch(HeroTeam heroes, int lane, String rule)
    {
        switch (rule)
        {
            case "same":
                return heroes.getMember(lane).getLevel();
            case "average":
                int average = 0;
                for (int i = 0; i < heroes.getTeamSize(); i ++) {
                    average += heroes.getMember(i).getLevel();
                }
                average = (int) Math.round(average/(double)heroes.getTeamSize());
                return average;
            default:
                System.out.println("rule doesn't exist. Pick a rule from high|low|mix");
                return -1;
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

    public static int goldReward(Monster monster)
    {
        return 500 * monster.getLevel();
    }

    public static int expReward(Monster monster)
    {
        return 2 * monster.getLevel();
    }
}
