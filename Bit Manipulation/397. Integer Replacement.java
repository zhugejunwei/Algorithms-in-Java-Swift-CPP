public class Solution {
    public int integerReplacement(int n) {
        // even, n & 0b1 == 0, n >>= 1
        // odd, else, n = n-1 || n + 1
        int count = 0;
        while (n != 1) {
            count++;
            if ((n & 0b1) == 0) { // even
                n >>>= 1;
            } else { // odd
                if (n == 3 || ((n >>> 1) & 1) == 0) n -= 1; //
                else n += 1;
            }
        }
        return count;
    }
}
