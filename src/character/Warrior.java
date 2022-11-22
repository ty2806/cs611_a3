/*
 *  It contains a Warrior class inherited from Hero class.
 */

package character;

public class Warrior extends Hero{
    public Warrior(String name, int hp, int level, int mp, int strength, int dexterity, int agility, int gold, int exp, int lane) {
        super(name, hp, level, mp, strength, dexterity, agility, gold, exp, lane);
    }

    @Override
    public void updateAttributes() {
        setHp(LevelUpPolicy.updateHealth(getLevel()));
        setMp(LevelUpPolicy.updateMana(getLevel(), getDexterity()));

        setStrength(LevelUpPolicy.updateFavoredStrength(getStrength()));
        setAgility(LevelUpPolicy.updateFavoredAgility(getAgility()));

        setDexterity(LevelUpPolicy.updateDexterity(getDexterity()));
    }

    public String toString()
    {
        return "Warrior " + super.toString();
    }
}
