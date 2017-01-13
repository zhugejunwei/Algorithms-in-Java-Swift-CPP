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
                    // find the two invalid nodes, first is too big, second is too small
                    if (node != null && pre.val > node.val) {
                        if (n1 == null) n1 = pre;
                        n2 = node;
                    }
                    pre = node;
                    
                    tmp.right = null; // break the connection we built before
                    node = node.right;
                }
            } else {
                // find the two invalid nodes, first is too big, second is too small
                if (pre != null && pre.val > node.val) {
                    if (n1 == null) n1 = pre;
                    n2 = node;
                }
                pre = node;
                
                node = node.right;
            }
        }
        
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}
