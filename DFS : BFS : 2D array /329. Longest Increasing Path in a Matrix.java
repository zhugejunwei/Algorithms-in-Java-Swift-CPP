public class Solution {
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, findPath(matrix, i, j, cache));
            }
        }
        return res;
    }
    
    private int findPath(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], findPath(matrix, x, y, cache));
            }
        }
        return ++cache[i][j];
    }
}


// inital cache[][] with 1;
// if (matrix[x][y] > matrix[i][j])
//      cache[i][j] = Math.max(cache[i][j], 1 + find(matrix, x, y, cache));
public class Solution {
    int[][] memo;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], 1);
        
        boolean[][] vis = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = true;
                res = Math.max(res, helper(matrix, i, j, vis, m, n));
                vis[i][j] = false;
            }
        }
        return res;
    }
    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int helper(int[][] matrix, int i, int j, boolean[][] vis, int m, int n) {
        if (memo[i][j] > 1) return memo[i][j];
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x >= m || y >= n || x < 0 || y < 0 || vis[x][y] || matrix[x][y] <= matrix[i][j]) continue;
            vis[x][y] = true;
            memo[i][j] = Math.max(memo[i][j], helper(matrix, x, y, vis, m, n) + 1);
            vis[x][y] = false;
        }
        return memo[i][j];
    }
}
