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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return q == null && p == null;
        return helper(p, p.val, q, q.val);
    }
    
    private boolean helper(TreeNode n1, int v1, TreeNode n2, int v2) {
        if (v1 != v2) return false;
        if (n1 == null || n2 == null) return n1 == null && n2 == null;
        
        return helper(n1.left, n1.val, n2.left, n2.val) && helper(n1.right, n1.val, n2.right, n2.val);
    }
}

// 2 lines

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == null && q == null;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
