public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        helper(nums, res, new ArrayList(), 0);
        return res;
    }
    
    private void helper(int[] nums, List<List<Integer>> res, List<Integer> list, int start) {
        if (list.size() >= 2) {
            res.add(new ArrayList(list));
        }
        Set<Integer> vis = new HashSet();
        for (int i = start; i < nums.length; i++) {
            if (vis.contains(nums[i])) continue;
            if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
                vis.add(nums[i]);
                list.add(nums[i]);
                helper(nums, res, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
