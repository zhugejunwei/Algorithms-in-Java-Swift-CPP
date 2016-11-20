public class Solution {
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int area = (r - l) * Math.min(height[l],height[r]);
        while (l < r) {
            if (height[l] < height[r]) {
                l++;
                area = Math.max(area, (r - l) * Math.min(height[l],height[r]));
            }
            else {
                r--;
                area = Math.max(area, (r - l) * Math.min(height[l],height[r]));
            }
        }
        return area;
    }
}
