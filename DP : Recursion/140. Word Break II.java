public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<String, List<String>>());
    }
    
    private List<String> backtrack(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
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


////  pruned backtracking

public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<Integer>[] starts = new List[s.length() + 1]; // all valid start positions
        starts[0] = null;
        int maxWordLen = 0;
        for (String word : wordDict) {
            maxWordLen = Math.max(word.length(), maxWordLen);
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= i-maxWordLen && j >= 0; j--) {
                if (starts[j] == null) continue;
                if (wordDict.contains(s.substring(j, i))) {
                    if (starts[i] == null) {
                        starts[i] = new ArrayList<>();
                    }
                    starts[i].add(j);
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (starts[s.length()] == null) {
            return res;
        }
        dfs(s, res, "", starts, s.length());
        return res;
    }
    
    private void dfs(String s, List<String> res, String path, List<Integer>[] starts, int end) {
        if (end == 0) {
            res.add(path.substring(1));
            return;
        }
        for (Integer start : starts[end]) {
            String word = s.substring(start, end);
            dfs(s, res, " " + word + path, starts, start);
        }
    }
}
