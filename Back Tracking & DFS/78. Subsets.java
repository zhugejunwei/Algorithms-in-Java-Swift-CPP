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

// another shorter solution
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        dfs(nums, res, new ArrayList(), 0);
        return res;
    }
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, int start) {
        // 不需要if结束条件，因为下面这个循环就是有头有尾的
        res.add(new ArrayList(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
