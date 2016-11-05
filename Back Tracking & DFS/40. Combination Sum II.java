public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        Arrays.sort(candidates);
        dfs(candidates, res, new ArrayList<>(), target, 0);
        return res;
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) res.add(new ArrayList<>(list));
        
        for (int i = start; i < nums.length; i++) {
            if (remain - nums[i] < 0) break;
            if (i > start && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            dfs(nums, res, list, remain - nums[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
