/*
 *  It represents a combat between heroes and monsters.
 */

package combat;

import character.*;
import inventory.*;
import main.BoardSession;

import java.util.*;

public class Combat {

    public void heroAttack(Hero hero, Monster monster)
    {
        double damage = CombatPolicy.heroDamage(hero.getStrength(), hero.getEquipment().getWeaponSlot().getDamage());
        double defense = CombatPolicy.defense(monster.getDefense());
        double dodge = CombatPolicy.monsterDodge(monster.getDodge());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            monster.setHp(Math.max(0, monster.getHp() - (int) Math.round(attackResult)));
            System.out.println(monster.getName() + " received " + attackResult + " damage. " + monster.getName() + " has " + monster.getHp() + " hp left.");
        }
        else {
            System.out.println(monster.getName() + " dodged the attack from " + hero.getName());
        }
    }

    public boolean castSpell(Hero hero, Monster monster, Spell spell)
    {
        boolean success = spell.deductMana(hero);
        if (!success) {
            System.out.println(hero.getName() + "doesn't have enough mp. The use of spell " + spell.getName() + " is failed.");
            return false;
        }

        double damage = CombatPolicy.spellDamage(spell.getDamage(), hero.getDexterity());
        double defense = CombatPolicy.defense(monster.getDefense());
        double dodge = CombatPolicy.monsterDodge(monster.getDodge());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            monster.setHp(Math.max(0, monster.getHp() - (int) Math.round(attackResult)));
            System.out.println(monster.getName() + " received " + attackResult + " damage. " + monster.getName() + " has " + monster.getHp() + " hp left.");
            if (monster.getHp() > 0) {
                spell.additionalEffect(monster, CombatPolicy.getSpellDebuffRate());
            }
        }
        else {
            System.out.println(monster.getName() + " dodged the spell attack from " + hero.getName());
        }

        spell.deductQuantity();
        return true;
    }

    public void monsterAttack(Monster monster, Hero hero)
    {
        double defense = CombatPolicy.defense(hero.getEquipment().getArmorSlot().getDefense());
        double damage = monster.getDamage();
        double dodge = CombatPolicy.heroDodge(hero.getAgility());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            hero.setHp(Math.max(0, hero.getHp() - (int) Math.round(attackResult)));
            System.out.println(hero.getName() + " received " + attackResult + " damage. " + hero.getName() + " has " + hero.getHp() + " hp left.");
            if (hero.getHp() == 0) {
                hero.setFainted(true);
                BoardSession.removeHeroFromBoard(hero.getLocation());
                System.out.println(hero.getName() + " is knocked out by " + monster.getName());
            }
        }
        else {
            System.out.println(hero.getName() + " dodged the attack from " + monster.getName());
        }
    }

    public void roundEndRecovery(HeroTeam heroes)
    {
        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            Hero hero = heroes.getMember(i);
            if (!hero.isFainted()) {
                int hpLimit = LevelUpPolicy.updateHealth(hero.getLevel());
                int mpLimit = LevelUpPolicy.updateMana(hero.getLevel(), hero.getDexterity());

                if (hpLimit > hero.getHp()) {
                    hero.setHp(Math.min(CombatPolicy.roundEndRecover(hero.getHp()), hpLimit));
                    System.out.print(hero.getName() + " recovers HP to" + hero.getHp() + ". ");
                }

                if (mpLimit > hero.getMp()) {
                    hero.setMp(Math.min(CombatPolicy.roundEndRecover(hero.getMp()), mpLimit));
                    System.out.print(hero.getName() + " recovers MP to " + hero.getMp() + ".");
                }
                System.out.println();
            }
        }
    }

    public void distributeReward(Hero hero, Monster monster)
    {
        int gold = CombatPolicy.goldReward(monster);
        int exp = CombatPolicy.expReward(monster);
        hero.gainGold(gold);
        hero.gainExp(exp);
        System.out.println(hero.getName() + " gains " + gold + " golds and " + exp + " exp from this battle.");
    }
}
