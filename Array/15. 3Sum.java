public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[j] + nums[k] + nums[i] > 0) {
                    k--;
                } else if (nums[j] + nums[k] + nums[i] < 0) {
                    j++;
                } else {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    while (j + 1 < n && nums[j] == nums[j + 1]) j++;
                    while (k - 1 >= 0 && nums[k] == nums[k - 1]) k--;
                    j++; k--;
                }
            }
        }
        return res;
    }
}
