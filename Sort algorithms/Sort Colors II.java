// sort color 2
// l和r走过的路是可以保证valid的，但是 cur 走过的路也必须保证valid，也就是说如果cur从左往右，那么左边检查的部分一定要放在第二部分检查，也就是检查右边之后检查左边，这样才能把右端的所有min的值放到左边来。如果反过来，右边的valid的值有可能卡在中间，因为先左再右检查的话，左边第一次检查没有min，min在右边，右边把min拿过来的时候，cur已经往后走了。
class Solution {
    public void sortColors2(int[] colors, int k) {
        int n = colors.length, l = 0, r = n - 1, min = 1, max = k;
        while (min <= max) {
            for (int cur = l; cur <= r; cur++) {
                while (cur <= r && colors[cur] == max) {
                    swap(colors, cur, r);
                    r--;
                }
                while (cur >= l && colors[cur] == min) {
                    swap(colors, cur, l);
                    l++;
                }
            }
            min++; max--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
