/*
 *  It contains a Potion class inherited from Item class.
 */

package inventory;

import character.*;

public class Potion extends Item{
    private int effect;
    private String attribute;

    public Potion(String name, int price, int level, int effect, String attribute) {
        super(name, price, level);
        this.effect = effect;
        this.attribute = attribute;
    }

    public int getEffect() {
        return effect;
    }

    public String getAttribute() {
        return attribute;
    }

    public void increaseAttr(Hero hero, String attribute)
    {
        switch (attribute)
        {
            case "strength":
                hero.setStrength(hero.getStrength() + effect);
                break;
            case "dexterity":
                hero.setDexterity(hero.getDexterity() + effect);
                break;
            case "agility":
                hero.setAgility(hero.getAgility() + effect);
                break;
            case "hp":
                hero.setHp(hero.getHp() + effect);
                break;
            case "mp":
                hero.setMp(hero.getMp() + effect);
                break;
        }
        System.out.println(hero.getName() + "'s " + attribute + " increased by " + effect);
    }

    public void revive(Hero hero)
    {
        hero.setFainted(false);
        hero.setHp(LevelUpPolicy.updateHealth(hero.getLevel()) / 2);
        System.out.println(hero.getName() + " is revived!");
    }

    public void drink(Hero hero)
    {
        System.out.println(hero.getName() + " has drank " + getName());
        for (String attr : attribute.split("\\|")) {
            if (attr.equals("revive")) {
                revive(hero);
            }
            else {
                increaseAttr(hero, attr);
            }
        }
    }

    public String toString()
    {
        return "Potion" + super.toString() + " Effect:"+getEffect() + " Attributes:"+getAttribute();
    }
}
