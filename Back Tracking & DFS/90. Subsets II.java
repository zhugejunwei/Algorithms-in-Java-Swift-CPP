public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        DFS(nums, res, new ArrayList<>(), 0);
        return res;
    }
    
    private void DFS(int[] nums, List<List<Integer>> res, List<Integer> list, int start) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) continue; // skip the duplicates
            list.add(nums[i]);
            DFS(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
