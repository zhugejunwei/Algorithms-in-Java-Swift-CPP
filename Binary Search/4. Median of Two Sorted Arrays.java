public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);
        int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) >> 1;
        while (imin <= imax) {
            i = (imin & imax) + ((imin ^ imax) >> 1);
            j = half - i;
            if (i > 0 && nums1[i - 1] > nums2[j]) imax = i - 1;
            else if (i < m && nums2[j - 1] > nums1[i]) imin = i + 1;
            else break;
        }
        int maxLeft = 0, minRight = Integer.MAX_VALUE;
        if (i == 0) maxLeft = nums2[j - 1];
        else if (j == 0) maxLeft = nums1[i - 1];
        else maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
        if (((m + n) & 1) == 1) return maxLeft;
        
        if (i == m) minRight = nums2[j];
        else if (j == n) minRight = nums1[i];
        else minRight = Math.min(nums1[i], nums2[j]);
        return (minRight + maxLeft) / 2.0;
    }
}
