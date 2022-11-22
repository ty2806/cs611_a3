/*
 *  It manages info display.
 */


package main;

import character.HeroTeam;
import character.MonsterTeam;
import util.InputParser;

public class InfoSession {

    public void showInfo(InputParser parser, HeroTeam heroes, MonsterTeam monsters)
    {
        for (int i = 0; i < heroes.getTeamSize(); i ++) {
            System.out.println(heroes.getMember(i));
        }

        for (int i = 0; i < monsters.getTeamSize(); i ++) {
            System.out.println(monsters.getMember(i));
        }

        System.out.println("Press Q to quit information listing");
        String[] allowedWords = {"Q", "q"};
        parser.parseInputToString(allowedWords);
    }
}
