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
    Map<Integer, List<List<Integer>>> map = new HashMap();
    int k, t;
    public List<List<Integer>> getFactors(int n) {
        if (n <= 3) return new ArrayList();
        t = n;
        k = (int)Math.sqrt(n);
        return helper(n, 2);
    }
    
    private List<List<Integer>> helper(int target, int start) {
        if (map.containsKey(target)) return map.get(target);
        
        List<List<Integer>> res = new ArrayList();
        if (1 == target) {
            res.add(new ArrayList());
        }
        
        for (int fact = start; fact <= target; fact++) {
            if (start > k) break;
            if (target / fact == 0) break;
            if (target % fact != 0) continue;
            List<List<Integer>> pre = helper(target / fact, fact);
            
            for (List<Integer> l : pre) {
                if ((l.size() > 0 && fact > l.get(l.size() - 1)) || fact == t) continue;
                l.add(fact);
                res.add(new ArrayList(l));
                l.remove(l.size() - 1);
            }
        }
        map.put(target, res);
        return res;
    }
}
