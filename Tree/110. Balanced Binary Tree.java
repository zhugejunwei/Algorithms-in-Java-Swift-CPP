// bfs, 2ms, O(nlogn)
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

// dfs, 1ms, O(n)
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return dfsHeight(root) != -1;
    }
    
    private int dfsHeight(TreeNode root) {
        if (root == null) return 0;
        
        int left = dfsHeight(root.left); // left height
        if (left == -1) return -1;
        int right = dfsHeight(root.right); // right height
        if (right == -1) return -1;
        
        if (Math.abs(left - right) > 1) return -1;
        
        return Math.max(left, right) + 1;
    }
}
