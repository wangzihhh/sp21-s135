package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;
        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);
        int size = board.size();
        int cnt = 0;
        for (int c = 0; c < size; c += 1) {
            int unit_tilt = tilt_helper(c);
            cnt += unit_tilt;
        }
        board.setViewingPerspective(Side.NORTH);
        changed = cnt > 0;
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }



    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        boolean result = false;
        int size = b.size();
        /** Iterate rows of the board. */
        for (int r = 0; r < size; r += 1) {
            /** Iterate columns of the board. */
            for (int c = 0; c < size; c += 1) {
                Tile cur_Tile = b.tile(c, r);
                if (isEmpty(cur_Tile)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        /** Iterate the rows. */
        for (int r = 0; r < b.size(); r += 1) {
            /** Iterate the columns. */
            for (int c = 0; c < b.size(); c += 1) {
                Tile cur_Tile = b.tile(c, r);
                if (isEmpty(cur_Tile)) {
                    continue;
                }
                int val = cur_Tile.value();
                if (val == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        return emptySpaceExists(b) || adjacentEquals(b);

    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }

    /** Return true if tile is empty. */
    public static boolean isEmpty(Tile tile) {
        return tile == null;
    }

    /** Return true if there are two adjacent tiles with the same value, with the assumption
     *  that all the tiles in the board are not empty
     */
    public static boolean adjacentEquals(Board b) {
        /** handle the case for left right equals. */
        for (int r = 0; r < b.size(); r += 1) {
            for (int c = 0; c < b.size() - 1; c += 1) {
                if (b.tile(c, r).value() == b.tile(c + 1, r).value()) {
                    return true;
                }
            }
        }
        /** handle the case for up down equals. */
        for (int r = 0; r < b.size() - 1; r += 1) {
            for (int c = 0; c < b.size(); c += 1) {
                if (b.tile(c, r).value() == b.tile(c, r + 1).value()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**This method return the row number to which (c,r) piece will tilt in up direction
     * when merging is not considered. */
    public int simpleTiltPos(int c, int r) {
        int size = board.size();
        if (r == size - 1) {
            return (size - 1);
        }
        if (isEmpty(board.tile(c, r + 1))) {
            return simpleTiltPos(c, r + 1);
        }
        return r;
    }

    /** This method return the row number to which (c,r) piece will tilt to in up direction
     *  when merging is considered. */
    public int complexTiltPos(int c, int r, int merged_recorder) {
        int initial_guess = simpleTiltPos(c, r);
        if (initial_guess == board.size() - 1) {
            return initial_guess;
        }
        int cur_val = board.tile(c, r).value();
        int cmp_val = board.tile(c, initial_guess + 1).value();
        if (cur_val == cmp_val) {
            if (merged_recorder == initial_guess + 1) {
                return initial_guess;
            }
            return initial_guess + 1;
        }
        return initial_guess;
    }

    /** This method apply tilt functionality in a specific column in the board.  */
    public int tilt_helper(int c) {
        int merged_recorder = -1;
        int size = board.size();
        int changed_indicator = 0;
        for (int r = size - 1; r >= 0; r -= 1) {
            Tile t = board.tile(c, r);
            if (isEmpty(t)) {
                continue;
            }
            int destination = complexTiltPos(c, r, merged_recorder);
            if (destination == r) {
                continue;
            }
            changed_indicator += 1;
            boolean move = board.move(c, destination, t);
            if (move) {
                score += board.tile(c, destination).value();
                merged_recorder = destination;
            }
        }
        return changed_indicator;
    }
    }

