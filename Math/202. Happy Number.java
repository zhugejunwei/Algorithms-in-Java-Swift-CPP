public class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = next(n);
        while (slow != fast) {
            slow = next(slow); // floyd cycle detection
            fast = next(next(fast));
        }
        return slow == 1;
    }
    
    private int next(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n%10) *(n%10);
            n /= 10;
        }
        return sum;
    }
}
