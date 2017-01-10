/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode next = null, pre = null, cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    // left child
                    if (pre != null) { // there is pre node
                        pre.next = cur.left;
                    } else { // no pre node, so this should be the start node of next level
                        next = cur.left;
                    }
                    pre = cur.left;
                }
                
                if (cur.right != null) {
                    // right child
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        next = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = next;
            next = pre = null;
        }
    }
}
