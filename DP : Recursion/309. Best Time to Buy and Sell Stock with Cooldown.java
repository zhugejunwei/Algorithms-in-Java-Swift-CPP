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
