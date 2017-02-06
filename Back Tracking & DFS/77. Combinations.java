public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        helper(n, k, res, new ArrayList(), 1);
        return res;
    }
    
    private void helper(int n, int k, List<List<Integer>> res, List<Integer> list, int start) {
        if (k == 0) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = start, end = n - k + 1; i <= end; i++) { // use end to end recursion ealier
            list.add(i);
            helper(n, k-1, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
