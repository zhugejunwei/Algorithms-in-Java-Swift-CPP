package io.kongming;

/**
 * Created by zhugejunwei on 12/4/16.
 */
public class Board {
    // constants for the dimensions
    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final int LEVELS = 4;

    Cell[][][] cells; // cell instances
    int currentRow = -1, currentCol = -1, currentLevel = -1; // the current seed's row, column, level

    public Board() {
        cells = new Cell[ROWS][COLS][LEVELS]; // allocate the board array
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                for (int level = 0; level < LEVELS; ++level) {
                    cells[row][col][level] = new Cell(row, col, level);
                }
            }
        }
    }

    // initialize the board
    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                for (int level = 0; level < LEVELS; ++level) {
                    cells[row][col][level].clear();
                }
            }
        }
    }

    // return true if it is a draw
    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                for (int level = 0; level < LEVELS; ++level) {
                    if (cells[row][col][level].content == Seed.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true; // no empty cell
    }

    // return true if the player with "theSeed" has won after placing at current position
    public boolean hasWon(Seed theSeed) {
        // 16 * 3 + 8 * 3 + 4 = 76
        return (// same 4 on the current row, 16
                cells[currentRow][0][currentLevel].content == theSeed
                    && cells[currentRow][1][currentLevel].content == theSeed
                    && cells[currentRow][2][currentLevel].content == theSeed
                    && cells[currentRow][3][currentLevel].content == theSeed
                // same 4 on the current col, 16
                || cells[0][currentCol][currentLevel].content == theSeed
                    && cells[1][currentCol][currentLevel].content == theSeed
                    && cells[2][currentCol][currentLevel].content == theSeed
                    && cells[3][currentCol][currentLevel].content == theSeed
                // same 4 on the current row and col, 16
                || cells[currentRow][currentCol][0].content == theSeed
                    && cells[currentRow][currentCol][1].content == theSeed
                    && cells[currentRow][currentCol][2].content == theSeed
                    && cells[currentRow][currentCol][3].content == theSeed
                // same 4 on the current row-col diagonal, 8
                || currentRow == currentCol
                        && cells[0][0][currentLevel].content == theSeed
                        && cells[1][1][currentLevel].content == theSeed
                        && cells[2][2][currentLevel].content == theSeed
                        && cells[3][3][currentLevel].content == theSeed
                        || currentRow + currentCol == 3
                        && cells[0][3][currentLevel].content == theSeed
                        && cells[1][2][currentLevel].content == theSeed
                        && cells[2][1][currentLevel].content == theSeed
                        && cells[3][0][currentLevel].content == theSeed
                // same 4 on the current row-level diagonal, 8
                || currentRow == currentLevel
                        && cells[0][currentCol][0].content == theSeed
                        && cells[1][currentCol][1].content == theSeed
                        && cells[2][currentCol][2].content == theSeed
                        && cells[3][currentCol][3].content == theSeed
                        || currentRow + currentLevel == 3
                        && currentRow == currentLevel
                        && cells[0][currentCol][3].content == theSeed
                        && cells[1][currentCol][2].content == theSeed
                        && cells[2][currentCol][1].content == theSeed
                        && cells[3][currentCol][0].content == theSeed
                // same 4 on the current col-level diagonal, 8
                || currentCol == currentLevel
                        && cells[currentRow][0][0].content == theSeed
                        && cells[currentRow][1][1].content == theSeed
                        && cells[currentRow][2][2].content == theSeed
                        && cells[currentRow][3][3].content == theSeed
                        || currentCol + currentLevel == 3
                        && cells[currentRow][0][3].content == theSeed
                        && cells[currentRow][1][2].content == theSeed
                        && cells[currentRow][2][1].content == theSeed
                        && cells[currentRow][3][0].content == theSeed
                // same 4 on the current row-col-level diagonal, 4
                || currentRow == currentCol && currentCol == currentLevel
                    && cells[0][0][0].content == theSeed
                    && cells[1][1][1].content == theSeed
                    && cells[2][2][2].content == theSeed
                    && cells[3][3][3].content == theSeed
                        || currentRow == currentCol && currentCol + currentLevel == 3
                        && cells[3][3][0].content == theSeed
                        && cells[2][2][1].content == theSeed
                        && cells[1][1][2].content == theSeed
                        && cells[0][0][3].content == theSeed
                        || currentRow + currentCol == 3 && currentRow == currentLevel
                        && cells[0][3][0].content == theSeed
                        && cells[1][2][1].content == theSeed
                        && cells[2][1][2].content == theSeed
                        && cells[3][0][3].content == theSeed
                        || currentRow + currentCol == 3 && currentCol == currentLevel
                        && cells[3][0][0].content == theSeed
                        && cells[2][1][1].content == theSeed
                        && cells[1][2][2].content == theSeed
                        && cells[0][3][3].content == theSeed
                );
    }

    public void paint() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                for (int level = 0; level < LEVELS; ++level) {
                    cells[row][col][level].paint();
                    if (level < ROWS - 1) System.out.print("|");
                }
                System.out.println();
                if (col < COLS - 1) {
                    System.out.println("---------------");
                }
            }
            System.out.println();
            if (row < LEVELS - 1) {
                System.out.println("##################");
            }
            System.out.println();
        }
    }
}
