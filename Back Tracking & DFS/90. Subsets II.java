public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums); // must sort first
        dfs(nums, res, new ArrayList(), 0);
        return res;
    }
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, int start) {
        res.add(new ArrayList(list));
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
