/*
 *  A factory class responsible for generate heroes by reading data files.
 */

package character;

import java.util.*;
import util.DataParser;

public class HeroGenerator {
    private String warriorFile;
    private String sorcererFile;
    private String paladinFile;
    private ArrayList<ArrayList<String>> warriorList;
    private ArrayList<ArrayList<String>> sorcererList;
    private ArrayList<ArrayList<String>> paladinList;

    public HeroGenerator(String warriorFile, String sorcererFile, String paladinFile) {
        this.warriorFile = warriorFile;
        this.sorcererFile = sorcererFile;
        this.paladinFile = paladinFile;
        warriorList = DataParser.parse(warriorFile);
        sorcererList = DataParser.parse(sorcererFile);
        paladinList = DataParser.parse(paladinFile);
    }

    public ArrayList<ArrayList<String>> getWarriorList() {
        return warriorList;
    }

    public ArrayList<ArrayList<String>> getSorcererList() {
        return sorcererList;
    }

    public ArrayList<ArrayList<String>> getPaladinList() {
        return paladinList;
    }

    public Hero generateHero(int index, String heroType)
    {
        ArrayList<String> attrList;
        if (heroType.equals("warrior")) {
            attrList = warriorList.get(index);
        }
        else if (heroType.equals("sorcerer")) {
            attrList = sorcererList.get(index);
        }
        else {
            attrList = paladinList.get(index);
        }

        String name = attrList.get(0);
        int mana = Integer.parseInt(attrList.get(1));
        int strength = Integer.parseInt(attrList.get(2));
        int agility = Integer.parseInt(attrList.get(3));
        int dexterity = Integer.parseInt(attrList.get(4));
        int money = Integer.parseInt(attrList.get(5));
        int exp = Integer.parseInt(attrList.get(6));

        if (heroType.equals("warrior")) {
            return new Warrior(name, LevelUpPolicy.updateHealth(1), 1, mana, strength, dexterity, agility, money, exp);
        }
        else if (heroType.equals("sorcerer")) {
            return new Sorcerer(name, LevelUpPolicy.updateHealth(1), 1, mana, strength, dexterity, agility, money, exp);
        }
        else {
            return new Paladin(name, LevelUpPolicy.updateHealth(1), 1, mana, strength, dexterity, agility, money, exp);
        }
    }

    public void printLists()
    {
        System.out.println(String.join("   ", "No.", "Name", "HP", "MP", "strength", "agility", "dexterity", "starting money", "starting experience"));

        int index = 0;
        System.out.println("Warriors");
        for (ArrayList<String> list : warriorList) {
            list.add(1, String.valueOf(LevelUpPolicy.updateHealth(1)));
            System.out.println(index + " " + list);
            list.remove(1);
            index += 1;
        }
        System.out.println("Sorcerers");
        for (ArrayList<String> list : sorcererList) {
            list.add(1, String.valueOf(LevelUpPolicy.updateHealth(1)));
            System.out.println(index + " " + list);
            list.remove(1);
            index += 1;
        }
        System.out.println("Paladins");
        for (ArrayList<String> list : paladinList) {
            list.add(1, String.valueOf(LevelUpPolicy.updateHealth(1)));
            System.out.println(index + " " + list);
            list.remove(1);
            index += 1;
        }
    }




}
