/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// -------- iteration, BFS ------ //
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque();
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        q.add(root);
        while (!q.isEmpty()) {
            int levelNum = q.size();
            List<Integer> list = new ArrayList();
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
                list.add(cur.val);
            }
            res.add(0, list);
        }
        return res;
    }
}

// ------- recursion, DFS -------//
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, root, 0);
        Collections.reverse(res);
        return res;
    }
    private void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (level == res.size()) res.add(new ArrayList());
        res.get(level).add(root.val);
        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }
}
