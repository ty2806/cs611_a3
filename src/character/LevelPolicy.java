/*
 *  It contains all formulae needed to calculate all kinds of attributes of a monster for each level.
 */

package character;

public class LevelPolicy {

    public static int updateDamage(int damage, int level)
    {
        return damage + (int) Math.round(level*0.1*damage);
    }

    public static int updateDefense(int defense, int level)
    {
        return defense + (int) Math.round(level*0.1*defense);
    }

    public static int updateDodge(int dodge, int level)
    {
        return dodge + (int) Math.round(level*0.1*dodge);
    }

    public static int updateFavoredDamage(int damage, int level) { return damage + (int) Math.round(level*0.2*damage); }

    public static int updateFavoredDefense(int defense, int level)
    {
        return defense + (int) Math.round(level*0.2*defense);
    }

    public static int updateFavoredDodge(int dodge, int level)
    {
        return dodge + (int) Math.round(level*0.2*dodge);
    }

    public static int updateHealth(int level)
    {
        return level*100;
    }
}
