import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");
        
        for (String string : strings) {
            System.out.println(string);
        }
    }
}

// Hello Junwei! This is Kirill from Pocket Gems. Are you ready for the phone interview?

/*
 Problem Statement
 Suppose you have a 2-D grid. Each point is either land or water. There is also a start point and a goal.
 
 There are now keys that open up doors. Each key corresponds to one door.
 
 Implement a function that returns the shortest path from the start to the goal using land tiles, keys and open doors.
 
 Data Representation
 The map will be passed as an array of strings.
 
 A map can have the following tiles.
 
 0 = Water
 1 = Land
 2 = Start
 3 = Goal
 
 uppercase = door A  0
 lowercase = key  a  0
 
 Example Maps
 `No doors`
 MAP_1 = ['02111',
 '01001',
 '01003',
 '01001',
 '01111']
 
 Solution
 (0, 1) with keys ''
 (0, 2) with keys ''
 (0, 3) with keys ''
 (0, 4) with keys ''
 (1, 4) with keys ''
 (2, 4) with keys ''
 
 `One door`
 MAP_2 = ['02a11',
 '0100A',
 '01003',
 '01001',
 '01111']
 
 Solution
 Keys needed: a
 (0, 1) with keys ''
 (0, 2) with keys ‘a’
 (0, 3) with keys 'a'
 (0, 4) with keys 'a'
 (1, 4) with keys 'a'
 (2, 4) with keys 'a'
 
 
 `Two doors`
 MAP_3 = ['021a1',    //   door: B(1) A(0)
 '00001',     //   key:  b(1) a(0)  ^
 '0111b',
 '01001',
 '01001',
 '01A1B',
 '00100',
 '00113']
 
 Solution
 Keys needed: a, b
 (0, 1) with keys ''
 (0, 2) with keys ''
 (0, 3) with keys ‘a’
 (0, 4) with keys 'a'
 (1, 4) with keys 'a'
 (2, 4) with keys 'ab’
 (3, 4) with keys 'ab'
 (4, 4) with keys 'ab'
 (5, 4) with keys 'ab'
 (5, 3) with keys 'ab'
 (5, 2) with keys 'ab'
 (6, 2) with keys 'ab'
 (7, 2) with keys 'ab'
 (7, 3) with keys 'ab'
 (7, 4) with keys 'ab'
 */

class Point {
    int row, col;
    Set<Integer> keys;
    Point pre; // route
    public (int row, int col, Set<Integer> keys, Point pre) {
        this.row = row;
        this.col = col;
        this.key = key;
        this.pre = pre;
    }
}

public List<Point> findPathOut(String[] inputs) {
// And also we may assume the input is valid, i.e. it's a rectangular map with one '2' and one '3', and '3' is reachable from '2'
int row = inputs.length, col = intputs[0].length();

// find the start, and end point
Point start = null, end = null;
for (int i = 0; i < row; i++) {
for (int j = 0; j < col; j++) {
if (inputs[i].charAt(j) == '2') {   // start
start = new Point(i, j, 0, null);
}
if (inputs[i].charAt(j) == '3') {   // end position
end = new Point(i, j, 0, null);
}
}
}

int[][] visited = new int[row][col];
Point path = null;
Queue<Point> q = new ArrayDeque();
q.offer(start);

int[][] dirs = {{1, 0},{-1, 0},{0, 1},{0, -1}};
// bfs
while (!q.isEmpty()) {
Point cur = q.poll();  // current position

// if it's water
if (inputs[cur.row].charAt(cur.col) == '0') continue;

// if it's end, break
if (cur.row == end.row && cur.col == end.col) {
path = cur;
break;
}
// if it's visited
// 1. if he has no key, he can only go through one point once
// 2. if he has a key, he can go back again
// 000010001
if (((visited[cur.row][cur.col] & 1) == 1) && (cur.keys.contains(visited[cur.row][cur.col] >> 1)) == 0)
continue;

// if it's a key
if (inputs[cur.row].charAt(cur.col) >= 'a' && inputs[cur.row].charAt(cur.col) <= 'z') {
int key =  inputs[cur.row].charAt(cur.col) - 'a';
cur.key |= (1 << key); // 26
}

// if it's a door
if (inputs[cur.row].charAt(cur.col) >= 'A' && inputs[cur.row].charAt(cur.col) <= 'Z') {
int door = inputs[cur.row].charAt(cur.col) - 'A';
// he has no key
if ((cur.key & (1 << door)) == 0) {
continue;
} else {
// he has a key
}
}

// mark as visited, key + visited
visited[cur.row][cur.col] = (cur.key << 1) + 1; // So, here you are labeling it with all the keys you currently have
// And this way, you are discarding "visited" labels for whatever smaller set of keys you might have had before on this cell
// This can potentially (but I can't construct a counterexample at the moment!) lead to you revisiting this cells again with one of previous (smaller) sets of keys
// The best way to resolve this is to preserve somehow not a set of keys you have on this cell, but the set of sets of keys you've been there with

// go to neighbors, try 4 directions


/*
 2a003
 b1101
 00AB1
 */

}

// print the route
}

=================================================================================================
