/*
 *  It represents a hero's inventory. It contains a list of items.
 */

package inventory;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventory;

    public Inventory()
    {
        inventory = new ArrayList<>();
    }

    public Item getItem(int index)
    {
        return inventory.get(index);
    }

    public void addItem(Item i)
    {
        inventory.add(i);
    }

    public Item removeItem(int index)
    {
        return inventory.remove(index);
    }

    public int getInventorySize()
    {
        return inventory.size();
    }

    public void printInventory()
    {
        System.out.println("===============================================================================");
        for (int i = 0; i < inventory.size(); i ++) {
            Item item = inventory.get(i);
            System.out.println(i + " " + item);
        }
        System.out.println("===============================================================================");
    }
}
