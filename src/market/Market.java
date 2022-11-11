/*
 *  It represents a market and contains methods needed for heroes to trade.
 */

package market;

import inventory.*;
import character.Hero;

import java.util.ArrayList;

public class Market {
    private ArrayList<Item> products;
    private final double resellRate = 0.5;

    public Market()
    {
        products = new ArrayList<>();
    }

    public Market(ItemGenerator generator, int number)
    {
        this();
        generateProduct(generator, number);
    }

    public void addProduct(Item p)
    {
        products.add(p);
    }

    public Item getProduct(int index)
    {
        return products.get(index);
    }

    public Item removeProduct(int index)
    {
        return products.remove(index);
    }

    public int getMarketSize()
    {
        return products.size();
    }

    public void sell(Hero buyer, int index)
    {
        Item product = getProduct(index);
        if (product.getLevel() > buyer.getLevel()) {
            System.out.println(buyer.getName() + "(" + buyer.getLevel() + ")" + " do not have enough level to buy " + product.getName() + "(" + product.getLevel() + ")");
            return;
        }

        if (product.getPrice() > buyer.getGold()) {
            System.out.println(buyer.getName() + "(" + buyer.getGold() + ")" + " do not have enough money to buy " + product.getName() + "(" + product.getPrice() + ")");
            return;
        }

        buyer.loseGold(product.getPrice());

        removeProduct(index);
        product.setPrice((int) Math.round(product.getPrice()*resellRate));
        buyer.getBag().addItem(product);
        System.out.println(buyer.getName() + " purchased " + product.getName() + "(" + product.getLevel() + ")");
    }

    public void purchase(Hero seller, Item product)
    {
        seller.gainGold(product.getPrice());
        product.setPrice((int) Math.round((double) product.getPrice()/resellRate));
        addProduct(product);
        System.out.println(seller.getName() + " sold " + product.getName() + "(" + product.getLevel() + ")");
    }

    public void generateProduct(ItemGenerator generator, int number)
    {
        for (int i = 0; i < number; i ++) {
            addProduct(generator.randomGenerate());
        }
    }

    public void printMarket()
    {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        for (int i = 0; i < products.size(); i ++) {
            Item item = products.get(i);
            System.out.println(i + " " + item);
        }
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

}
