public class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int p : prices) {
            buy1 = Math.max(buy1, -p); // the max profile after buy1
            sell1 = Math.max(sell1, buy1 + p); // the max profile after sell1
            buy2 = Math.max(buy2, sell1 - p); // the max profile after buy2
            sell2 = Math.max(sell2, buy2 + p); // the max profile after sell2
        }
        return sell2;
    }
}


// ======================== typical DP solution ========================= //

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int n = 2;
        int max = 0;
        int[][] f = new int[n + 1][prices.length];
        for (int k = 1; k <= n; k++) {
            int tmp = f[k-1][0] - prices[0];
            for (int i = 1; i < prices.length; i++) {
                f[k][i] = Math.max(f[k][i-1], prices[i] + tmp);
                tmp = Math.max(tmp, f[k - 1][i] - prices[i]);
                max = Math.max(max, f[k][i]);
            }
        }
        return max;
    }
}
