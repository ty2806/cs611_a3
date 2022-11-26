package board;

import java.util.Random;

import character.Hero;

public class LegendsOfValorBoard {

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private Tile[][] board;
    private int height;
    private int width;

    public LegendsOfValorBoard(int h, int w) {
        setHeight(h);
        setWidth(w);
        board = new Tile[h][w];
    }

    private void setWidth(int w) {
        this.width = w;
    }

    private void setHeight(int h) {
        this.height = h;
    }

    public LegendsOfValorBoard(int size) {
        this(size, size);
    }

    public Tile getCell(int row, int col) {
        return board[row][col];
    }

    public void addHero(Hero hero) {
        int[] loc = hero.getLocation();
        board[loc[1]][loc[0]].setHero(hero);
    }

    //method to increase and reset hero attributes
    public void buffHero(Hero hero) {

        hero.setStrength(hero.getOriginalStrength());
        hero.setOriginalDexterity(hero.getDexterity());
        hero.setOriginalAgility(hero.getOriginalAgility());

        int[] loc = hero.getLocation();
        String buff = board[loc[1]][loc[0]].getTerrain().IncreaseAbility();
        switch (buff) {
            case "Strength":
                hero.setStrength((int) (hero.getStrength() * 1.1));
                System.out.println("hero receives a strength bonus on current terrain.");
                break;
            case "Agility":
                hero.setAgility((int) (hero.getAgility() * 1.1));
                System.out.println("hero receives an agility bonus on current terrain.");
                break;
            case "Dexterity":
                hero.setDexterity((int) (hero.getDexterity() * 1.1));
                System.out.println("hero receives an dexterity bonus on current terrain.");
                break;
            default:
                break;
        }
    }

    //method for moving hero
    public boolean makeMoveHero(int[] origin, int[] destination, String moveType) {
        boolean specialRestriction = false;
        switch (moveType) {
            case "Teleport":
                Teleport TEL = new Teleport();
                specialRestriction = TEL.specificRestriction(origin, destination, board);
                break;
            case "Recall":
                Recall RECALL = new Recall();
                specialRestriction = RECALL.specificRestriction(origin, destination, board);
                break;
            case "Regular":
                RegularMove RM = new RegularMove();
                specialRestriction = RM.specificRestriction(origin, destination, board);
                break;
        }
        Move move = new Move();
        boolean moveResult = move.move(origin, destination, board, specialRestriction);
        return moveResult;
    }

    //method for moving monster
    public void makeMoveMonster(int[] origin, int[] destination) {
        Move move = new Move();
        move.monsterMove(origin, destination, board);
    }

    //method to check range of attack
    public boolean isInAttackRange(int[] a, int[] b) {
        return Math.abs(b[0] - a[0]) <= 1 && Math.abs(b[1] - a[1]) <= 1;
    }

    public boolean isMarket(int x, int y) {
        return board[y][x].getTerrain().MarketPolicy();
    }

    //printing board
    public String toString() {
        String gameBoard = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Tile lovcell = board[i][j];
                if (!(lovcell == null)) {
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("V"))
                        gameBoard += ANSI_RED_BACKGROUND + ANSI_BLACK;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("H"))
                        gameBoard += ANSI_GREEN_BACKGROUND + ANSI_BLACK;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("X"))
                        gameBoard += ANSI_BLACK_BACKGROUND + ANSI_WHITE;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase(" "))
                        gameBoard += ANSI_WHITE_BACKGROUND + ANSI_BLACK;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("K"))
                        gameBoard += ANSI_BLUE_BACKGROUND + ANSI_BLACK;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("C"))
                        gameBoard += ANSI_PURPLE_BACKGROUND + ANSI_BLACK;
                    if (lovcell.toString().substring(0, 1).equalsIgnoreCase("B"))
                        gameBoard += ANSI_CYAN_BACKGROUND + ANSI_BLACK;
                    gameBoard += lovcell.toString() + "" + ANSI_RESET;
                } else
                    gameBoard += " ";
            }
            gameBoard += '\n';
        }
        return gameBoard;
    }

    //initialising the board
    public void initBoard() {
        Random random = new Random();
        //Tile[][] board = new Tile[default_size][default_size];
        int num;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                if (j == 2 || j == 5) {
                    board[i][j] = new Tile(new LOVInaccesibleCell());
                } else {
                    if (i == height - 1) {
                        board[i][j] = new Tile(new LOVHeroNexus());
                    } else if (i == 0)
                        board[i][j] = new Tile(new LOVMonsterNexus());
                    else {
                        num = random.nextInt(10);
                        if (num < 2)
                            board[i][j] = new Tile(new LOVKoulouCell());
                        else if (num < 4)
                            board[i][j] = new Tile(new LOVBushCell());
                        else if (num < 6)
                            board[i][j] = new Tile(new LOVCaveCell());
                        else
                            board[i][j] = new Tile(new LOVPlainsCell());
                    }
                }
            }
        }
    }
}