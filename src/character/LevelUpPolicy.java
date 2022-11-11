/*
 *  It contains all formulae needed to level up all kinds of attributes of a hero.
 */

package character;

public class LevelUpPolicy {

    public static int expRequired(int level)
    {
        return level * 10;
    }

    public static int updateStrength(int strength)
    {
        return strength + 1;
    }

    public static int updateDexterity(int dexterity)
    {
        return dexterity + 1;
    }

    public static int updateAgility(int agility)
    {
        return agility + 1;
    }

    public static int updateFavoredStrength(int strength)
    {
        return strength + 2;
    }

    public static int updateFavoredDexterity(int dexterity)
    {
        return dexterity + 2;
    }

    public static int updateFavoredAgility(int agility)
    {
        return agility + 2;
    }

    public static int updateHealth(int level)
    {
        return level * 100;
    }

    public static int updateMana(int level, int dexterity)
    {
        return level * 100 + dexterity / 10;
    }

}
