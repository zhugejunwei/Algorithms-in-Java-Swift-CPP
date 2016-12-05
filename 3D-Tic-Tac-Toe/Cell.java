package io.kongming;

/**
 * Created by zhugejunwei on 12/4/16.
 */
public class Cell {
    Seed content;

    int row, col, level;

    public Cell(int row, int col, int depth) {
        this.row = row;
        this.col = col;
        this.level = depth;
        clear();
    }

    public void clear() {
        content = Seed.EMPTY;
    }

    public void paint() {
        switch(content) {
            case X:
                System.out.print(" X "); break;
            case O:
                System.out.print(" O "); break;
            case EMPTY:
                System.out.print("   "); break;
        }
    }
}
