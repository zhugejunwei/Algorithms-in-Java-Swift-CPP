// faster
public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList();
        helper(n, res, new ArrayList(), 2);
        return res;
    }
    
    private void helper(int n, List<List<Integer>> res, List<Integer> list, int start) {
        if (n == 1) {
            if (list.size() > 1) res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            //// this step is to skip duplicates, and make it faster
            if (i > (int)Math.sqrt(n)) i = n;
            if (n % i == 0) {
                list.add(i);
                helper(n/i, res, list, i);
                list.remove(list.size() - 1);
            }
        }
    }
}

// slower solution
public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList();
        helper(res, new ArrayList(), n, n, 2);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int n, int remain, int start) {
        // this step is to skip duplicates
        if (remain < start) return;
        
        if (list.size() > 0) {
            list.add(remain);
            res.add(new ArrayList(list));
            list.remove(list.size() - 1);
        }
        for (int i = start; i < remain; i++) {
            if (remain%i == 0) {
                list.add(i);
                helper(res, list, n, remain/i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
