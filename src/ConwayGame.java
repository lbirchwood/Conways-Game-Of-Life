public interface ConwayGame {

    /**
     * Returns board as an boolean array, where either an item is true or false depending on if it
     * alive or dead
     */
    public boolean[][] getBoard();

    /**
     * Create board with the dimensions of the board
     *
     * @param num
     */
    public void setBoard(int num);

    /**
     * Toggles the game on/off
     */
    public void toggleGame();

    /**
     * Sets toggles a piece alive or dead
     *
     * @param x
     * @param y
     */
    public void togglePiece(int x, int y);

}
