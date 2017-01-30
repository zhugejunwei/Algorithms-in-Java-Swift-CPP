public class Solution {
    int m, n;
    int min;
    String minS;
    int[] hole;
    int[][] maze;
    int[][] map; // min step till this postion, 利用map代替 vis[][]
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // r, d, l, u
    char[] dirc = {'r', 'd', 'l', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.m = maze.length;
        this.n = maze[0].length;
        this.min = Integer.MAX_VALUE;
        this.minS = null;
        this.hole = hole;
        this.maze = maze;
        this.map = new int[m][n];
        
        for (int i = 0; i < m; i++) Arrays.fill(map[i], Integer.MAX_VALUE);
        
        move(ball[0], ball[1], 0, "", -1);
        
        return minS == null ? "impossible" : minS;
    }
    
    private void move(int r, int c, int step, String route, int dir) {
        if (step > min || step > map[r][c]) return;
        
        if (dir != -1) {
            // continue going to the wall
            while (r < m && r >= 0 && c < n && c >= 0 && maze[r][c] == 0) {
                map[r][c] = Math.min(map[r][c], step);
                
                if (r == hole[0] && c == hole[1]) {
                    if (step == min && route.compareTo(minS) < 0) {
                        minS = route;
                    } else if (step < min) {
                        min = step;
                        minS = route;
                    }
                    return;
                }
                r += dirs[dir][0];
                c += dirs[dir][1];
                step++;
            }
            r -= dirs[dir][0];
            c -= dirs[dir][1];
            step--;
        }
        
        // if it's wall or start, turn
        for (int i = 0; i < 4; i++) {
            if (dir == i || dir == (4 - i)) continue;
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if (newR >= 0 && newR < m && newC < n && newC >= 0 && maze[newR][newC] == 0) {
                move(r, c, step, route + dirc[i], i);
            }
        }
    }
}
