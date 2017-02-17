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


// more reasonable
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) return 0;
        int[] dif = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            dif[i] = A[i] - A[i - 1];
        }
        
        int i = 1, res = 0;
        while (i < A.length) {
            int j = i + 1, count = 0;
            while (j < A.length && dif[i] == dif[j]) j++;
            count = j - i + 1;
            res += (count - 1)*(count - 2)/2;
            i = j;
        }
        return res;
    }
}
