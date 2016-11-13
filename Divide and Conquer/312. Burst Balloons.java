public class Solution {
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        int[] num = new int[nums.length+2];
        int n = 1;
        for (int i : nums) if (i > 0) num[n++] = i;
        num[0] = num[n++] = 1;
        
        return DC(new int[n][n], num, 0, n-1);
    }
    
    private int DC(int[][] memo, int[] num, int l, int r) {
        if (l + 1 == r) return 0;
        if (memo[l][r] > 0) return memo[l][r];
        int coin = 0;
        for (int i = l+1; i < r; i++) {
            coin = Math.max(coin, num[l]*num[i]*num[r] + DC(memo, num, l, i) + DC(memo, num, i, r));
        }
        memo[l][r] = coin;
        return coin;
    }
}
