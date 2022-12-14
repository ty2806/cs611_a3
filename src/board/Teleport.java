package board;

public class Teleport extends Move implements specificMove {

    @Override
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board) {
        boolean value = false;
        //condition to check that columns can only be positive and have a value
        if (Math.abs(destination[0] - origin[0]) > 1) {
            //checking if there is a hero adjacent to the position of the destination position
            if (destination[0] != 7 && board[destination[1]][destination[0] + 1].getHero() != null) {
                value = true;
            }
            if (destination[0] != 0 && board[destination[1]][destination[0] - 1].getHero() != null) {
                value = true;
            }
            if (destination[1] != 0 && board[destination[1] - 1][destination[0]].getHero() != null) {
                value = true;
            }
            if (board[destination[1]][destination[0]].getHero() != null) {
                value = false;
            }
        }
        return value;
    }
}