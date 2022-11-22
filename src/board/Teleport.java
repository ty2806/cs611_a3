package board;

public class Teleport extends Move implements specificMove {

    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {
        boolean value = true;
        if (board[destination[1]][destination[0]].getHero() != null)
            value = false;
        else {
            if (board[destination[1]][destination[0] + 1].getHero() == null && board[destination[1]][destination[0] - 1].getHero() == null && board[destination[1] - 1][destination[0]].getHero() == null) {
                value = false;
            }
        }
        return value;
    }
}