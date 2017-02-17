public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        return mergeSort(sum, 0, n, lower, upper);
    }
    
    private int mergeSort(long[] sum, int l, int r, int lower, int upper) {
        if (l >= r) return 0;
        int mid = l + (r - l)/2;
        int count = mergeSort(sum, l, mid, lower, upper)
        + mergeSort(sum, mid + 1, r, lower, upper);
        
        long[] cache = new long[r - l + 1];
        int j = mid + 1, k = mid + 1, t = mid + 1;
        for (int i = l, c = 0; i <= mid; ++i, ++c) {
            while (j <= r && sum[j] - sum[i] < lower) j++;
            while (k <= r && sum[k] - sum[i] <= upper) k++;
            while (t <= r && sum[t] < sum[i]) cache[c++] = sum[t++];
            cache[c] = sum[i];
            count += k - j;
        }
        System.arraycopy(cache, 0, sum, l, t - l);
        return count;
    }
}
