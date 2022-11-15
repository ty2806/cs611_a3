/*
 *  This class records all heroes' and monsters' birthplaces.
 */

package board;

public class InitialLocations {
    private int[] lane1HeroBirthplace;
    private int[] lane2HeroBirthplace;
    private int[] lane3HeroBirthplace;
    private int[] lane1MonsterBirthplace;
    private int[] lane2MonsterBirthplace;
    private int[] lane3MonsterBirthplace;

    public InitialLocations()
    {
        lane1HeroBirthplace = new int[]{0, 7};
        lane2HeroBirthplace = new int[]{3, 7};
        lane3HeroBirthplace = new int[]{6, 7};

        lane1MonsterBirthplace = new int[]{0, 0};
        lane2MonsterBirthplace = new int[]{3, 0};
        lane3MonsterBirthplace = new int[]{6, 0};
    }

    public int[] getLane1HeroBirthplace() {
        return lane1HeroBirthplace;
    }

    public int[] getLane2HeroBirthplace() {
        return lane2HeroBirthplace;
    }

    public int[] getLane3HeroBirthplace() {
        return lane3HeroBirthplace;
    }

    public int[] getLane1MonsterBirthplace() {
        return lane1MonsterBirthplace;
    }

    public int[] getLane2MonsterBirthplace() {
        return lane2MonsterBirthplace;
    }

    public int[] getLane3MonsterBirthplace() {
        return lane3MonsterBirthplace;
    }
}
