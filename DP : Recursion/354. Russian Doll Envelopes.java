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
