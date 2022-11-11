/*
 *  It contains a Dragon class inherited from Monster class.
 */

package character;

public class Dragon extends Monster{
    public Dragon(String name, int hp, int level, int damage, int defense, int dodge) {
        super(name, hp, level, damage, defense, dodge);
    }

    @Override
    public void setAttributes() {
        setHp(LevelPolicy.updateHealth(getLevel()));

        setDamage(LevelPolicy.updateFavoredDamage(getDamage(), getLevel()));

        setDefense(LevelPolicy.updateDefense(getDefense(), getLevel()));
        setDodge(LevelPolicy.updateDodge(getDodge(), getLevel()));
    }
}
