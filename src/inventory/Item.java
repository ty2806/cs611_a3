/*
 *  An abstract item class. It contains all methods necessary for all items.
 */

package inventory;

public class Item {
    private String name;
    private int price;
    private int level;

    public Item(String name, int price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString()
    {
        return "Name:"+getName() + " Price:"+getPrice() + " Level:"+getLevel();
    }
}
