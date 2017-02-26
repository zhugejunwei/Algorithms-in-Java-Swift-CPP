// https://www.youtube.com/watch?v=yCQN096CwWM
// 先从第一列开始作为最后结果matrix的第一列，把后面的列再一列一列加上，用Kadane's algorithm求加好的 1D array的no larger than k，一直加到最后一列，
// 再以第二列为开始。。。一直到以最后一列为开始，
public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            int[] sums = new int[m];
            for (int r = l; r < n; r++) {
                // Kadane's algorithm 的变形
                TreeSet<Integer> set = new TreeSet();
                set.add(0);
                int curSum = 0;
                for (int i = 0; i < m; i++) {
                    sums[i] += matrix[i][r];
                    curSum += sums[i];
                    Integer ceiling = set.ceiling(curSum - k);
                    if (ceiling != null)
                        res = Math.max(curSum - ceiling, res);
                    set.add(curSum);
                }
            }
        }
        return res;
    }
}
