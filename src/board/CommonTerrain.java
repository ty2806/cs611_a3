/*
 *  It has a method allowing a probability of 30% to encounter monsters.
 */

package board;
import java.util.Random;

public class CommonTerrain extends Terrain{
    private final double encounterEnemyProb = 0.3;
    private Random rand;

    public CommonTerrain()
    {
        rand = new Random();
    }

    @Override
    public boolean accessPolicy() {
        return true;
    }

    @Override
    public boolean encounterEnemyPolicy() {
        if (rand.nextDouble() > encounterEnemyProb) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean MarketPolicy()
    {
        return false;
    }

    public String toString()
    {
        return " ";
    }
}
