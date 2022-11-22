package board;

public class LOVHeroNexus extends Terrain {

    public LOVHeroNexus() {
    }

    @Override
    public boolean accessPolicy() {
        return true;
    }

    @Override
    public boolean encounterEnemyPolicy() {
        return false;
    }

    @Override
    public boolean MarketPolicy() {
        return true;
    }

    @Override
    public String IncreaseAbility() {
        return "";
    }
    @Override
    public String toString(){
        return "H";
    }
}