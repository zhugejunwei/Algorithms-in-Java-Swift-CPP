public class Solution {
    // (ab)%k = (a%k)(b%k)%k
    // a^12345 % k = (a^12340)%k * (a^5)%k %k = (((a^1234)%k)^10 %k * (a^5)%k) %k
    // f(a, 12345) = f(a, 12340) * f(a, 5) % k = f(f(a, 1234), 10) * f(a, 5) % k
    int base = 1337;
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length - 1);
    }
    
    private int superPow(int a, int[] b, int i) {
        if (i == -1) return 1;
        
        return mod(superPow(a, b, i - 1), 10) * mod(a, b[i]) % base;
    }
    
    private int mod(int a, int k) {
        int res = 1;
        for (int i = 0; i < k; i++) {
            res = res * (a % base) % base;
        }
        return res;
    }
}
