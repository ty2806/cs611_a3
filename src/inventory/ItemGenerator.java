/*
 *  A factory class responsible for generate items by reading data files.
 */

package inventory;

import util.DataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemGenerator {
    private String weaponFile;
    private String armorFile;
    private String potionFile;
    private String spellFile;
    private ArrayList<ArrayList<String>> weaponList;
    private ArrayList<ArrayList<String>> armorList;
    private ArrayList<ArrayList<String>> potionList;
    private ArrayList<ArrayList<String>> spellList;

    public ItemGenerator(String weaponFile, String armorFile, String potionFile, String spellFile) {
        this.weaponFile = weaponFile;
        this.armorFile = armorFile;
        this.potionFile = potionFile;
        this.spellFile = spellFile;
        weaponList = DataParser.parse(weaponFile);
        armorList = DataParser.parse(armorFile);
        potionList = DataParser.parse(potionFile);
        spellList = DataParser.parse(spellFile);
    }

    public ArrayList<ArrayList<String>> getWeaponList() {
        return weaponList;
    }

    public ArrayList<ArrayList<String>> getArmorList() {
        return armorList;
    }

    public ArrayList<ArrayList<String>> getPotionList() {
        return potionList;
    }

    public ArrayList<ArrayList<String>> getSpellList() {
        return spellList;
    }

    public Item generateItem(int index, String itemType)
    {
        ArrayList<String> attrList;
        switch (itemType) {
            case "weapon":
                attrList = weaponList.get(index);
                break;
            case "armor":
                attrList = armorList.get(index);
                break;
            case "potion":
                attrList = potionList.get(index);
                break;
            default:
                attrList = spellList.get(index);
                break;
        }

        String name = attrList.get(0);
        int price = Integer.parseInt(attrList.get(1));
        int level = Integer.parseInt(attrList.get(2));
        int effect = Integer.parseInt(attrList.get(3));

        switch (itemType) {
            case "weapon":
                int hand = Integer.parseInt(attrList.get(4));
                return new Weapon(name, price, level, effect, hand);
            case "armor":
                return new Armor(name, price, level, effect);
            case "potion":
                String attributes = attrList.get(4);
                return new Potion(name, price, level, effect, attributes);
            default:
                int mana = Integer.parseInt(attrList.get(4));
                String spellType = attrList.get(5);
                int spellQuantity = Integer.parseInt(attrList.get(6));
                return new Spell(name, price, level, effect, mana, spellType, spellQuantity);
        }
    }

    public Item randomGenerate()
    {
        Random rand = new Random();
        int randIndex = rand.nextInt(weaponList.size()+armorList.size()+potionList.size()+spellList.size());
        if (randIndex >= weaponList.size()+armorList.size()+potionList.size()) {
            return generateItem(randIndex-(weaponList.size()+armorList.size()+potionList.size()), "spell");
        }
        else if (randIndex >= weaponList.size()+armorList.size()) {
            return generateItem(randIndex-(weaponList.size()+armorList.size()), "potion");
        }
        else if (randIndex >= weaponList.size()) {
            return generateItem(randIndex-weaponList.size(), "armor");
        }
        else {
            return generateItem(randIndex, "weapon");
        }
    }
}
