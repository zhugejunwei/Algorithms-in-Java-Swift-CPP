// Newton

public class Solution {
    public int mySqrt(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        return (int)x;
    }
}

// Binary search

public class Solution {
    public int mySqrt(int a) {
        if (a == 0) return 0;
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid > a / mid) {
                r = mid - 1;
            } else {
                if (mid + 1 > a / (mid + 1))
                    return mid;
                l = mid + 1;
            }
        }
        return l;
    }
}
