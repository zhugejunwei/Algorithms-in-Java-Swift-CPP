public class Solution {
    public int numberOfPatterns(int m, int n) {
        // int remain, cur;
        int[][] skip = new int[10][10];
        boolean[] vis = new boolean[10];
        int res = 0;
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[3][7] = skip[2][8] = skip[4][6] = skip[9][1] = skip[7][3] = skip[8][2] = skip[6][4] = 5;
        for (int i = m; i <= n; i++) {
            res += DFS(vis, skip, 1, i - 1)*4;
            res += DFS(vis, skip, 2, i - 1)*4;
            res += DFS(vis, skip, 5, i - 1);
        }
        return res;
    }
    
    private int DFS(boolean[] vis, int[][] skip, int cur, int remain) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;
        int count = 0;
        vis[cur] = true;
        for (int i = 1; i <= 9; i++) {
            if (vis[i] || (skip[cur][i] != 0 && vis[skip[cur][i]] == false)) continue;
            count += DFS(vis, skip, i, remain - 1);
        }
        vis[cur] = false;
        return count;
    }
}
