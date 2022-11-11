/*
 *  It contains a Armor class inherited from Item class.
 */

package inventory;

public class Armor extends Item{
    private int defense;

    public Armor(String name, int price, int level, int defense) {
        super(name, price, level);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public String toString()
    {
        return "Armor" + super.toString() + " Defense:"+getDefense();
    }
}
