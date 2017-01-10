/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        if (root == null) return res;
        dfs(res, "", root);
        return res;
    }
    private void dfs(List<String> res, String s, TreeNode root) {
        if (root.left == null && root.right == null) {
            s += root.val;
            res.add(new String(s));
            return;
        }
        
        if (root.left != null) dfs(res, s + root.val + "->", root.left);
        if (root.right != null) dfs(res, s + root.val + "->", root.right);
    }
}

// another
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        if (root == null) return res;
        String s = String.valueOf(root.val);
        findPath(res, root, s);
        return res;
    }
    private void findPath(List<String> res, TreeNode root, String s) {
        if (root.left == null && root.right == null) {
            res.add(new String(s));
            return;
        }
        if (root.left != null) findPath(res, root.left, s + "->" + root.left.val);
        if (root.right != null) findPath(res, root.right, s + "->" + root.right.val);
    }
}
