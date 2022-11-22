package board;

public class Teleport extends Move implements specificMove {

    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {
        boolean value;
        if (board[destination[1]][destination[0]].getHero() == null)
            value = false;
        else {
            if (board[destination[1] + 1][destination[0]].getHero() == null)
                value = false;
            else if (board[destination[1] - 1][destination[0]].getHero() == null) {
                value = false;
            } else if (board[destination[1]][destination[0] + 1].getHero() == null) {
                value = false;
            } else value = true;
        }
        return value;
    }
}