/*
 *  It contains a Spirit class inherited from Monster class.
 */

package character;

public class Spirit extends Monster{
    public Spirit(String name, int hp, int level, int damage, int defense, int dodge) {
        super(name, hp, level, damage, defense, dodge);
    }

    @Override
    public void setAttributes() {
        setHp(LevelPolicy.updateHealth(getLevel()));

        setDodge(LevelPolicy.updateFavoredDodge(getDodge(), getLevel()));

        setDefense(LevelPolicy.updateDefense(getDefense(), getLevel()));
        setDamage(LevelPolicy.updateDamage(getDamage(), getLevel()));
    }
}
