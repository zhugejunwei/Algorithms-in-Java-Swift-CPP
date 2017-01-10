public class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = dividend;
        dvd = Math.abs(dvd);
        long dvs = divisor;
        dvs = Math.abs(dvs);
        
        int res = 0;
        while (dvd >= dvs) {
            long tmp = dvs, count = 1;
            while (dvd >= (tmp << 1)) {
                tmp <<= 1;
                count <<= 1;
            }
            dvd -= tmp;
            res += count;
        }
        return sign < 0 ? -res : res;
    }
}
