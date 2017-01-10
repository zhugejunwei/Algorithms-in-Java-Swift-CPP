public class Solution {
    public boolean wordPatternMatch(String p, String s) {
        Map<Character, String> map = new HashMap();
        Set<String> set = new HashSet();
        return tryDifLen(s, 0, p, 0, map, set);
    }
    
    private boolean tryDifLen(String s, int i, String p, int j, Map<Character, String> map, Set<String> set) {
        if (i == s.length() && j == p.length()) return true;
        if (i == s.length() || j == p.length()) return false;
        
        char c = p.charAt(j);
        
        if (map.containsKey(c)) {
            String tmp = map.get(c);
            if (s.startsWith(tmp, i)) {
                return tryDifLen(s, i + tmp.length(), p, j + 1, map, set);
            } else return false;
        }
        
        for (int k = i; k < s.length(); k++) {
            String sub = s.substring(i, k + 1);
            if (set.contains(sub)) continue;
            
            map.put(c, sub);
            set.add(sub);
            
            if (s.startsWith(sub, i)) {
                if (tryDifLen(s, k + 1, p, j + 1, map, set)) return true;
            }
            
            map.remove(c);
            set.remove(sub);
        }
        return false;
    }
}
