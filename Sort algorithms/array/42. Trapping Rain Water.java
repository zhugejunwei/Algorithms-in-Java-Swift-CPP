public class Solution {
    public int trap(int[] height) {
        int mid = 0, midIdx = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > mid) {
                mid = height[i];
                midIdx = i;
            }
        }
        
        int res = 0;
        int n = height.length;
        int l = 0, r = n - 1;
        int i = l, j = r;
        while (i < midIdx || j > midIdx) {
            if (i < midIdx) {
                if (height[i] < height[l]) {
                    res += height[l] - height[i];
                } else l = i;
            }
            if (j > midIdx) {
                if (height[j] < height[r]) {
                    res += height[r] - height[j];
                } else r = j;
            }
            i++; j--;
        }
        return res;
    }
}

// shorter

public class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1, ml = 0, mr = 0, res = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= ml) ml = height[l];
                else res += ml - height[l];
                l++;
            } else {
                if (height[r] >= mr) mr = height[r];
                else res += mr - height[r];
                r--;
            }
        }
        return res;
    }
}
