/*
 *  The GameBoard class is an abstraction of general 2 dimensional game board. It is intended to be inherited by more specific games.
 *  It stores the height, width and area info of the 2 dimensional board.
 */

package board;

import character.Hero;

import java.util.Random;

public class Board {
    private Tile[][] board;
    private int height;
    private int width;
    private int area;

    public Board(int h, int w) {
        setHeight(h);
        setWidth(w);
        calculateArea();
        board = new Tile[h][w];
    }

    public Board(int size) {
        this(size, size);
    }

    public void setWidth(int w) {
        if (w < 0) {
            throw new IllegalArgumentException();
        }
        width = w;
    }

    // return width
    public int getWidth() {
        return width;
    }

    // set the height attribute
    public void setHeight(int h) {
        if (h < 0) {
            throw new IllegalArgumentException();
        }
        height = h;
    }

    // return height
    public int getHeight() {
        return height;
    }

    // calculate area by multiplying height and width
    public void calculateArea() {
        area = height * width;
    }

    // return area of the board
    public int getArea() {
        return area;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void initBoard(double inaccessibleRatio, double marketRatio) {
        Random marketRand = new Random();
        Random inaccessibleRand = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (marketRand.nextDouble() < marketRatio) {
                    board[i][j] = new Tile(new MarketTerrain());
                }
                // we divide inaccessibleRatio by (1 - marketRatio) to compensate its prob loss by else if
                else if (inaccessibleRand.nextDouble() < (inaccessibleRatio / (1 - marketRatio))) {
                    board[i][j] = new Tile(new InaccessibleTerrain());
                } else {
                    board[i][j] = new Tile(new CommonTerrain());
                }
            }
        }
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }

        return board[y][x].getTerrain().accessPolicy();
    }

    public boolean isInAttackRange(int[] a, int[] b) {
        return Math.abs(b[0] - a[0]) <= 1 && Math.abs(b[1] - a[1]) <= 1;
    }

    public boolean isEncounterMonster(int x, int y) {
        return board[y][x].getTerrain().encounterEnemyPolicy();
    }

    public boolean isMarket(int x, int y) {
        return board[y][x].getTerrain().MarketPolicy();
    }

    public void setupBirthplace(Hero hero, int x, int y) {
        board[y][x].setHero(hero);
        board[y][x].setTerrain(new CommonTerrain());
    }

    public void move(int x1, int y1, int x2, int y2) {
        board[y2][x2].setHero(board[y1][x1].getHero());
        board[y1][x1].setHero(null);
    }

    public void printBoard() {
        for (int i = 0; i < height * 2 + 1; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 != 0) {
                    System.out.print("| " + board[(i - 1) / 2][j] + " ");
                } else {
                    System.out.print("+---");
                }
            }
            if (i % 2 != 0) {
                System.out.print("|");
            } else {
                System.out.print("+");
            }
            System.out.println();
        }
        System.out.println("X = inaccessible, $ = market, P = heroes");
    }

}
