/*
 *  This class is responsible for creating and starting a new game
 */

package main;
import java.util.Scanner;

public class MainMenu {

    public void start()
    {
        printWelcome();

        Scanner userInput = new Scanner(System.in);

        // create game running objects
        RunGame runner = new RunGame();

        while (true) {
            runner.run(userInput);
            System.out.println("Game restarting......");
        }
    }

    public void printWelcome()
    {
        System.out.println("Welcome to console game Monsters and Heros!");
    }
}
