public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            int[] sums = new int[m];
            for (int r = l; r < n; r++) {
                // calculate the rows sum for each col
                for (int i = 0; i < m; i++) {
                    sums[i] += matrix[i][r];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                // Kadane's Algorithm
                
                // preSum >= curSum - k (ceiling(curSum - k))
                // => curSum - preSum <= k
                int curSum = 0;
                for (int sum : sums) {
                    curSum += sum;
                    Integer ceiling = set.ceiling(curSum - k);
                    if (ceiling != null) {
                        maxSum = Math.max(curSum - ceiling, maxSum);
                    }
                    set.add(curSum);
                }
            }
        }
        return maxSum;
    }
}
