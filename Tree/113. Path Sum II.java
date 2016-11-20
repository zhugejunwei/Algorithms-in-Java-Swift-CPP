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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        dfs(root, sum, res, new ArrayList());
        return res;
    }
    private void dfs(TreeNode root, int target, List<List<Integer>> res, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            res.add(new ArrayList(list));
        }
        dfs(root.left, target - root.val, res, list);
        dfs(root.right, target - root.val, res, list);
        list.remove(list.size() - 1);
    }
}
