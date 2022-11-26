package board;

public class Recall extends Move implements specificMove {

    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {

        /*int lane = destination[0] < 2 ? 1 : (destination[0] < 5 ? 2 : 3);
        if (lane == board[origin[1]][origin[0]].getHero().getLane())
            return true;*/
        //there should be no hero at the recall destination
        if (board[destination[1]][destination[0]].getHero() != null)
            return false;
        return true;
    }
}