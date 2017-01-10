public class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int i = 0, i2 = 0, i3 = 0, i5 = 0;
        while (++i < n) {
            res[i] = Math.min(Math.min(res[i2] * 2, res[i3] * 3), res[i5] * 5);
            if (res[i2] * 2 == res[i]) i2++;
            if (res[i3] * 3 == res[i]) i3++;
            if (res[i5] * 5 == res[i]) i5++;
        }
        return res[n - 1];
    }
}
