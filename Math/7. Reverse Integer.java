public class Solution {
    public int reverse(int x) {
        long res = 0, y = x;
        int sign = 1;
        if (y < 0) {
            sign = -1;
            y = -y;
        }
        while (y > 0) {
            res = res * 10 + y % 10;
            y /= 10;
            if (sign > 0 && res > Integer.MAX_VALUE) return 0;
            if (sign < 0 && res - 1 > Integer.MAX_VALUE) return 0;
        }
        return (int)res * sign;
    }
}

//

public class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int newRes = res * 10 + x % 10;
            if (newRes/10 != res) return 0;
            res = newRes;
            x /= 10;
        }
        return res;
    }
}
