public class Solution {
    public int getMoneyAmount(int n) {
        if (n == 1) return 0;
        int[][] count = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            int i = j - 1;
            while (i > 0) {
                int res = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int tmp = Math.max(count[i][k - 1], count[k + 1][j]) + k;
                    res = Math.min(tmp, res);
                }
                count[i][j] = (i + 1 == j) ? i : res;
                i--;
            }
        }
        return count[1][n];
    }
}
