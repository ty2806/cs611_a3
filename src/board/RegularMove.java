package board;

public class RegularMove extends Move implements specificMove{
    //regular move has no restriction type
    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {
        return true;
    }
}