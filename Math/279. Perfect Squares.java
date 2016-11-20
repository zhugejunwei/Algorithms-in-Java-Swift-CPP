// 4^k * (8 * m + 7)
public class Solution {
    public int numSquares(int n) {
        // 4
        int m = n;
        while ((m & 3) == 0) m >>>= 2;
        if ((m - 7) % 8 == 0) return 4;
        
        // 1, 2
        int index = (int)Math.sqrt(n);
        while (index > 0) {
            int tmp = n - index * index;
            if (n == (int)Math.sqrt(tmp) * (int)Math.sqrt(tmp) + index * index) {
                return Math.sqrt(tmp) == 0 ? 1 : 2;
            }
            index--;
        }
        return 3;
    }
}

// DP
public class Solution {
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 1; i <= n; i++) {
            int min = i;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                int tmp = i - j * j;
                min = Math.min(min, tmp == 0 ? 1 : 1 + f[tmp]);
            }
            f[i] = min;
        }
        return f[n];
    }
}
