public class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[10][10];
        boolean[] vis = new boolean[10];
        skip[1][3] = 2; skip[3][1] = 2; skip[9][3] = 6; skip[3][9] = 6;
        skip[1][7] = 4; skip[7][1] = 4; skip[9][7] = 8; skip[7][9] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += 4 * findPath(i - 1, skip, vis, 1);
            res += 4 * findPath(i - 1, skip, vis, 2);
            res += findPath(i - 1, skip, vis, 5);
        }
        return res;
    }
    
    private int findPath(int remain, int[][] skip, boolean[] vis, int i) {
        if (remain == 0) return 1;
        int count = 0;
        vis[i] = true;
        for (int j = 1; j <= 9; j++) {
            if (vis[j] || (skip[i][j] != 0 && !vis[skip[i][j]])) continue;
            count += findPath(remain - 1, skip, vis, j);
        }
        vis[i] = false;
        return count;
    }
}
