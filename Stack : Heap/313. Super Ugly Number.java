// O(nk)
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] idx = new int[primes.length];
        int i = 0;
        while (++i < n) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (primes[j]*res[idx[j]] < min) {
                    min = primes[j]*res[idx[j]];
                }
            }
            for (int j = 0; j < primes.length; j++) {
                if (primes[j]*res[idx[j]] == min) {
                    idx[j]++;
                }
            }
            res[i] = min;
        }
        return res[n - 1];
    }
}

// using indexed-Priority-queue, O(nlog(k))
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        PriorityQueue<Num> q = new PriorityQueue();
        for (int p : primes)
            q.offer(new Num(p, 0, p));
        // res[idx[j]] * primes[j], idx[j]++
        for (int i = 1; i < n; i++) {
            res[i] = q.peek().val;
            while (q.peek().val == res[i]) {
                Num cur = q.poll();
                q.offer(new Num(res[cur.idx] * cur.prime, cur.idx + 1, cur.prime));
            }
        }
        return res[n - 1];
    }
    
    class Num implements Comparable<Num> {
        int val, idx, prime;
        public Num(int val, int idx, int prime) {
            this.val = val;
            this.idx = idx;
            this.prime = prime;
        }
        
        public int compareTo(Num that) {
            return this.val - that.val;
        }
    }
}
