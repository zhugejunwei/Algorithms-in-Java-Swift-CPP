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
    public void recoverTree(TreeNode root) {
        TreeNode n1 = null, n2 = null, pre = null; // 2 invalid nodes
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode tmp = node.left;
                while (tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) { // build the connect, right most node to root node
                    tmp.right = node;
                    node = node.left;
                } else {
                    //----------- do something with current node below -------------
                    
                    // find the two invalid nodes, first is too big, second is too small
                    if (pre.val > node.val) {
                        if (n1 == null) n1 = pre;
                        n2 = node;
                    }
                    pre = node;
                    
                    //---------------------------------------------------------------
                    
                    tmp.right = null; // break the connection we built before
                    node = node.right;
                }
            } else {
                //----------- do something with current node below -------------
                
                // find the two invalid nodes, first is too big, second is too small
                if (pre != null && pre.val > node.val) {
                    if (n1 == null) n1 = pre;
                    n2 = node;
                }
                pre = node;
                
                //---------------------------------------------------------------
                
                node = node.right;
            }
        }
        
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}


// O(n) solution
public class Solution {
    TreeNode n1 = null, n2 = null;
    public void recoverTree(TreeNode root) {
        helper(root, null);
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
    
    private TreeNode helper(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        pre = helper(root.left, pre);
        if (pre != null && pre.val > root.val) {
            if (n1 == null) n1 = pre;
            n2 = root;
        }
        return helper(root.right, root);
    }
}
