/*
 *  It contains 1 weapon slot and 1 armor slot of a hero.
 */

package inventory;

public class Equipment {
    private Weapon weaponSlot;
    private Armor armorSlot;

    public Equipment()
    {
        weaponSlot = new Weapon("bare fists", 0, 1, 1, 2);
        armorSlot = new Armor("cotton clothes", 0, 1, 1);
    }

    public void equipWeapon(Weapon w)
    {
        weaponSlot = w;
    }

    public void equipArmor(Armor a)
    {
        armorSlot = a;
    }

    public Weapon unequipWeapon()
    {
        Weapon w = weaponSlot;
        weaponSlot = null;
        return w;
    }

    public Armor unequipArmor()
    {
        Armor a = armorSlot;
        armorSlot = null;
        return a;
    }

    public Weapon getWeaponSlot()
    {
        if (weaponSlot == null) {
            return new Weapon("empty", 0, 1, 0, 1);
        }
        return weaponSlot;
    }

    public Armor getArmorSlot()
    {
        if (armorSlot == null) {
            return new Armor("empty", 0, 1, 0);
        }
        return armorSlot;
    }

    public String toString()
    {
        return "Equipment: Weapon:"+getWeaponSlot() + " Armor:"+getArmorSlot();
    }
}
