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
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet(wordDict);
        int maxLen = 0;
        for (String word : wordDict) maxLen = Math.max(word.length(), maxLen);
        return helper(s, dict, maxLen, 0);
    }
    private List<String> helper(String s, Set<String> dict, int maxLen, int start) {
        if (s.length() == start) return new ArrayList(Arrays.asList(""));
        if (map.containsKey(start)) return map.get(start);
        List<String> res = new ArrayList();
        
        for (int i = start; i < s.length() && i < start + maxLen; i++) {
            String pre = s.substring(start, i + 1);
            if (dict.contains(pre)) {
                List<String> list = helper(s, dict, maxLen, i + 1);
                for (String str : list) {
                    res.add(new String(pre + (str.isEmpty() ? "" : " ") + str));
                }
            }
        }
        map.put(start, res);
        return res;
    }
}

