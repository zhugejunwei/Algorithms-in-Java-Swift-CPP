public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int max = 0;
        for (int[] en : envelopes) {
            int i = 0, j = max;
            while (i != j) {
                int mid = i + (j - i)/2;
                if (dp[mid] < en[1]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            dp[i] = en[1];
            if (i == max) ++max;
        }
        return max;
    }
}

//
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return 0;
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[] dp = new int[n];
        int size = 0;
        for (int[] en : envelopes) {
            int l = 0, r = size;
            while (l < r) {
                int m = (l & r) + ((l ^ r) >>> 1);
                if (en[1] > dp[m]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            dp[l] = en[1];
            if (l == size) size++;
        }
        return size;
    }
}
