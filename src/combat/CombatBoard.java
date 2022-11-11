/*
 *  It represents a battlefield to combat on.
 */

package combat;

import character.*;

import java.util.ArrayList;

public class CombatBoard {
    private Hero[] HeroSide;
    private Monster[] MonsterSide;
    private int size;

    public CombatBoard(int size)
    {
        HeroSide = new Hero[size];
        MonsterSide = new Monster[size];
        this.size = size;
    }

    public Hero[] getHeroSide() {
        return HeroSide;
    }

    public Monster[] getMonsterSide() {
        return MonsterSide;
    }

    public int getSize() {
        return size;
    }

    public void fill(HeroTeam heroes, ArrayList<Monster> monsters)
    {
        for (int i = 0; i < size; i ++) {
            HeroSide[i] = heroes.getMember(i);
            MonsterSide[i] = monsters.get(i);
        }
    }

    public void removeMonster(int index)
    {
        MonsterSide[index] = null;
    }

    public void printBoard()
    {
        System.out.println("===============================================================================");
        for (int i = 0; i < size; i ++) {
            Hero hero = HeroSide[i];
            int hpLimit = LevelUpPolicy.updateHealth(hero.getLevel());
            int mpLimit = LevelUpPolicy.updateMana(hero.getLevel(), hero.getDexterity());

            if (hero.isFainted()) {
                System.out.print("FAINTED ");
            }
            System.out.print(String.join(" ", hero.getName(),
                    "HP:"+hero.getHp()+"/"+hpLimit,
                    "MP:"+hero.getMp()+"/"+mpLimit,
                    "Damage:"+CombatPolicy.heroDamage(hero.getStrength(), hero.getEquipment().getWeaponSlot().getDamage()),
                    "Defense:"+CombatPolicy.defense(hero.getEquipment().getArmorSlot().getDefense()),
                    "Dodge:"+CombatPolicy.heroDodge(hero.getAgility())));

            System.out.print("|");

            if (MonsterSide[i] == null) {
                System.out.print(" EMPTY ");
            }
            else {
                Monster monster = MonsterSide[i];
                System.out.print(monster);
            }
            System.out.println();
        }
        System.out.println("===============================================================================");
    }

}
