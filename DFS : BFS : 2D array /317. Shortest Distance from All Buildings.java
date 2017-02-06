public class Solution {
    class Point {
        int x, y, step;
        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    int[][] dist;
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        dist = new int[m][n];
        List<Point> buildings = new ArrayList();
        
        // add start points
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildings.add(new Point(i, j, 0));
                }
                grid[i][j] = -grid[i][j];
            }
        }
        
        // bfs traverse
        for (int k = 0; k < buildings.size(); k++) {
            BFS(buildings.get(k), grid, k, m, n);
        }
        
        // get min dist
        int min = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // grid[i][j] = size, means it is reachable by every building
                if ((dist[i][j] < min || min == -1) && grid[i][j] == buildings.size())
                    min = dist[i][j];
            }
        }
        return min;
    }
    
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // we only need to consider those points that every prior building can reach, i.e. grid[i][j] = k
    private void BFS(Point start, int[][] grid, int k, int m, int n) {
        Queue<Point> q = new ArrayDeque();
        q.add(start);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            dist[cur.x][cur.y] += cur.step;
            for (int[] d : dirs) {
                int x = cur.x + d[0], y = cur.y + d[1];
                if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == k) {
                    grid[x][y] = k + 1;
                    q.add(new Point(x, y, cur.step + 1));
                }
            }
        }
    }
}
