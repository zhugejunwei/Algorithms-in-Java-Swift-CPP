// divide and conqur 16ms
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return helper(s, wordDict, new HashMap<String, List<String>>());
    }
    
    private List<String> helper(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        
        for (String word:wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = backtrack(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}


// pruned dp/divide and conquer 8ms
public class Solution {
    Map<Integer, List<String>> map = new HashMap();
    public List<String> wordBreak(String s, Set<String> wordDict) {
        int maxLength = 0;
        for (String word : wordDict) maxLength = Math.max(word.length(), maxLength);
        return helper(s, wordDict, maxLength, 0);
    }
    
    private List<String> helper(String s, Set<String> dict, int max, int start) {
        List<String> res = new ArrayList();
        if (start == s.length()) {
            res.add("");
            return res;
        }
        for (int i = start + 1; i <= start + max && i <= s.length(); i++) {
            String tmp = s.substring(start, i);
            if(dict.contains(tmp)) {
                List<String> list;
                if (map.containsKey(i)) list = map.get(i);
                else list = helper(s, dict, max, i);
                for (String ss : list) res.add(tmp + (ss.isEmpty() ? "" : " ") + ss);
            }
        }
        map.put(start, res);
        return res;
    }
}

