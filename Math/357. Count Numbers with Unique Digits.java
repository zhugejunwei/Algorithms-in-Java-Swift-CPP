public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1; // n = 0
        if (n > 10) return countNumbersWithUniqueDigits(10);
        int res = 10, unique = 9, avail = 9; // init n = 1
        for (; n-- > 1 && avail > 0; avail--) { // cases when n > 1, 9*9*8*...*1
            unique = unique * avail;
            res += unique;
        }
        return res;
    }
}
