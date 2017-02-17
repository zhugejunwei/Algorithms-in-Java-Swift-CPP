// faster
public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList();
        helper(res, new ArrayList(), n, n, 2);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int n, int remain, int start) {
        if (list.size() > 0) {
            list.add(remain);
            res.add(new ArrayList(list));
            list.remove(list.size() - 1);
        }
        for (int i = start; i < remain; i++) {
            // skip duplicates
            if (i > (int)Math.sqrt(remain)) break;
            if (remain%i == 0) {
                list.add(i);
                helper(res, list, n, remain/i, i);
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
        // this step is to skip duplicates, but the for loop still runs, so this can only skip
        // duplicates results, but cannot skip duplicates operations, that's why it's slower.
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
