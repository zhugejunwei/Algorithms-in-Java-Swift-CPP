package io.kongming;
import java.util.*;

/**
 * Created by zhugejunwei on 2/27/17.
 */
class Cell {
    private int row, col;
    private String value;
    
    List<Cell> children = new ArrayList();
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public Cell(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String val) {
        this.value = val;
    }
    
    public List<Cell> getChildren() {
        return this.children;
    }
    
    public void addChild(Cell child) {
        children.add(child);
    }
    
    public boolean isEqual(Cell that) {
        return this.row == that.row && this.col == that.col;
    }
    
    @Override
    public boolean equals(Object o) {
        // itself
        if (o == this) {
            return true;
        }
        
        /**
         * check if o is an instance of Cell or not
         * "null instance type" also returns false
         */
        if (!(o instanceof Cell)) {
            return false;
        }
        
        Cell c = (Cell)o;
        return Integer.compare(row, c.row) == 0 && Integer.compare(col, c.col) == 0;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + row;
        hash = 31 * hash + col;
        return hash;
    }
}
