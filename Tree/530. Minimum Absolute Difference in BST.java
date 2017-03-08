// first solution
public class Solution {
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        helper(root, null);
        return min;
    }
    
    private TreeNode helper(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        
        pre = helper(root.left, pre);
        
        if (pre != null) {
            min = Math.min(min, Math.abs(pre.val - root.val));
        }
        
        return helper(root.right, root);
    }
}

// second solution
public class Solution {
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMinimumDifference(root.left);
        if (pre != null)
            min = Math.min(min, Math.abs(pre.val - root.val));
        pre = root;
        return getMinimumDifference(root.right);
    }
}
