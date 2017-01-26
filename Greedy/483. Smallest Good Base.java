// 9ms
public class Solution {
    public String smallestGoodBase(String n) {
        // sum base r == 1111... means sum = 1+k+k^2+k^3+...+k^i
        // num = (k^m - 1)/(k - 1)
        long num = 0;
        for (char c : n.toCharArray()) num = num * 10 + c - '0';
        
        long x = 1;
        // 1+k+k^2+k^3+...+k^i
        for (int p = 2; p < 100; p++) {
            // if 2^(i-1) >= num, it means 1+k+k^2+k^3+...+k^i > num, so r should be num - 1
            if ((x << p) < num) {
                long k = helper(num, p);
                if (k != -1) return String.valueOf(k);
            }
        }
        return String.valueOf(num - 1);
    }
    
    private long helper(long num, int p) {
        long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
        while (l < r) {
            long mid = l + (r - l) / 2;
            long sum = 0, cur = 1;
            for (int i = 0; i <= p; i++) {
                sum += cur;
                cur *= mid;
            }
            if (sum == num) return mid;
            else if (sum > num) r = mid;
            else l = mid + 1;
        }
        return -1;
    }
}
