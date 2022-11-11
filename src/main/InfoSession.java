/*
 *  It manages info display.
 */


package main;

import character.HeroTeam;
import util.InputParser;

public class InfoSession {

    public void showInfo(InputParser parser, HeroTeam heroes)
    {
        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            System.out.println(heroes.getMember(i));
        }

        System.out.println("Press Q to quit information listing");
        String[] allowedWords = {"Q", "q"};
        parser.parseInputToString(allowedWords);
    }
}
