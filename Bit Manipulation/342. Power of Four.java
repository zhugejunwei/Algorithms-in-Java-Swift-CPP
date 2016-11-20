public class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && ((num & 0x55555555) != 0) && ((num ^ (num & -num)) == 0);
    }
}
