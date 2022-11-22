package board;

import character.Hero;

public class LOVCaveCell extends Terrain {
    // the class represent cave cells which extend LOVCell

    public LOVCaveCell() {
    }

    @Override
    public boolean accessPolicy() {
        return true;
    }

    @Override
    public boolean encounterEnemyPolicy() {
        return true;
    }

    @Override
    public boolean MarketPolicy() {
        return false;
    }

    @Override
    public String IncreaseAbility() {
        return "Agility";
    }

    @Override
    public String toString(){
        return "C";
    }
}