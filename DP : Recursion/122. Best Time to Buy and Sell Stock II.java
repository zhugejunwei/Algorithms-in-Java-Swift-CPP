// 每次有上升了就有一次交易，那么连续上升的利润其实等价于最后最大的－最开始上升的价格。

public class Solution {
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }
        
        return total;
    }
}
