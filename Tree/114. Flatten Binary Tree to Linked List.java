public class Solution {
    TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }
        
        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
