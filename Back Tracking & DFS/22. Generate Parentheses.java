public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        DFS(list, "", 0, 0, n);
        return list;
    }
    // "" -> str: 递归传递的参数
    public void DFS(List<String> list, String str, int open, int close, int max) {
        // 退出
        if (str.length() == max * 2) {
            list.add(str);
            return; //
        }
        // 递归条件
        if (open < max) {
            DFS(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            DFS(list, str + ")", open, close + 1, max);
        }
    }
}


public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        dfs(n, res, 0, 0, "");
        return res;
    }
    
    private void dfs(int n, List<String> res, int l, int r, String s) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }
        if (l < n) {
            dfs(n, res, l + 1, r, s + "(");
        }
        if (r < l) {
            dfs(n, res, l, r + 1, s + ")");
        }
    }
}
