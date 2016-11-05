public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, s, new ArrayList<>(), 0);
        return res;
    }
    
    private void dfs(List<List<String>> res, String s, List<String> list, int start) {
        if (start == s.length()) res.add(new ArrayList<>(list));
        
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                list.add(s.substring(start, i + 1));
                dfs(res, s, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
