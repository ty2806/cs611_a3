package board;

public class LOVInaccesibleCell extends Terrain {
    // the class represent Inaccesible cells which extend Terrain
    public LOVInaccesibleCell() {
    }

    @Override
    public boolean accessPolicy() {
        return false;
    }

    @Override
    public boolean encounterEnemyPolicy() {
        return false;
    }

    @Override
    public boolean MarketPolicy() {
        return false;
    }

    @Override
    public String IncreaseAbility() {
        return "";
    }

    @Override
    public String toString() {
        return "X";
    }
}