package board;

import character.Hero;

public class LOVPlainsCell extends Terrain {
    // the class represent plain cells which extend LOVCell
    public LOVPlainsCell() {
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
        return null;
    }
    @Override
    public String toString(){
        return " ";
    }
}