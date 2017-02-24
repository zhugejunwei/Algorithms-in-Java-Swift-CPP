// =================== BFS =================== //

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        bfs(s, res);
        return res;
    }
    
    private void bfs(String s, List<String> res) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(s);
        visited.add(s);
        boolean found = false;
        while (!q.isEmpty()) {
            s = q.poll();
            if (isValid(s)) {
                res.add(s);
                found = true; // stay the current level, dont need to go through next level
            }
            if (found) continue;
            for (int i = 0; i < s.length(); i++) { // iterate the next level
                if (s.charAt(i) != ')' && s.charAt(i) != '(') continue; // skip letters
                String tmp = s.substring(0,i) + s.substring(i + 1); // except s[i]
                if (visited.contains(tmp)) continue;
                q.add(tmp);
                visited.add(tmp);
            }
        }
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }
}

// =================== DFS faster =================== //

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        DFS(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }
    
    // if ))), these three ))) are the same.
    // ))(), first two )) are the same.
    // so remove the consecutive ))'s first )
    private void DFS(String s, List<String> res, int last_i, int last_j, char[] pare) {
        for (int count = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == pare[0]) count++;
            if (s.charAt(i) == pare[1]) count--;
            if (count >= 0) continue;
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == pare[1] && (j == last_j || s.charAt(j - 1) != pare[1])) {
                    DFS(s.substring(0, j) + s.substring(j + 1), res, i, j, pare);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (pare[0] == '(') {
            DFS(reversed, res, 0, 0, new char[]{')', '('});
        } else {
            res.add(reversed);
        }
    }
}

// =================== DFS =================== //

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int lm = 0, rm = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') lm++;
            if (s.charAt(i) == ')') {
                if (lm == 0) rm++;
                else lm--;
            }
        }
        Set<String> res = new HashSet();
        DFS(s, res, new StringBuilder(), 0, lm, rm, 0);
        return new ArrayList<String>(res);
    }
    
    private void DFS(String s, Set<String> res, StringBuilder sb, int i, int lm, int rm, int open) {
        if (lm < 0 || rm < 0 || open < 0) return;
        if (i == s.length()) {
            if (lm == 0 && rm == 0)
                res.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        int len = sb.length();
        
        if (c == '(') {
            DFS(s, res, sb, i + 1, lm - 1, rm, open); // not use
            DFS(s, res, sb.append(c), i + 1, lm, rm, open + 1); // use
        }
        else if (c == ')') {
            DFS(s, res, sb, i + 1, lm, rm - 1, open); // not use
            DFS(s, res, sb.append(c), i + 1, lm, rm, open - 1); // use
        }
        else
            DFS(s, res, sb.append(c), i + 1, lm, rm, open);
        sb.setLength(len);
    }
}
