public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] vis) {
        if (list.size() == nums.length) res.add(new ArrayList<>(list));
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) continue;
            // !vis[i - 1], cannot start from a same node's next node.
            // e.g. 1,1,2, cannot start from the second 1;
            // OK: 012, 021, 201
            // NO: 102,210,120
            list.add(nums[i]);
            vis[i] = true;
            dfs(nums, res, list, vis);
            vis[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
