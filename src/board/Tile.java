/*
 *  This class represents a general tile of a game board
 *  It contains 2 basic properties: the terrain of this tile and the hero team on this tile
 */

package board;

import character.Hero;
import character.Monster;

public class Tile {
    private Terrain terrain;
    private Hero hero;
    private Monster monster;

    public Tile(Terrain t) {
        setTerrain(t);
        hero = null;
    }

    public void setTerrain(Terrain t) {
        terrain = t;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String toString() {
        String s = "";
        s += terrain.toString();

        if (hero != null) {
            s += "H" + hero.getLane();
        } else {
            s += "  ";
        }

        if (monster != null) {
            s += "M";
        } else {
            s += " ";
        }
        return s;
    }
}
