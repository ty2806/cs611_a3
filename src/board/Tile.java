/*
 *  This class represents a general tile of a game board
 *  It contains 2 basic properties: the terrain of this tile and the hero team on this tile
 */

package board;
import character.HeroTeam;
public class Tile {
    private Terrain terrain;
    private HeroTeam heroes;

    public Tile(Terrain t)
    {
        setTerrain(t);
        heroes = null;
    }

    public void setTerrain(Terrain t)
    {
        terrain = t;
    }

    public Terrain getTerrain()
    {
        return terrain;
    }

    public HeroTeam getHeroes() {
        return heroes;
    }

    public void setHeroes(HeroTeam heroes) {
        this.heroes = heroes;
    }

    public String toString() {
        if (heroes != null) {
            return "P";
        } else {
            return terrain.toString();
        }
    }
}
