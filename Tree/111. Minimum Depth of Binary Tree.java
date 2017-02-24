// dfs
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}

// bfs
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return root.left == null || root.right == null ? Math.max(minDepth(root.left), minDepth(root.right)) + 1 : Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
