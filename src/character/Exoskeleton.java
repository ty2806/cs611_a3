/*
 *  It contains a Exoskeleton class inherited from Monster class.
 */

package character;

public class Exoskeleton extends Monster{
    public Exoskeleton(String name, int hp, int level, int damage, int defense, int dodge) {
        super(name, hp, level, damage, defense, dodge);
    }

    @Override
    public void setAttributes() {
        setHp(LevelPolicy.updateHealth(getLevel()));

        setDefense(LevelPolicy.updateFavoredDefense(getDefense(), getLevel()));

        setDamage(LevelPolicy.updateDamage(getDamage(), getLevel()));
        setDodge(LevelPolicy.updateDodge(getDodge(), getLevel()));
    }
}
