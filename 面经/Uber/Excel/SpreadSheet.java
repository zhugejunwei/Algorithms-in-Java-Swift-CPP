package io.kongming;

import java.util.*;

/**
 * Created by zhugejunwei on 2/27/17.
 */
class SpreadSheet {
    private final Map<Integer, Map<Integer, Cell>> map;
    
    /**
     * constructor of SpreadSheet
     */
    public SpreadSheet() {
        map = new HashMap<>();
    }
    
    /**
     * get the value from a specific cell
     * @param row
     * @param col
     * @return
     */
    public String getValue(int row, int col) {
        Map<Integer, Cell> cellMap = map.get(row);
        if (cellMap == null)
            return "";
        
        Cell cell = cellMap.get(col);
        if (cell == null)
            return "";
        return cell.getValue();
    }
    
    /**
     * set a cell's value with a string
     * @param row
     * @param col
     * @param value
     */
    public Cell setValue(int row, int col, String value) {
        Cell newCell = new Cell(row, col, value);
        String origValue = null;
        if (!map.containsKey(row))
            map.put(row, new HashMap<>());
        else
            origValue = map.get(row).get(col).getValue();
        
        map.get(row).put(col, newCell);
        
        if (origValue != null) {
            int dif = Integer.valueOf(value) - Integer.valueOf(origValue);
            updateChildren(newCell, dif);
        }
        return newCell;
    }
    
    /**
     * update values of children cells if there are cells refer to the cell
     * @param cell
     * @param dif
     */
    private void updateChildren(Cell cell, int dif) {
        Queue<Cell> q = new ArrayDeque();
        q.offer(cell);
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            for (Cell next : cur.getChildren()) {
                int newInt = Integer.valueOf(next.getValue()) + dif;
                String newStr = String.valueOf(newInt);
                next.setValue(newStr);
                q.offer(next);
            }
        }
    }
    
    /**
     * calculate two cells' values based on some operation
     * @param s1
     * @param s2
     * @param op
     * @return an integer
     */
    public int cal(String s1, String s2, char op) {
        int a = Integer.valueOf(s1);
        int b = Integer.valueOf(s2);
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0;
        }
    }
    
    /**
     * set a cell's value based on other cells'value, thus, the new cell refers to those cells
     * @param row
     * @param col
     * @param c1
     * @param c2
     * @param operator
     */
    public Cell setValue(int row, int col, Cell c1, Cell c2, char operator) {
        String s1 = c1.getValue(), s2 = c2.getValue();
        if (s1 == null) s1 = "0";
        if (s2 == null) s2 = "0";
        int res = cal(s1, s2, operator);
        Cell newCell = new Cell(row, col, String.valueOf(res));
        if (!map.containsKey(row) || !map.get(row).containsKey(col))
            map.put(row, new HashMap());
        else {
            newCell = map.get(row).get(col);
            if (hasCycle(newCell, c1, c2)) {
                // check whether there could be a cycle reference
                System.out.println("Error! There is a cycle reference!");
                
                // do something
                // ...
                
                return null;
            }
        }
        
        map.get(row).put(col, newCell);
        
        c1.addChild(newCell);
        c2.addChild(newCell);
        return newCell;
    }
    
    /**
     * check whether there is a reference from child cell to one of the parent cells.
     * @param child
     * @param p1
     * @param p2
     * @return true if there is a cycle reference, false if there is no cycle
     */
    private boolean hasCycle(Cell child, Cell p1, Cell p2) {
        Queue<Cell> q = new ArrayDeque();
        q.add(child);
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            for (Cell next : cur.getChildren()) {
                if (next.isEqual(p1) || next.isEqual(p2)) {
                    return true;
                }
                q.add(next);
            }
        }
        return false;
    }
    
    /**
     * delete a row
     */
    public void removeRow(int row) {
        if (!map.containsKey(row))
            return;
        Map<Integer, Cell> cellMap = map.get(row);
        for (int key : cellMap.keySet()) {
            Cell cur = cellMap.get(key);
            int val = Integer.valueOf(cur.getValue());
            updateChildren(cur, -val);
            cellMap.remove(key);
        }
        map.remove(row);
    }
    
    /**
     * delete a column
     */
    public void removeCol(int col) {
        for (int row : map.keySet()) {
            Map<Integer, Cell> cellMap = map.get(row);
            for (int c : map.get(row).keySet()) {
                if (c == col) {
                    Cell cell = cellMap.get(col);
                    int val = Integer.valueOf(cell.getValue());
                    updateChildren(cell, -val);
                    cellMap.remove(col);
                }
            }
        }
    }
}
