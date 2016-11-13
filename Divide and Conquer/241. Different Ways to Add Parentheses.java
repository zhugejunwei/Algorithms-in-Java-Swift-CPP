public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        return compute(input, map);
    }
    private List<Integer> compute(String s, Map<String, List<Integer>> map) {
        if (map.get(s) != null) return map.get(s);
        boolean expression = false;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if ("+-*".indexOf(s.charAt(i)) != -1) {
                List<Integer> left = compute(s.substring(0,i), map);
                List<Integer> right = compute(s.substring(i+1,s.length()), map);
                for (int l : left) {
                    for (int r : right) {
                        res.add(cal(l, r, s.charAt(i)));
                    }
                }
                expression = true;
            }
        }
        if (!expression) res.add(Integer.valueOf(s));
        map.put(s, res);
        return res;
    }
    private int cal(int l, int r, char c) {
        int res = 0;
        switch(c) {
            case '+': res = l + r; break;
            case '-': res = l - r; break;
            case '*': res = l * r; break;
            default: break;
        }
        return res;
    }
}
