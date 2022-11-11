/*
 *  It reads terminal inputs and checks their validity and return a proper java data type.
 */

package util;
import java.util.Scanner;

public class InputParser {
    private Scanner input;
    private String parsedString;
    private int parsedInt;
    private boolean parsedBoolean;

    public InputParser(Scanner sc)
    {
        input = sc;
    }

    public void setParsedString(String s)
    {
        parsedString = s;
    }

    public String getParsedString()
    {
        return parsedString;
    }

    public void setParsedInt(int i)
    {
        parsedInt = i;
    }

    public int getParsedInt()
    {
        return parsedInt;
    }

    public void setParsedBoolean(boolean b)
    {
        parsedBoolean = b;
    }

    public boolean getParsedBoolean()
    {
        return parsedBoolean;
    }

    public void setScanner(Scanner sc)
    {
        input = sc;
    }

    public Scanner getScanner()
    {
        return input;
    }

    public void parseInputToInt(int low, int high)
    {
        while (true) {
            String line = input.nextLine();

            // check if the input is a number and in the range of [low, high]
            try {
                int parsed = Integer.parseInt(line);
                if (parsed < low || parsed > high) {
                    System.out.println("Your input is out of boundary. Please choose an integer between " + low + " and " + high);
                    continue;
                }
                else {
                    setParsedInt(parsed);
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Your input is not an integer. Try again.");
            }

        }
    }

    public void parseInputToString(String[] allowedWords)
    {
        while (true) {
            String line = input.nextLine();
            // check if player want to exit the program

            // check if the input is one of allowed words
            if (line.matches(String.join("|", allowedWords))) {
                setParsedString(line);
                break;
            }
            else {
                System.out.println("Your input is not valid. Please only enter " + String.join(" or ", allowedWords));
            }

        }
    }

    public void parseInputToBoolean()
    {
        while (true) {
            String line = input.nextLine();

            // check if the input is one of allowed words
            if (line.matches("y|yes|Y|Yes|n|no|N|No")) {
                if (line.matches("y|yes|Y|Yes")) {
                    setParsedBoolean(true);
                }
                else {
                    setParsedBoolean(false);
                }
                break;
            }
            else {
                System.out.println("Your input is not valid. Please only enter yes or no.");
            }

        }
    }
}
