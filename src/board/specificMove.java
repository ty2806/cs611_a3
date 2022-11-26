package board;
//interface class to define specific restriction according to move type
public interface specificMove {
    public boolean specificRestriction(int[] origin, int[] destination, Tile[][] board);
}