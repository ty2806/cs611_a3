package board;

public class RegularMove extends Move implements specificMove{

    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {
        return true;
    }
}
