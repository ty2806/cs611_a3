/*
 *  An abstract terrain class. It has 3 subclasses: CommonTerrain, InaccessibleTerrain and MarketTerrain.
 */

package board;

abstract public class Terrain {

    public Terrain() {
    }

    abstract public boolean accessPolicy();

    abstract public boolean encounterEnemyPolicy();

    abstract public boolean MarketPolicy();

    abstract public String IncreaseAbility();

    abstract public String toString();
}