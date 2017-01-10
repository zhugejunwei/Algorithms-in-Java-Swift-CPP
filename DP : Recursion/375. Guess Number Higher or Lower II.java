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

//
public class Solution {
    public int getMoneyAmount(int n) {
        return helper(1, n, new int[n + 1][n + 1]);
    }
    private int helper(int l, int r, int[][] cache) {
        if (l >= r) return 0;
        if (l + 1 == r) return l;
        if (cache[l][r] != 0) return cache[l][r];
        int guess = Integer.MAX_VALUE;
        for (int k = l + 1; k < r; k++) { // 枚举猜测
            int choose = 0;
            choose = Math.max(choose, helper(l, k - 1, cache)); // 选择大的
            choose = Math.max(choose, helper(k + 1, r, cache)); // 选择大的
            guess = Math.min(guess, choose + k);
        }
        cache[l][r] = guess;
        return guess;
    }
}
