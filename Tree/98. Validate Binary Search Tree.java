// ============================= Recursion ============================= //

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

// ============================= Inorder Traversal ============================= //

public class Solution {
    TreeNode invalidNode = new TreeNode(Integer.MAX_VALUE);
    
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null) != invalidNode;
    }
    
    private TreeNode isValid(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        pre = isValid(root.left, pre);
        if (pre != null && pre.val >= root.val) return invalidNode;
        return isValid(root.right, root);
    }
}
