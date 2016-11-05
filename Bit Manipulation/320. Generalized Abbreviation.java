// ========================== backtracking ========================== //

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(word, res, new StringBuilder(), 0, 0);
        return res;
    }
    
    private void dfs(String word, List<String> res, StringBuilder sb, int count, int i) {
        int len = sb.length();
        if (i == word.length()) {
            if (count != 0) sb.append(count);
            res.add(sb.toString());
        } else {
            // abbr word[i]
            dfs (word, res, sb, count + 1, i + 1);
            
            // not abbr word[i]
            if (count != 0) sb.append(count);
            dfs(word, res, sb.append(word.charAt(i)), 0, i + 1);
        }
        sb.setLength(len);
    }
}

// ========================== backtracking ========================== //

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(word, res, "", 0, 0);
        return res;
    }
    
    private void dfs(String word, List<String> res, String cur, int count, int i) {
        if (i == word.length()) {
            if (count != 0) cur += count;
            res.add(cur);
        } else {
            // abbr word[i]
            dfs (word, res, cur, count + 1, i + 1);
            
            // not abbr word[i]
            dfs(word, res, cur + (count > 0 ? count : "") + word.charAt(i), 0, i + 1);
        }
    }
}

// ========================== recursion slow solution ========================== //

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        int len = word.length();
        res.add(len > 0 ? String.valueOf(len) : "");
        for (int i = 0; i < len; i++) {
            for (String right : generateAbbreviations(word.substring(i+1))) {
                String leftNum = i > 0 ? String.valueOf(i) : "";
                res.add(leftNum + word.substring(i, i+1) + right);
            }
        }
        return res;
    }
}
