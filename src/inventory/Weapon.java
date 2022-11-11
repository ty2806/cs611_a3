/*
 *  It contains a Weapon class inherited from Item class.
 */

package inventory;

public class Weapon extends Item{
    private int damage;
    private int hand;

    public Weapon(String name, int price, int level, int damage, int hand) {
        super(name, price, level);
        this.damage = damage;
        this.hand = hand;
    }

    public int getDamage() {
        return damage;
    }

    public int getHand() {
        return hand;
    }

    public String toString()
    {
        return "Weapon " + super.toString() + " Damage:"+getDamage() + " Hand:"+getHand();
    }
}
