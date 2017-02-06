public class Solution {
    public String smallestGoodBase(String n) {
        long num = 0;
        for (char c : n.toCharArray()) num = num * 10 + c - '0';
        long x = 1;
        for (int p = 64; p >= 1; p--) { // 0, 1, 2...p, 从大到小找，可以保证最后的答案 base是最小的。
            if ((x << p) < num) {
                // find base
                long base = calBase(num, p);
                if (base != -1) {
                    return String.valueOf(base);
                }
            }
        }
        return String.valueOf(num - 1);
    }
    
    private long calBase(long num, int p) {
        long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
        while (l < r) {
            long m = l + (r - l) / 2;
            long sum = 0, cur = 1;
            for (int i = 0; i <= p; i++) {
                sum += cur;
                cur *= m;
            }
            if (sum == num) return m;
            else if (sum > num) r = m;
            else l = m + 1;
        }
        return -1;
    }
}
