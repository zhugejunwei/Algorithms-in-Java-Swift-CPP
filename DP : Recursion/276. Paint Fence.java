public class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        int same = k, dif = (k-1)*k;
        for (int i = 3; i <= n; i++) {
            int tmp = same;
            same = dif;
            dif = (k - 1) * (tmp + dif);
        }
        return same + dif;
    }
}
