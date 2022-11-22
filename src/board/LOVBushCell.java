package board;

import character.Hero;

public class LOVBushCell extends Terrain {
    // the class represent bush cells which extend LOVCell
    public LOVBushCell() {
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
        return "Dexterity";
    }

    @Override
    public String toString() {
        return "B";
    }
}