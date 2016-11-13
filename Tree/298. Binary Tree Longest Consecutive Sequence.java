public class Solution {
    int max = 1;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 1, root.val);
        return max;
    }
    
    private void dfs(TreeNode node, int count, int pre) {
        if (node == null) return;
        if (node.val == pre + 1) {
            count++;
            max = Math.max(max, count);
        } else count = 1;
        dfs(node.left, count, node.val);
        dfs(node.right, count, node.val);
    }
}
