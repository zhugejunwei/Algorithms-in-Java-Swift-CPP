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


//

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        Map<String, Boolean> map = new HashMap();
        helper(s, res, map, new ArrayList());
        return res;
    }
    
    private void helper(String s, List<List<String>> res, Map<String, Boolean> map, List<String> list) {
        if (s == null || s.isEmpty()) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(0, i + 1);
            if ((map.containsKey(tmp) && map.get(tmp)) || isPl(tmp.toCharArray())) {
                map.put(tmp, true);
                list.add(tmp);
                helper(s.substring(tmp.length()), res, map, list);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private boolean isPl(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            if (s[l++] != s[r--]) return false;
        }
        return true;
    }
}
