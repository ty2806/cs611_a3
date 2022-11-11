/*
 *  It manages all interactions in a market.
 */

package main;

import board.Board;
import character.Hero;
import character.HeroTeam;
import inventory.ItemGenerator;
import market.Market;
import util.InputParser;

import java.util.Random;

public class MarketSession {
    private ItemGenerator itemGenerator;
    private Random rand;

    public MarketSession(ItemGenerator itemGenerator) {
        this.itemGenerator = itemGenerator;
        this.rand = new Random();
    }

    public void runMarket(InputParser parser, HeroTeam heroes, Board map)
    {
        Market market = new Market(itemGenerator, rand.nextInt(10)+10);
        market.printMarket();
        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            Hero hero = heroes.getMember(i);
            trade(parser, hero, market, map);
        }
    }

    public void trade(InputParser parser, Hero customer, Market market, Board map)
    {
        while (true) {
            System.out.println("customer: " + customer.getName() + " level:"+customer.getLevel() + " gold:"+customer.getGold());
            System.out.println("S/s:sell items in your inventory to market|B/b:buy items from market|Q/q:leave market|M/m: display map");
            String[] allowedWords = {"Q", "q", "S", "s", "B", "b", "M", "m"};
            parser.parseInputToString(allowedWords);
            String command = parser.getParsedString().toLowerCase();
            switch (command) {
                case "q":
                    return;
                case "s":
                    sellInventory(parser, customer, market);
                    break;
                case "b":
                    buyFromMarket(parser, customer, market);
                    break;
                case "m":
                    map.printBoard();
                    break;
            }
        }
    }

    public void sellInventory(InputParser parser, Hero seller, Market market)
    {
        while (true) {
            seller.getBag().printInventory();
            int bagSize = seller.getBag().getInventorySize();

            System.out.println("Enter a number before each item to sell or enter q to quit selling");

            String[] allowedWords = new String[bagSize+2];
            for (int i = 0; i < bagSize; i ++) {
                allowedWords[i] = String.valueOf(i);
            }
            allowedWords[bagSize] = "Q";
            allowedWords[bagSize+1] = "q";

            parser.parseInputToString(allowedWords);
            String command = parser.getParsedString().toLowerCase();

            if (command.equals("q")) {
                break;
            }
            else {
                int index = Integer.parseInt(command);
                market.purchase(seller, seller.getBag().getItem(index));
            }
        }
    }

    public void buyFromMarket(InputParser parser, Hero buyer, Market market)
    {
        while (true) {
            market.printMarket();
            int marketSize = market.getMarketSize();

            System.out.println("Enter a number before each item to buy or enter q to quit buying");

            String[] allowedWords = new String[marketSize+2];
            for (int i = 0; i < marketSize; i ++) {
                allowedWords[i] = String.valueOf(i);
            }
            allowedWords[marketSize] = "Q";
            allowedWords[marketSize+1] = "q";

            parser.parseInputToString(allowedWords);
            String command = parser.getParsedString().toLowerCase();

            if (command.equals("q")) {
                break;
            }
            else {
                int index = Integer.parseInt(command);
                market.sell(buyer, index);
            }
        }
    }
}
