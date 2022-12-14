/*
 *  It contains all heros and monsters classes.
 */

package character;

abstract public class Character {
    private String name;
    private int hp;
    private int level;
    private int[] location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }
}
