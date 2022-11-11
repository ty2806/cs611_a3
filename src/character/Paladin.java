/*
 *  It contains a Paladin class inherited from Hero class.
 */

package character;

public class Paladin extends Hero{
    public Paladin(String name, int hp, int level, int mp, int strength, int dexterity, int agility, int gold, int exp) {
        super(name, hp, level, mp, strength, dexterity, agility, gold, exp);
    }

    @Override
    public void updateAttributes() {
        setHp(LevelUpPolicy.updateHealth(getHp()));
        setMp(LevelUpPolicy.updateMana(getLevel(), getDexterity()));

        setDexterity(LevelUpPolicy.updateFavoredDexterity(getDexterity()));
        setStrength(LevelUpPolicy.updateFavoredStrength(getStrength()));

        setAgility(LevelUpPolicy.updateAgility(getAgility()));
    }

    public String toString()
    {
        return "Paladin " + super.toString();
    }
}
