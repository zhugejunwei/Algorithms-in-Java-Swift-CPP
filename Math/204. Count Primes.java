public class Solution {
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        for (int i = 2; i*i < n; i++) {
            if (prime[i]) continue;
            for (int j = i*i; j < n; j += i) {
                if (prime[j]) continue;
                prime[j] = true;
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!prime[i]) res++;
        }
        return res;
    }
}
