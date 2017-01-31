public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        int n = A.length;
        int[] dp = new int[n - 1];
        for (int i = 1; i < n; i++) {
            dp[i - 1] = A[i] - A[i-1];
        }
        int i = 0, res = 0;
        while (i < n - 1) {
            int j = i, count = 0;
            while (j < n - 1 && dp[i] == dp[j]) j++;
            count = j - i;
//            for (int k = 2; k <= count; k++) {
//                res += count - k + 1;
//            }
            res += (count - 2) * (count - 1)/2 + count - 1;
            i = j;
        }
        return res;
    }
}
