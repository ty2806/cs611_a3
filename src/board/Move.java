package board;
//class to check if move is legal or not

import java.util.Arrays;

public class Move {

    public boolean move(int[] origin, int[] destination, Tile[][] board, boolean sr) {
        if (generalRestriction(destination, board) && sr) {
            //sets hero at new position and removes hero from original position
            board[destination[1]][destination[0]].setHero(board[origin[1]][origin[0]].getHero());
            board[origin[1]][origin[0]].setHero(null);
            return true;
        }

        System.out.println("Illegal move, please try again!");
        return false;
    }

    //method for monster move
    public void monsterMove(int[] origin, int[] destination, Tile[][] board) {
        System.out.println("monster move from " + Arrays.toString(origin) + " to " + Arrays.toString(destination));
        board[destination[1]][destination[0]].setMonster(board[origin[1]][origin[0]].getMonster());
        board[origin[1]][origin[0]].setMonster(null);
    }

    //method to check general restriction of each move type
    public boolean generalRestriction(int[] position, Tile[][] board) {
        boolean value = true;
        if (position[0] < 0 || position[0] >= 8 || position[1] < 0 || position[1] >= 8) {
            value = false;
        }

        if (!board[position[1]][position[0]].getTerrain().accessPolicy()) {
            value = false;
        }

        //no hero at destination
        Tile tile = board[position[1]][position[0]];
        if (tile.getHero() != null) {
            value = false;
        }

        //destination column should be last column
        if (position[1] != 7) {
            for (int i = -1; i <= 1; i++) {
                if (position[0] + i < 0 || position[0] + i >= 8) {
                    continue;
                }
                if (board[position[1] + 1][position[0] + i].getMonster() != null) {
                    value = false;
                    break;
                }
            }
        }
        return value;
    }
}