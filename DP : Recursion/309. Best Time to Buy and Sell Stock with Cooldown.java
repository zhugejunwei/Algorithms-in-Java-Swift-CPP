public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] res = new int[n];
        buy[0] = -prices[0]; sell[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i-1], res[i-1] - prices[i]);
            sell[i] = buy[i - 1] + prices[i];
            res[i] = Math.max(sell[i-1], res[i-1]);
        }
        return Math.max(res[n-1], sell[n-1]);
    }
}


// space complexity, O(1)

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int sell = 0, buy = -prices[0], sell_pre = 0;
        for (int p : prices) {
            buy = Math.max(sell_pre - p, buy); // buy 要在 sell 之后
            sell_pre = sell; // 为了空一格，表示rest
            sell = Math.max(buy + p, sell_pre); // sell
        }
        return sell;
    }
}
