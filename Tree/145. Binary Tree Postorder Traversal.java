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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> leftNodes = new Stack();
        TreeNode cur = root;
        while (cur != null) {
            list.addFirst(cur.val);
            if (cur.left != null) {
                leftNodes.push(cur.left);
            }
            cur = cur.right;
            if (cur == null && !leftNodes.empty()) {
                cur = leftNodes.pop();
            }
        }
        return list;
    }
}
