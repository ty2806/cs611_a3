package board;

import character.Hero;

public class LOVKoulouCell extends Terrain {

    // the class represent Koulou cells which extend Terrain
    public LOVKoulouCell() {
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
        return "Strength";
    }

    @Override
    public String toString() {
        return "K";
    }
}