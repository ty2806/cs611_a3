package board;

public class Move {

    public boolean move(int[] origin, int[] destination, Tile[][] board, boolean sr) {
        if (generalRestriction(destination, board) && sr) {
            board[destination[1]][destination[0]].setHero(board[origin[1]][origin[0]].getHero());
            board[origin[1]][origin[0]].setHero(null);
            return true;
        }

        System.out.println("Illegal move, please try again!");
        return false;
    }

    public void monsterMove(int[] origin, int[] destination, Tile[][] board) {
        board[destination[1]][destination[0]].setMonster(board[origin[1]][origin[0]].getMonster());
        board[origin[1]][origin[0]].setMonster(null);
    }

    public boolean generalRestriction(int[] position, Tile[][] board) {
        if (position[0] < 0 || position[0] >= 8 || position[1] < 0 || position[1] >= 8) {
            return false;
        }

        if (!board[position[1]][position[0]].getTerrain().accessPolicy()) {
            return false;
        }

        Tile tile = board[position[1]][position[0]];
        if (tile.getHero() != null) {
            return false;
        }

        if (board[position[1] - 1][position[0]].getMonster() == null || board[position[1] - 1][position[0] - 1].getMonster() == null || board[position[1] - 1][position[0] + 1].getMonster() == null) {
            return false;
        }

        return true;
        // TODO: 20-11-2022 add other general res
    }
}