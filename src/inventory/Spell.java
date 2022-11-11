/*
 *  It contains a Spell class inherited from Item class.
 */

package inventory;

import character.*;

public class Spell extends Item{
    private int damage;
    private int mana;
    private String spellType;
    private int quantity;

    public Spell(String name, int price, int level, int damage, int mana, String spellType, int quantity) {
        super(name, price, level);
        this.damage = damage;
        this.mana = mana;
        this.spellType = spellType;
        this.quantity = quantity;
    }

    public int getDamage() {
        return damage;
    }

    public int getMana() {
        return mana;
    }

    public String getSpellType() {
        return spellType;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean deductMana(Hero hero)
    {
        if (hero.getMp() >= mana)
        {
            hero.setMp(hero.getMp() - mana);
            return true;
        }
        else {
            return false;
        }
    }

    public void deductQuantity()
    {
        quantity -= 1;
    }

    public void reduceAttribute(Monster monster, String attribute, double rate)
    {
        switch (attribute) {
            case "damage":
                monster.setDamage((int) Math.round(monster.getDamage()*(1-rate)));
                break;
            case "defense":
                monster.setDefense((int) Math.round(monster.getDefense()*(1-rate)));
                break;
            case "dodge":
                monster.setDodge((int) Math.round(monster.getDodge()*(1-rate)));
                break;
        }
    }

    public void additionalEffect(Monster monster, double rate)
    {
        switch (spellType) {
            case "ice":
                reduceAttribute(monster, "damage", rate);
                System.out.println(monster.getName() + "'s damage is reduced by " + rate*100 + "%");
                break;
            case "fire":
                reduceAttribute(monster, "defense", rate);
                System.out.println(monster.getName() + "'s defense is reduced by " + rate*100 + "%");
                break;
            case "lightning":
                reduceAttribute(monster, "dodge", rate);
                System.out.println(monster.getName() + "'s dodge ability is reduced by " + rate*100 + "%");
                break;
        }
    }

    public String toString()
    {
        return "Spell" + super.toString() + " Damage:"+getDamage() + " Mana cost:"+getMana() + " Type:"+getSpellType() + " Quantity:"+getQuantity();
    }
}
