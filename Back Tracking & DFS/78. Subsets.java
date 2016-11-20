public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        for (int len = 0; len <= nums.length; len++) {
            dfs(res, nums, new ArrayList(), 0, len);
        }
        return res;
    }
    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> list, int start, int len) {
        if (start == len) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, nums, list, i + 1, len);
            list.remove(list.size() - 1);
        }
    }
}
