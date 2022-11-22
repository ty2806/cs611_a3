/*
 *  Player is allowed to enter a market on this terrain.
 */

package board;

public class MarketTerrain extends Terrain{
    public MarketTerrain() {}

    @Override
    public boolean accessPolicy() {
        return true;
    }

    @Override
    public boolean encounterEnemyPolicy() {
        return false;
    }

    @Override
    public boolean MarketPolicy() {
        return true;
    }

    @Override
    public String IncreaseAbility() {
        return null;
    }
    @Override
    public String toString()
    {
        return "M";
    }
}
