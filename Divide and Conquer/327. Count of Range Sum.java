public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i-1] + (long)nums[i-1];
        return mergeSort(sum, 0, sum.length - 1, lower, upper);
    }
    
    // j > i, lower <= sum[j] - sum[i] <= upper
    private int mergeSort(long[] sum, int l, int r, int lower, int upper) {
        if (l >= r) return 0;
        int mid = l + (r - l)/2;
        int count = mergeSort(sum, l, mid, lower, upper) + mergeSort(sum, mid + 1, r, lower, upper);
        
        long[] cache = new long[r - l + 1];
        int j = mid + 1, li = mid + 1, ri = mid + 1;
        for (int i = l, idx = 0; i <= mid; i++, idx++) {
            // compare sum[left] and sum[right]
            while (li <= r && sum[li] - sum[i] < lower) li++;
            while (ri <= r && sum[ri] - sum[i] <= upper) ri++;
            // add smaller
            while (j <= r && sum[j] < sum[i]) cache[idx++] = sum[j++];
            cache[idx] = sum[i];
            count += ri - li;
        }
        System.arraycopy(cache, 0, sum, l, j - l);
        return count;
    }
}
