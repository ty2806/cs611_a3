/*
 *  A factory class responsible for generate monsters by reading data files.
 */

package character;

import java.util.*;
import util.DataParser;

public class MonsterGenerator {
    private String dragonFile;
    private String exoskeletonFile;
    private String spiritFile;
    private ArrayList<ArrayList<String>> dragonList;
    private ArrayList<ArrayList<String>> exoskeletonList;
    private ArrayList<ArrayList<String>> spiritList;

    public MonsterGenerator(String dragonFile, String exoskeletonFile, String spiritFile) {
        this.dragonFile = dragonFile;
        this.exoskeletonFile = exoskeletonFile;
        this.spiritFile = spiritFile;
        dragonList = DataParser.parse(dragonFile);
        exoskeletonList = DataParser.parse(exoskeletonFile);
        spiritList = DataParser.parse(spiritFile);
    }

    public ArrayList<ArrayList<String>> getDragonList() {
        return dragonList;
    }

    public ArrayList<ArrayList<String>> getExoskeletonList() {
        return exoskeletonList;
    }

    public ArrayList<ArrayList<String>> getSpiritList() {
        return spiritList;
    }

    public Monster generateMonster(int index, String monsterType) {
        ArrayList<String> attrList;
        if (monsterType.equals("dragon")) {
            attrList = dragonList.get(index);
        } else if (monsterType.equals("exoskeleton")) {
            attrList = exoskeletonList.get(index);
        } else {
            attrList = spiritList.get(index);
        }

        String name = attrList.get(0);
        int level = Integer.parseInt(attrList.get(1));
        int damage = Integer.parseInt(attrList.get(2));
        int defence = Integer.parseInt(attrList.get(3));
        int dodge = Integer.parseInt(attrList.get(4));

        if (monsterType.equals("dragon")) {
            return new Dragon(name, LevelPolicy.updateHealth(level), level, damage, defence, dodge);
        } else if (monsterType.equals("exoskeleton")) {
            return new Exoskeleton(name, LevelPolicy.updateHealth(level), level, damage, defence, dodge);
        } else {
            return new Spirit(name, LevelPolicy.updateHealth(level), level, damage, defence, dodge);
        }
    }

    public Monster randomGenerate()
    {
        Random rand = new Random();
        int randIndex = rand.nextInt(dragonList.size()+exoskeletonList.size()+spiritList.size());
        if (randIndex >= dragonList.size()+exoskeletonList.size()) {
            return generateMonster(randIndex-(dragonList.size()+exoskeletonList.size()), "spirit");
        }
        else if (randIndex >= dragonList.size()) {
            return generateMonster(randIndex-dragonList.size(), "exoskeleton");
        }
        else {
            return generateMonster(randIndex, "dragon");
        }
    }

}
