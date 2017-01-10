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

// sell 在 buy 之前
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        
        int sell1 = 0, buy1 = -prices[0], sell2 = 0, buy2 = Integer.MIN_VALUE;
        for (int p : prices) {
            sell1 = Math.max(sell1, buy1 + p);
            buy1 = Math.max(buy1, -p);
            sell2 = Math.max(sell2, buy2 + p);
            buy2 = Math.max(buy2, sell1 - p);
        }
        return sell2;
    }
}

// ======================== typical DP solution ========================= //

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int max = 0, k = 2;
        int[][] f = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int preMax = f[i-1][0] - prices[0]; // transaction_1, buy the first stock
            for (int j = 1; j < prices.length; j++) {
                f[i][j] = Math.max(f[i][j-1], preMax + prices[j]); // whether to sell the stock at this time
                preMax = Math.max(preMax, f[i - 1][j] - prices[j]); // whether to buy this stock or not
                max = Math.max(max, f[i][j]);
            }
        }
        return max;
    }
}
