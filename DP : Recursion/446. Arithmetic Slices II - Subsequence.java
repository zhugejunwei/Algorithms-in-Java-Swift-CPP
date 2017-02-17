public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        Map<Long, Integer>[] dp = new Map[A.length];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            dp[i] = new HashMap();
            for (int j = 0; j < i; j++) {
                long dif = (long)A[i] - A[j];
                if (dif >= Integer.MAX_VALUE || dif < Integer.MIN_VALUE) continue;
                int count = dp[j].getOrDefault(dif, 0);
                dp[i].put(dif, dp[i].getOrDefault(dif, 0) + count + 1);
                res += count;
            }
        }
        return res;
    }
}
