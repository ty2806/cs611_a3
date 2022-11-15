/*
 *  It is a collection of all monsters on map.
 */

package character;

import java.util.ArrayList;

public class MonsterTeam {
    private ArrayList<Monster> team;

    public MonsterTeam() {
        this.team = new ArrayList<>();
    }

    public void addMember(Monster m)
    {
        team.add(m);
    }

    public Monster getMember(int index)
    {
        return team.get(index);
    }

    public Monster removeMember(int index)
    {
        return team.remove(index);
    }

    public int getTeamSize()
    {
        return team.size();
    }
}
