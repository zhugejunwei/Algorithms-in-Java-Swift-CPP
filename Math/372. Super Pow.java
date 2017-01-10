public class Solution {
    // ab % k = (a%k * b%k)%k
    // f(a, 123) = f(a, 120) * f(a, 3) % k = f(f(a, 12), 10) * f(a, 3) % k
    int base = 1337;
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length - 1);
    }
    private int superPow(int a, int[] b, int i) {
        if (i == 0) {
            return powMod(a, b[0]);
        }
        return powMod(superPow(a, b, i-1), 10) * powMod(a, b[i]) % base;
    }
    private int powMod(int a, int k) {
        int res = 1;
        a %= base;
        for (int i = 0; i < k; i++) {
            res = (res * a) % base;
        }
        return res;
    }
}
