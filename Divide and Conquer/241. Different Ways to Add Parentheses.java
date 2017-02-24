public class Solution {
    Map<String, List<Integer>> map = new HashMap();
    public List<Integer> diffWaysToCompute(String input) {
        return helper(input);
    }
    
    private List<Integer> helper(String s) {
        if (s.length() == 0) new ArrayList();
        if (map.containsKey(s)) return map.get(s);
        List<Integer> res = new ArrayList();
        boolean isExpression = false;
        for (int i = 0; i < s.length(); i++) {
            if ("+-*".indexOf(s.charAt(i)) != -1) {
                isExpression = true;
                List<Integer> left = helper(s.substring(0, i));
                List<Integer> right = helper(s.substring(i + 1));
                for (int l : left)
                    for (int r : right)
                        res.add(cal(l, r, s.charAt(i)));
            }
        }
        if (!isExpression) res.add(Integer.valueOf(s));
        map.put(s, res);
        return res;
    }
    
    private int cal(int l, int r, char op) {
        switch(op) {
            case '+': return l + r;
            case '-': return l - r;
            case '*': return l * r;
        }
        return -1;
    }
}

// use more mapped duplicated string
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap();
        return calculate(input, map);
    }
    
    private List<Integer> calculate(String s, Map<String, List<Integer>> map) {
        if (map.containsKey(s)) return map.get(s);
        boolean isExpression = false;
        List<Integer> list = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            if ("+-*".indexOf(s.charAt(i)) != -1) {
                List<Integer> left = new ArrayList();
                List<Integer> right = new ArrayList();
                if (map.containsKey(s.substring(0, i))) {
                    left = map.get(s.substring(0, i));
                } else {
                    left = calculate(s.substring(0, i), map);
                }
                if (map.containsKey(s.substring(i + 1))) {
                    right = map.get(s.substring(i + 1));
                } else {
                    right = calculate(s.substring(i + 1), map);
                }
                for (int l : left) {
                    for (int r : right) {
                        list.add(cal(l, r, s.charAt(i)));
                    }
                }
                isExpression = true;
            }
        }
        if (!isExpression) list.add(Integer.valueOf(s));
        map.put(s, list);
        return list;
    }
    private int cal(int l, int r, char c) {
        switch (c) {
            case '+': return l + r;
            case '-': return l - r;
            case '*': return l * r;
        }
        return 0;
    }
}
