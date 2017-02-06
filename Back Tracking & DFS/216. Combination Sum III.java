public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || n <= 0) return new ArrayList();
        List<List<Integer>> res = new ArrayList();
        helper(k, n, res, new ArrayList(), 1);
        return res;
    }
    
    private void helper(int k, int target, List<List<Integer>> res, List<Integer> list, int start) {
        if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList(list));
            }
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            if (target - i < 0) continue;
            list.add(i);
            helper(k - 1, target - i, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
