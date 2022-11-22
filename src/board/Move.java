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
        Tile tile = board[position[1]][position[0]];
        if (tile.getHero() != null) {
            return false;
        }

        if (board[position[1]][position[0]].getTerrain() instanceof InaccessibleTerrain)
            return false;

        // if (position[1] == 2 || position[1] == 5)

        // TODO: 20-11-2022 add other general res
        return true;
    }
}