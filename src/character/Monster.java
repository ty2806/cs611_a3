/*
 *  An abstract Monster class. It contains all methods necessary for all Monsters.
 */

package character;

import combat.CombatPolicy;

abstract public class Monster extends Character{
    private int damage;
    private int defense;
    private int dodge;

    public Monster(String name, int hp, int level, int damage, int defense, int dodge) {
        setName(name);
        setHp(hp);
        setLevel(level);
        setDamage(damage);
        setDefense(defense);
        setDodge(dodge);
        setAttributes();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public String toString()
    {
        return String.join(" ", getName(), "Level:"+getLevel(), "HP:"+getHp(), "Damage:"+getDamage(),
                "Defense:"+ CombatPolicy.defense(getDefense()), "Dodge:"+CombatPolicy.monsterDodge(getDodge()));
    }

    abstract public void setAttributes();
}
