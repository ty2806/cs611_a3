/*
 *  An abstract hero class. It contains all methods necessary for all heroes.
 */

package character;

import inventory.*;

abstract public class Hero extends Character {
    private int exp;
    private int mp;
    private int strength;
    private int dexterity;
    private int agility;
    private int gold;
    private Inventory bag;
    private Equipment equipment;
    private boolean isFainted;

    public Hero(String name, int hp, int level, int mp, int strength, int dexterity, int agility, int gold, int exp) {
        setName(name);
        setHp(hp);
        setLevel(level);
        setMp(mp);
        setStrength(strength);
        setDexterity(dexterity);
        setAgility(agility);
        setGold(gold);
        setExp(exp);
        setBag(new Inventory());
        setEquipment(new Equipment());
        isFainted = false;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Inventory getBag() {
        return bag;
    }

    public void setBag(Inventory bag) {
        this.bag = bag;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public void gainExp(int exp)
    {
        this.exp += exp;
        levelUp();
    }

    public boolean equipFromBag(int index)
    {
        Item item = bag.getItem(index);
        if (item instanceof Weapon) {
            if (!equipment.getWeaponSlot().getName().equals("empty")) {
                bag.addItem(equipment.getWeaponSlot());
            }
            equipment.equipWeapon((Weapon) bag.removeItem(index));
            System.out.println("Weapon " + item.getName() + " is equipped.");
            return true;
        }
        else if (item instanceof Armor) {
            if (!equipment.getArmorSlot().getName().equals("empty")) {
                bag.addItem(equipment.getArmorSlot());
            }
            equipment.equipArmor((Armor) bag.removeItem(index));
            System.out.println("Armor " + item.getName() + " is equipped.");
            return true;
        }
        else {
            System.out.println("This is not a valid equipment. Please choose another one.");
            return false;
        }
    }

    public void gainGold(int gold)
    {
        this.gold += gold;
    }

    public void loseGold(int gold)
    {
        if (this.gold < gold){
            System.out.println("A hero's gold cannot be negative!");
            throw new IllegalArgumentException();
        }
        this.gold -= gold;
    }

    public void levelUp()
    {
        while (exp >= LevelUpPolicy.expRequired(getLevel())) {
            exp -= LevelUpPolicy.expRequired(getLevel());
            setLevel(getLevel() + 1);
            updateAttributes();
            System.out.println(getName() + " levels up to " + getLevel() + "!");
        }
    }

    public String toString()
    {
        String state = "Name:" + getName() + " HP:" + getHp() + "/" + LevelUpPolicy.updateHealth(getLevel())
                + " MP:" + getMp() + "/" + LevelUpPolicy.updateMana(getLevel(), getDexterity())
                + " Exp:" + getExp() + "/" + LevelUpPolicy.expRequired(getLevel())
                + " Strength:" + getStrength() + " Dexterity:" + getDexterity() + " Agility:" + getAgility()
                + " Gold:" + getGold();
        if (isFainted) {
            state += " FAINTED!";
        }
        state += "\n" + getEquipment();
        return state;
    }

    abstract public void updateAttributes();
}
