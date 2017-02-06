public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList();
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList();
        helper(candidates, target, res, new ArrayList(), 0);
        return res;
    }
    
    private void helper(int[] nums, int target, List<List<Integer>> res, List<Integer> list, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList(list));
            return;
        }
        if (start == nums.length) return;
        
        for (int i = start; i < nums.length; i++) {
            if (target < nums[i]) break;
            if (i > start && nums[i] == nums[start]) continue;
            list.add(nums[i]);
            helper(nums, target - nums[i], res, list, i);
            list.remove(list.size() - 1);
        }
    }
}
