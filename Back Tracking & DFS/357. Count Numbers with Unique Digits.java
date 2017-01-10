// Backtracking

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) return countNumbersWithUniqueDigits(10);
        int used = 0, count = 1; // 0
        long max = (long)Math.pow(10, n);
        for (int i = 1; i < 10; i++) {
            used ^= (1 << i);
            count += find(i, max, used);
            used ^= (1 << i);
        }
        return count;
    }
    
    private int find(long pre, long max, int used) {
        int count = 0;
        if (pre < max) {
            count++;
        } else {
            return count;
        }
        
        for (int i = 0; i < 10; i++) {
            if ((used >>> i & 1) == 1) continue;
            used ^= (1 << i);
            count += find(10 * pre + i, max, used);
            used ^= (1 << i);
        }
        return count;
    }
}

// DP

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) return countNumbersWithUniqueDigits(10);
        if (n == 0) return 1;
        int res = 10;
        int available = 9;
        int count = 9;
        int i = 1;
        while (i++ < n) {
            count *= available;
            res += count;
            available--;
        }
        return res;
    }
}
