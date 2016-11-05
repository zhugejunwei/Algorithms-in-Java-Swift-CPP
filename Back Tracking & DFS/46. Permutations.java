public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(nums, res, new ArrayList<>());
        return res;
    }
    // here can use vis[]
    // also can use list.contains()
    private void DFS(int[] nums, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == nums.length) res.add(new ArrayList<>(list));
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue; // element already exists, skip
            list.add(nums[i]);
            DFS(nums, res, list);
            list.remove(list.size() - 1);
        }
    }
}

// vis[]  可以返回之前的数，
// start 不能返回，之后往后走
