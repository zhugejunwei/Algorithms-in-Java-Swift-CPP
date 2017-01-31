public class Solution {
    public int findKthNumber(int n, int k) {
        int x = 1;
        k--;
        while (k > 0) {
            int count = count(n, x, x + 1);
            if (count <= k) {
                k -= count;
                x++;
            } else {
                x *= 10;
                k--;
            }
        }
        return x;
    }
    
    private int count(int n, long a, long b) {
        int count = 0;
        while (a <= n) {
            count += Math.min(b, n + 1) - a;
            a *= 10;
            b *= 10;
        }
        return count;
    }
}
