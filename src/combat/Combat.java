/*
 *  It represents a combat between heroes and monsters.
 */

package combat;

import character.*;
import inventory.*;
import java.util.*;

public class Combat {
    private HeroTeam heroes;
    private ArrayList<Monster> monsters;
    private CombatBoard board;
    private Random rand;

    public Combat(HeroTeam heroes)
    {
        this.heroes = heroes;
        monsters = new ArrayList<>();
        board = new CombatBoard(heroes.getTeamSize());
        rand = new Random();
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public HeroTeam getHeroes() {
        return heroes;
    }

    public CombatBoard getBoard() {
        return board;
    }

    public Random getRand() {
        return rand;
    }

    public void initMonsters(MonsterGenerator generator)
    {
        heroes.updateLevelExtreme();

        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            Monster monster = generator.randomGenerate();
            int level = CombatPolicy.levelMatch(heroes.getLowest(), heroes.getHighest(), "mix");
            monster.setLevel(level);
            monster.setAttributes();
            monsters.add(monster);
        }
    }

    public void initBoard()
    {
        board.fill(heroes, monsters);
    }

    public void heroAttack(Hero hero, int target)
    {
        double damage = CombatPolicy.heroDamage(hero.getStrength(), hero.getEquipment().getWeaponSlot().getDamage());

        Monster monster = board.getMonsterSide()[target];
        double defense = CombatPolicy.defense(monster.getDefense());
        double dodge = CombatPolicy.monsterDodge(monster.getDodge());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            monster.setHp(monster.getHp() - (int) Math.round(attackResult));
            System.out.println(monster.getName() + " received " + attackResult + " damage. " + monster.getName() + " has " + Math.max(0, monster.getHp()) + " hp left.");
            if (monster.getHp() <= 0) {
                board.removeMonster(target);
                System.out.println(monster.getName() + " is killed by " + hero.getName());
            }
        }
        else {
            System.out.println(monster.getName() + " dodged the attack from " + hero.getName());
        }
    }

    public boolean castSpell(Hero hero, int target, Spell spell)
    {
        boolean success = spell.deductMana(hero);
        if (!success) {
            System.out.println(hero.getName() + "doesn't have enough mp. The use of spell " + spell.getName() + " is failed.");
            return false;
        }

        Monster monster = board.getMonsterSide()[target];
        double damage = CombatPolicy.spellDamage(spell.getDamage(), hero.getDexterity());
        double defense = CombatPolicy.defense(monster.getDefense());
        double dodge = CombatPolicy.monsterDodge(monster.getDodge());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            monster.setHp(monster.getHp() - (int) Math.round(attackResult));
            System.out.println(monster.getName() + " received " + attackResult + " damage. " + monster.getName() + " has " + Math.max(0, monster.getHp()) + " hp left.");
            if (monster.getHp() <= 0) {
                board.removeMonster(target);
                System.out.println(monster.getName() + " is slayed by " + hero.getName());
            }

            spell.additionalEffect(monster, CombatPolicy.getSpellDebuffRate());
        }
        else {
            System.out.println(monster.getName() + " dodged the spell attack from " + hero.getName());
        }

        spell.deductQuantity();
        return true;
    }

    public void monsterAttack(Monster monster, int target)
    {
        Hero hero = board.getHeroSide()[target];
        double defense = CombatPolicy.defense(hero.getEquipment().getArmorSlot().getDefense());

        double damage = monster.getDamage();
        double dodge = CombatPolicy.heroDodge(hero.getAgility());

        double attackResult = CombatPolicy.attack(damage, defense, dodge);
        if (attackResult >= 0) {
            hero.setHp(Math.max(0, hero.getHp() - (int) Math.round(attackResult)));
            System.out.println(hero.getName() + " received " + attackResult + " damage. " + hero.getName() + " has " + hero.getHp() + " hp left.");
            if (hero.getHp() == 0) {
                hero.setFainted(true);
                System.out.println(hero.getName() + " is knocked out by " + monster.getName());
            }
        }
        else {
            System.out.println(hero.getName() + " dodged the attack from " + monster.getName());
        }
    }

    public void monsterTurn()
    {
        System.out.println("Now it's monsters' turn. All monsters choose to attack.");
        for (int i = 0; i < board.getSize(); i ++) {
            if (board.getMonsterSide()[i] != null) {
                int target = CombatPolicy.pickTarget(board.getHeroSide(), i, "lowestHp");
                monsterAttack(board.getMonsterSide()[i], target);
            }
        }
    }

    public void roundEndRecovery()
    {
        for (Hero hero : board.getHeroSide())
        {
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

    public boolean checkHeroLost()
    {
        for (Hero hero : board.getHeroSide()) {
            if (!hero.isFainted()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMonsterLost()
    {
        for (Monster monster : board.getMonsterSide()) {
            if (monster != null) {
                return false;
            }
        }
        return true;
    }

    public void distributeReward()
    {
        for (Hero hero : board.getHeroSide())
        {
            if (!hero.isFainted()) {
                int gold = CombatPolicy.goldReward(monsters);
                int exp = CombatPolicy.expReward(monsters);
                hero.gainGold(gold);
                hero.gainExp(exp);
                System.out.println(hero.getName() + " gains " + gold + " golds and " + exp + " exp from this battle.");
            }
            else {
                hero.setFainted(false);
                hero.setHp(LevelUpPolicy.updateHealth(hero.getLevel()) / 2);
                hero.setMp(LevelUpPolicy.updateMana(hero.getLevel(), hero.getDexterity()) / 2);
                System.out.println(hero.getName() + " is revived after the battle ends.");
            }
        }
    }

    public void declareVictory()
    {
        System.out.println("All monsters are defeated. Heros have won the battle!");
        distributeReward();
    }

    public void declareDefeat()
    {
        System.out.println("All heroes are fainted. The battle is lost!");
    }

    public boolean isValidTarget(int index)
    {
        return board.getMonsterSide()[index] != null;
    }

    public void printBoard()
    {
        board.printBoard();
    }
}
