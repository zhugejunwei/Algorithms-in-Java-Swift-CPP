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
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cache[i][j] = 1;
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cache[i][j] != 1) continue;
                res = Math.max(res, find(matrix, i, j, cache));
            }
        }
        return res;
    }
    
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int find(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 1) return cache[i][j];
        
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            cache[i][j] = Math.max(cache[i][j], 1 + find(matrix, x, y, cache));
        }
        return cache[i][j];
    }
}
