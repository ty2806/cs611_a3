/*
 *  It contains a Sorcerer class inherited from Hero class.
 */


package character;

public class Sorcerer extends Hero{
    public Sorcerer(String name, int hp, int level, int mp, int strength, int dexterity, int agility, int gold, int exp) {
        super(name, hp, level, mp, strength, dexterity, agility, gold, exp);
    }

    @Override
    public void updateAttributes() {
        setHp(LevelUpPolicy.updateHealth(getHp()));
        setMp(LevelUpPolicy.updateMana(getLevel(), getDexterity()));

        setDexterity(LevelUpPolicy.updateFavoredDexterity(getDexterity()));
        setAgility(LevelUpPolicy.updateFavoredAgility(getAgility()));

        setStrength(LevelUpPolicy.updateStrength(getStrength()));
    }

    public String toString()
    {
        return "Sorcerer " + super.toString();
    }
}
