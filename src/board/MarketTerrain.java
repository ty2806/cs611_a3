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

    public String toString()
    {
        return "$";
    }
}
