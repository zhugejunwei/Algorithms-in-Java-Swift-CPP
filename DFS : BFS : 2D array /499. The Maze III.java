// BFS, Dijkstra's algorithm
public class Solution {
    int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    char[] dirc = {'d', 'l', 'r', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        String minS = null;
        int min = Integer.MAX_VALUE;
        
        // min distances from start point
        int[][] map = new int[m][n]; // min distance till this node
        for (int i = 0; i < m; i++) Arrays.fill(map[i], Integer.MAX_VALUE);
        map[ball[0]][ball[1]] = 0;
        
        Node start = new Node(ball[0], ball[1], 0, ""); // start point
        PriorityQueue<Node> q = new PriorityQueue();
        q.add(start);
        
        // // shortest path tree set
        boolean[][] vis = new boolean[m][n]; // visited nodes
        
        while (!q.isEmpty()) {
            // extract min, get the cur position
            Node cur = q.poll();
            if (cur.x == hole[0] && cur.y == hole[1]) break;
            vis[cur.x][cur.y] = true;
            // try 4 dirs
            for (int d = 0; d < 4; d++) {
                int x = cur.x, y = cur.y;
                
                // start point, or get the end point
                while (x + dirs[d][0] < m && x + dirs[d][0] >= 0 && y + dirs[d][1] < n && y + dirs[d][1] >= 0
                       && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                    x += dirs[d][0];
                    y += dirs[d][1];
                    if (vis[x][y] || (x == hole[0] && y == hole[1])) break;
                }
                int step = cur.step + Math.abs(x - cur.x) + Math.abs(y - cur.y);
                if (vis[x][y] || step > map[x][y]) continue;
                // update distance
                map[x][y] = step;
                // next node
                Node next = new Node(x, y, step, cur.route + dirc[d]);
                
                // reach the end
                if (x == hole[0] && y == hole[1]) {
                    if (step == min && (minS == null || next.route.compareTo(minS) < 0)) {
                        minS = next.route;
                    } else if (step < min) {
                        min = step;
                        minS = next.route;
                    }
                    // if reach the end in this direction, we don't need to try other directions
                    break;
                }
                
                q.add(next);
            }
        }
        return minS == null ? "impossible" : minS;
    }
    
    class Node implements Comparable<Node> {
        int x, y, step;
        String route; // a string formed by directions along the way
        public Node(int x, int y, int step, String route) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.route = route;
        }
        
        public int compareTo(Node that) {
            return this.step - that.step;
        }
    }
}


// DFS 1
public class Solution {
    int minStep;
    int m, n;
    String res;
    int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    String[] dirc = {"d", "r", "l", "u"}; // 0123
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.m = maze.length;
        this.n = maze[0].length;
        this.minStep = Integer.MAX_VALUE;
        this.res = null;
        boolean[][] vis = new boolean[m][n];
        vis[ball[0]][ball[1]] = true;
        
        dfs(ball[0], ball[1], maze, hole, vis, "", 0);
        
        return res == null ? "impossible" : res;
    }
    
    private void dfs(int i, int j, int[][] maze, int[] hole, boolean[][] vis, String route, int step) {
        if (step > minStep) return;
        if (i == hole[0] && j == hole[1]) {
            if (step == minStep && route.compareTo(res) < 0) {
                res = route;
            } else if (step < minStep) {
                minStep = step;
                res = route;
            }
            vis[i][j] = false;
            return;
        }
        
        for (int d = 0; d < 4; d++) {
            // roll to the wall
            int x = i, y = j;
            while (x + dirs[d][0] >= 0 && x + dirs[d][0] < m && y + dirs[d][1] >= 0 && y + dirs[d][1] < n
                   && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                x += dirs[d][0];
                y += dirs[d][1];
                if (x == hole[0] && y == hole[1] || vis[x][y])  break;
            }
            if (!vis[x][y] && maze[x][y] == 0) {
                vis[x][y] = true;
                dfs(x, y, maze, hole, vis, route + dirc[d], step + Math.abs(x - i) + Math.abs(y - j));
                vis[x][y] = false;
            }
        }
    }
}


// DFS 2
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
