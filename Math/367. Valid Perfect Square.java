// binary search
public class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l <= r) {
            long m = (l & r) + ((l ^ r) >>> 1);
            if (m * m == num) return true;
            else if (m * m > num) r = (int)m - 1;
            else l = (int)m + 1;
        }
        return false;
    }
}

// Newton Method
public class Solution {
    public boolean isPerfectSquare(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >>> 1;
        }
        return x * x == num;
    }
}
