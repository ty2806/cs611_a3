package board;

public class LOVMonsterNexus extends Terrain {
    // the class represent Monster Nexus cells which extend LOVCell
    public LOVMonsterNexus() {}

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
    public String toString()
    {
        return "V";
    }
}