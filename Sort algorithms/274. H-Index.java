// slow, without extra space
public class Solution {
    public int hIndex(int[] c) {
        Arrays.sort(c);
        int n = c.length;
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] < n - i) {
                h = Math.max(h, c[i]);
            } else {
                h = Math.max(h, n - i);
            }
        }
        return h;
    }
}

// faster, using extra space

public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cache = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                cache[n]++;
            } else {
                cache[citations[i]]++;
            }
        }
        
        int t = 0;
        for (int h = n; h >= 0; h--) {
            t += cache[h];
            if (t >= h) {
                return h;
            }
        }
        return 0;
    }
}
