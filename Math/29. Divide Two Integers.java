// bit manipulation, but I think this should not be used as it is the same idea of multiply and division
// we should use binary search instead
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


// using plus is better
public class Solution {
    public int divide(int dividend, int divisor) {
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long a = Math.abs((long)dividend), b = Math.abs((long)divisor);
        long res = 0;
        while (a >= b) {
            long count = 1, sum = b;
            while ((sum + sum) <= a) {
                sum += sum;
                count += count;
            }
            res += count;
            a -= sum;
            if (res > Integer.MAX_VALUE)
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return sign == -1 ? (int)-res : (int)res;
    }
}
