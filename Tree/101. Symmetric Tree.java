public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root, root.val, root, root.val);
    }
    
    private boolean isSymmetric(TreeNode n1, int v1, TreeNode n2, int v2) {
        if (v1 != v2) return false;
        if (n1 == null || n2 == null) return n1 == null && n2 == null;
        return isSymmetric(n1.left, n1.val, n2.right, n2.val) && isSymmetric(n1.right, n1.val, n2.left, n2.val);
    }
}
