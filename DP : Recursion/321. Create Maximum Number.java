public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int n1 = nums1.length, n2 = nums2.length;
        for (int len1 = Math.max(0, k - n2); len1 <= n1 && len1 <= k; len1++) {
            int[] candidate = merge(maxSubarray(nums1, len1), maxSubarray(nums2, k - len1), k);
            if (greater(candidate, 0, res, 0)) res = candidate;
        }
        return res;
    }
    
    private int[] maxSubarray(int[] arr, int k) {
        int[] res = new int[k];
        int n = arr.length;
        int last = -1;
        for (int i = 0; i < k; i++) {
            for (int j = last + 1; j + (k - i - 1) < n; j++) {
                if (res[i] < arr[j]) {
                    res[i] = arr[j];
                    last = j;
                }
            }
        }
        return res;
    }
    
    private int[] merge(int[] arr1, int[] arr2, int k) {
        int[] res = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++) {
            res[r] = greater(arr1, i, arr2, j) ? arr1[i++] : arr2[j++];
        }
        return res;
    }
    
    private boolean greater(int[] arr1, int i, int[] arr2, int j) {
        while (i < arr1.length && j < arr2.length && arr1[i] == arr2[j]) {
            i++;
            j++;
        }
        return j == arr2.length || (i < arr1.length && arr1[i] > arr2[j]);
    }
}
