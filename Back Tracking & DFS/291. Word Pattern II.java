public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        return isMatch(pattern, 0, str, 0, new HashMap(), new HashSet());
    }
    
    private boolean isMatch(String pat, int i, String str, int j, Map<Character, String> map, Set<String> set) {
        if (i == pat.length() && j == str.length()) return true;
        if (i == pat.length() || j == str.length()) return false;
        
        char p = pat.charAt(i);
        if (map.containsKey(p)) {
            String s = map.get(p);
            if (!str.startsWith(s, j)) return false;
            
            return isMatch(pat, i + 1, str, j + s.length(), map, set);
        }
        
        for (int k = j; k < str.length(); k++) {
            String s = str.substring(j, k + 1);
            if (set.contains(s)) continue;
            set.add(s);
            map.put(p, s);
            if (isMatch(pat, i + 1, str, k + 1, map, set)) return true;
            set.remove(s);
            map.remove(p);
        }
        return false;
    }
}
