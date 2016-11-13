public class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        if (k >= n/2) return quickSolve(prices, n);
        
        int[][] f = new int[k + 1][n];
        int max = 0;
        for (int i = 1; i <= k; i++) {
            int tmp = f[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.max(f[i][j - 1], tmp + prices[j]);
                tmp = Math.max(tmp, f[i - 1][j - 1] - prices[j]);
            }
        }
        return f[k][n - 1];
    }
    
    // avoid TLE or MLE
    private int quickSolve(int[] prices, int n) {
        int p = 0;
        for (int i = 1; i < n; i++) {
            p += prices[i] - prices[i - 1] > 0 ? (prices[i] - prices[i - 1]) : 0;
        }
        return p;
    }
}
