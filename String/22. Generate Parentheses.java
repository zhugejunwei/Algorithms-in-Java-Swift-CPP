public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        helper(res, n, 0, 0, "");
        return res;
    }
    
    private void helper(List<String> res, int n, int l, int r, String s) {
        if (l == n && r == n) {
            res.add(new String(s));
            return;
        }
        
        
        // add left
        if (l < n)
            helper(res, n, l + 1, r, s + '(');
        
        // add right
        if (r < l)
            helper(res, n, l, r + 1, s + ')');
    }
}
