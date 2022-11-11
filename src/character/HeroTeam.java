/*
 *  It represents a list of heroes.
 */

package character;

import java.util.ArrayList;

public class HeroTeam {
    private ArrayList<Hero> team;
    private int highest;
    private int lowest;

    public HeroTeam()
    {
        team = new ArrayList<>();
        highest = 1;
        lowest = 1;
    }

    public void addMember(Hero h)
    {
        team.add(h);
    }

    public Hero getMember(int index)
    {
        return team.get(index);
    }

    public Hero removeMember(int index)
    {
        return team.remove(index);
    }

    public int getHighest() {
        return highest;
    }

    public int getLowest() {
        return lowest;
    }

    public int getTeamSize()
    {
        return team.size();
    }

    public void updateLevelExtreme()
    {
        lowest = team.get(0).getLevel();
        for (Hero hero : team) {
            if (hero.getLevel() > highest) {
                highest = hero.getLevel();
            }

            if (hero.getLevel() < lowest) {
                lowest = hero.getLevel();
            }
        }
    }
}
