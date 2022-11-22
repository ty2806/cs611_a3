/*
 *  It doesn't allow player to get on.
 */

package board;

public class InaccessibleTerrain extends Terrain{
    public InaccessibleTerrain() {}

    @Override
    public boolean accessPolicy()
    {
        return false;
    }

    @Override
    public boolean encounterEnemyPolicy()
    {
        return false;
    }

    @Override
    public boolean MarketPolicy()
    {
        return false;
    }

    @Override
    public String IncreaseAbility() {
        return null;
    }

    public String toString()
    {
        return "X";
    }
}
